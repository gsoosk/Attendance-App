package Attendance;

import Attendance.Exceptions.*;
import Data.Student;
import Data.UTClass;

import java.util.ArrayList;

public interface AttendanceInterface {
    public ArrayList<UTClass> getExamsList();
    public void selectExamForAttendance(int examId) throws ExamNotFound;
    public void attendNewStudent(String studentId, Boolean attendedOrNot)
            throws StudentNotFound, NoExamSelected;
    public ArrayList<Student> getNotEvaluatedExamStudents();
    public void acceptAttendance();
    public void getProfessorAccept(String id) throws ProfessorNotFound;
    public void completeAttendance() throws CanNotCompleteAttendance;
}
