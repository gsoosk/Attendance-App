package Attendance;

import Attendance.Exceptions.ExamNotFound;
import Attendance.Exceptions.ProfessorNotFound;
import Attendance.Exceptions.StudentNotFound;
import Data.UTClass;

import java.util.ArrayList;

public interface AttendanceInterface {
    public ArrayList<UTClass> getExamsList();
    public void selectExamForAttendance(int examId) throws ExamNotFound;
    public void attendNewStudent(String studentId, Boolean attendedOrNot)
            throws StudentNotFound;
    public void acceptAttendance();
    public void getProfessorAccept(String id) throws ProfessorNotFound;
    public void completeAttendance();
}
