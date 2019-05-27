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
}
