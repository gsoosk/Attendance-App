package Tools;

import Attendance.Attendance;
import Data.UTClass;

import java.util.ArrayList;
import java.util.Scanner;

public class CommandHandler {
    public void getCommand(){
        String inputCommand;
        Scanner in = new Scanner(System.in);
        Attendance attendance = Attendance.getInstance();

        while (true){
            inputCommand = in.nextLine();

            String commandParts[] = inputCommand.split(" ", 1);
            String order = commandParts[0];
            String parameters;
            if(commandParts.length > 1)
                parameters = commandParts[1];

            if(order.equals("getExamsList")) {
                ArrayList<UTClass> examsList =  attendance.getExamsList();
                for (UTClass exam : examsList){
                    System.out.println(exam.getCourseName() + " id : " + Integer.toString(exam.getExamId()) + " Professor : " + exam.getProfessor() );
                }
            }
            else{
            }
        }

    }
}
