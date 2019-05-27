package Tools;

import Attendance.Attendance;
import Attendance.Exceptions.*;
import Data.Professor;
import Data.Student;
import Data.UTClass;

import java.util.ArrayList;
import java.util.Scanner;

import static Tools.State.*;

enum State {
    INITIAL, SET_EXAM_FOR_ATTENDANCE, EXIT_SYSTEM, ATTENDANCE, ACCEPT_ATTENDANCE, GET_PROFESSOT_ACCEPT
        }

public class CommandHandler {
    private Attendance attendance;
    private Scanner in;
    private State myState;
    private void showExamsList() {
        ArrayList<UTClass> examsList = attendance.getExamsList();
        for (UTClass exam : examsList) {
            System.out.println("course name : " + exam.getCourseName() + " | examID : " + exam.getExamId() + " | Professor : "
                    + exam.getProfessor().getFirstName() + " " + exam.getProfessor().getLastName());

        }
    }
    private void showStudentsList(){
        ArrayList<Student> list = attendance.getNotEvaluatedExamStudents();
        for (Student student:
                list) {
            System.out.println("student name : " + student.getFirstName() + student.getLastName() +
                    " | id : " + student.getId());
        }
        System.out.println();
    }
    private void initialStateHandler(){
        System.out.println("Please enter one of the following command according your desired order\n" +
                "1 : Getting exams list \n" +
                "2 : Terminating the system");
        int inputCommand = in.nextInt();
        switch (inputCommand) {
            case 1:
                myState = SET_EXAM_FOR_ATTENDANCE;
                showExamsList();
                break;
            case 2:
                myState = State.EXIT_SYSTEM;
                break;
            default:
                System.out.println("The command you entered is not valid.");
                break;
        }
    }
    private void setExamForAttendanceStateHandler(){
        System.out.println("Please Enter the examID for attendance.");
        int examID = in.nextInt();
        try {
            attendance.selectExamForAttendance(examID);
            myState = ATTENDANCE;
        }catch (ExamNotFound examNotFound){
            System.out.println("The examID you entered is not valid please enter another examID");
        }
    }
    private void attendStudent(){
        System.out.println("Please Enter the attendance state of the student in this format : <Student_ID> <absent/present>");
        String inputCommand = in.next();
        String commandParts[] = inputCommand.split(" ");
        if(commandParts.length != 2 ) {
            System.out.println("Your input format is not correct.");
            return;
        }
        String studentID = commandParts[0];
        Boolean presence;
        if(commandParts[1].equals("absent"))
            presence = false;
        else if(commandParts[1].equals("present"))
            presence = true;
        else {
            System.out.println("Your input format is not correct.");
            return;
        }

        try {
            attendance.attendNewStudent(studentID, presence);
        }catch(StudentNotFound studentNotFound){
            System.out.println("The stududentID you entered does not exist.");
        }catch (NoExamSelected noExamSelected){
            noExamSelected.printStackTrace();
        }
    }
    private void attendanceHandler(){
        System.out.println("Please enter one of the following command according your desired order\n" +
                "1 : Set a student attendance \n" +
                "2 : Terminating the attendance\n" +
                "3 : Show not evaluated Students list"
                );
        int inputCommand = in.nextInt();
        switch (inputCommand) {
            case 1:
                attendStudent();
                break;
            case 2:
                myState = ACCEPT_ATTENDANCE;
                break;
            case 3:
                showStudentsList();
                break;
            default:
                System.out.println("The command you entered is not valid.");
                break;
        }
    }
    private void acceptAttendanceHandler(){
        System.out.println("Absent students are:");
        showStudentsList();
        System.out.println("Please enter one of the following command according your desired order\n " +
                "1 : Accenpting attendance\n"  +
                "2 : Go back to attendance"
                );
        int inputCommand = in.nextInt();
        switch (inputCommand) {
            case 1:
                attendance.acceptAttendance();
                myState = GET_PROFESSOT_ACCEPT;
                break;
            case 2:
                myState = ATTENDANCE;
                break;
            default:
                System.out.println("The command you entered is not valid.");
                break;
        }
    }
    private void getProfessorAcceptHandler(){
        System.out.println("Please enter professorID for accepring attendance by professor" );

        String inputCommand = in.next();
        try {
            attendance.getProfessorAccept(inputCommand);
            attendance.completeAttendance();
            myState = INITIAL;
        }catch (ProfessorNotFound professorNotFound){
            System.out.println("The professorID you entered is not found.");
        }catch (CanNotCompleteAttendance canNotCompleteAttendance){
            System.out.println("Could not send attendance data. System will retry later.");
        }
    }
    public void exec(){
        int inputCommand;
        in = new Scanner(System.in);
        attendance = Attendance.getInstance();
        myState = INITIAL;

        System.out.println("Welcome to AttendanceData System. :)");

        while(true){
            switch(myState) {
                case INITIAL:
                    initialStateHandler();
                    break;
                case SET_EXAM_FOR_ATTENDANCE:
                    setExamForAttendanceStateHandler();
                    break;
                case EXIT_SYSTEM:
                    return;
                case ATTENDANCE:
                    attendanceHandler();
                    break;
                case ACCEPT_ATTENDANCE:
                    acceptAttendanceHandler();
                    break;
                case GET_PROFESSOT_ACCEPT:
                    getProfessorAcceptHandler();
                    break;
            }
        }
    }
}
