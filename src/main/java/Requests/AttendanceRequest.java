package Requests;

import Data.Attendance;
import Tools.JSONDecoder;

import java.io.IOException;

public class AttendanceRequest {
    public static Attendance getClasses() throws IOException {
        String projectsInfo = null;
        Attendance attendance = new Attendance();
        try {
            projectsInfo = HttpRequest.getRemoteData("api/attendance");
            attendance = JSONDecoder.decodeJSONReq(projectsInfo);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return attendance;
    }
}
