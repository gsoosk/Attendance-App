package Attendance;

import Attendance.Exceptions.ExamNotFound;
import Attendance.Exceptions.NoExamSelected;
import Attendance.Exceptions.ProfessorNotFound;
import Attendance.Exceptions.StudentNotFound;
import Data.UTClass;
import Requests.AttendanceRequest;

import java.util.ArrayList;
import java.util.HashMap;

public class Attendance implements AttendanceInterface{
    private static Attendance ourInstance = new Attendance();

    public static Attendance getInstance() {
        return ourInstance;
    }


    private ArrayList<UTClass> classes;
    private Attendance() {
        classes = AttendanceRequest.getClasses();
    }

    private UTClass examForAttendance;

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
        throw new ExamNotFound();
    }


    public void attendNewStudent(String studentId, Boolean attendedOrNot) throws StudentNotFound, NoExamSelected {

    }

    public void acceptAttendance() {

    }

    public void getProfessorAccept(String id) throws ProfessorNotFound {

    }

    public void completeAttendance() {

    }
}
