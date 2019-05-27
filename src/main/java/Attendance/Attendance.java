package Attendance;

import Attendance.Exceptions.*;
import Data.Professor;
import Data.Student;
import Data.UTClass;
import Requests.AttendanceRequest;

import java.util.ArrayList;

public class Attendance implements AttendanceInterface{
    private static Attendance ourInstance = new Attendance();

    public static Attendance getInstance() {
        return ourInstance;
    }


    private ArrayList<UTClass> classes;
    private Attendance() {
        rebootData();
    }

    private UTClass examForAttendance;
    private ArrayList<Student> presentStudents;
    private Boolean presentAcceptance;
    private Professor professorOfExam;
    private void rebootData(){
        classes = AttendanceRequest.getClasses();
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
        for (Student student :
             examForAttendance.getStudents()) {
            if(student.getId().equals(studentId)){
                presentStudents.add(student);
                return;
            }
        }
        throw new StudentNotFound();
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
        ArrayList<String> students = new ArrayList<String>();
        for (Student student:
             presentStudents) {
            students.add(student.getId());
        }
        if(!AttendanceRequest.setPresence(examId, isTeacherSigned, students))
            throw new CanNotCompleteAttendance();
        rebootData();
    }
}
