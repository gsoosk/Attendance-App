package Attendance;

import Attendance.Exceptions.*;
import Attendance.Schedulers.ClassesScheduler;
import Attendance.Schedulers.PresenceScheduler;
import Data.EducationalAdmin;
import Data.Professor;
import Data.Student;
import Data.UTClass;
import Requests.AttendanceRequest;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Attendance implements AttendanceInterface{
    private static Attendance ourInstance = new Attendance();

    private static EducationalAdmin educationalAdmin = new EducationalAdmin();

    public static Attendance getInstance() {
        return ourInstance;
    }

    private HashSet<String> dataToSend;

    public void setDataToSend(HashSet<String> dataToSend) {
        this.dataToSend = dataToSend;
    }

    public HashSet<String> getDataToSend() {
        return dataToSend;
    }

    private ArrayList<UTClass> classes;
    private Attendance() {
        dataToSend = new HashSet<String>();
        ScheduledExecutorService classesScheduler;
        classesScheduler = Executors.newSingleThreadScheduledExecutor();
        classesScheduler.scheduleAtFixedRate(new ClassesScheduler(), 0, 1, TimeUnit.DAYS);

        ScheduledExecutorService presenceScheduler;
        presenceScheduler = Executors.newSingleThreadScheduledExecutor();
        presenceScheduler.scheduleAtFixedRate(new PresenceScheduler(), 0, 5, TimeUnit.MINUTES);

        rebootData();
    }

    private UTClass examForAttendance;
    private ArrayList<Student> presentStudents;
    private Boolean presentAcceptance;
    private Professor professorOfExam;

    public void setClasses(ArrayList<UTClass> classes) {
        this.classes = classes;
    }

    private void rebootData(){
        examForAttendance = null;
        presentAcceptance = false;
        presentStudents = new ArrayList<Student>();
        professorOfExam = null;
    }

    public ArrayList<UTClass> getExamsList() {
        return classes;
    }

    public void selectExamForAttendance(int examId) throws ExamNotFound {
        for (UTClass utClass:
             classes) {
            if(utClass.getExamId() == examId){
                examForAttendance = utClass;
                return;
            }
        }
        examForAttendance = null;
        throw new ExamNotFound();
    }


    public void attendNewStudent(String studentId, Boolean attendedOrNot) throws StudentNotFound, NoExamSelected {
        if(examForAttendance == null)
            throw new NoExamSelected();

        int sid = Integer.valueOf(studentId);
        for (Student student :
             examForAttendance.getStudents()) {
            if(student.getId() == sid){
                if(attendedOrNot)
                    presentStudents.add(student);
                return;
            }
        }
        throw new StudentNotFound();
    }

    public ArrayList<Student> getNotEvaluatedExamStudents() {
        ArrayList<Student> notEvaluated = new ArrayList<Student>();
        for (Student student:
             examForAttendance.getStudents()) {
            boolean canAdd = true;
            for (Student presentStudent:
                 presentStudents) {
                if(student.getStudentID().getId() == presentStudent.getStudentID().getId()){
                    canAdd = false;
                    break;
                }
            }

            if(canAdd)
                notEvaluated.add(student);
        }
        return notEvaluated;
    }

    public EducationalAdmin getEducationalAdmin() {
        return educationalAdmin;
    }

    public void acceptAttendance(){
        presentAcceptance = true;
    }

    public void getProfessorAccept(String id) throws ProfessorNotFound {
       if(!examForAttendance.getProfessor().getId().equals(id))
           throw new ProfessorNotFound();
       professorOfExam = examForAttendance.getProfessor();
    }

    public void completeAttendance()throws CanNotCompleteAttendance {
        int examId = examForAttendance.getExamId();
        boolean isTeacherSigned = ( professorOfExam != null );
        ArrayList<Integer> students = new ArrayList<Integer>();
        for (Student student:
             presentStudents) {
            students.add(student.getStudentID().getId());
        }
        String data = AttendanceRequest.getSendingData(examId, isTeacherSigned, students);
        if(!AttendanceRequest.setPresence(data)) {
            dataToSend.add(data);
            rebootData();
            throw new CanNotCompleteAttendance();
        }

        rebootData();
    }
}
