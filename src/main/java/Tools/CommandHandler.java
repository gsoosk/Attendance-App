package Tools;

import Attendance.Attendance;
import Attendance.Exceptions.ExamNotFound;
import Data.UTClass;

import java.util.ArrayList;
import java.util.Scanner;

import static Tools.State.INITIAL;
import static Tools.State.SET_EXAM_FOR_ATTENDANCE;

enum State {
    INITIAL, SET_EXAM_FOR_ATTENDANCE, EXIT_SYSTEM
        }

public class CommandHandler {
    private Attendance attendance;
    private Scanner in;
    private State myState;
    private void showExamsList(){
        ArrayList<UTClass> examsList =  attendance.getExamsList();
        for (UTClass exam : examsList){
            System.out.println(exam.getCourseName() + " examID : " + Integer.toString(exam.getExamId()) + " Professor : " + exam.getProfessor() );
        }
    }
    private void initialStateHandler(){
        System.out.println("Please enter one of the following command according your desired order\n " +
                "1 : For getting exams list \n" +
                "2 : For terminating");
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
        }catch (ExamNotFound examNotFound){
            System.out.println("The examID you entered is not valid please enter another examID");
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
            }

        }

    }
}
