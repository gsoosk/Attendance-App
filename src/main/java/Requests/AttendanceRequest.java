package Requests;

import Data.UTClass;

import java.util.ArrayList;

public class AttendanceRequest {
    public static void getClasses(){
        String projectsInfo = null;
        try {
            projectsInfo = HttpRequest.getRemoteData("api/attendance");
            System.out.println(projectsInfo);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static boolean setPresence(int examId, boolean isTeacherSigned, ArrayList<String> presentStudents){
        try {

            return true;
        } catch (Exception e){
            return false;
        }
    }
}
