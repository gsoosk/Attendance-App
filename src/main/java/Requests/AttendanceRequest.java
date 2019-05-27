package Requests;

import Data.Attendance;
import Tools.JSONDecoder;

import java.io.IOException;
import java.util.ArrayList;

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
    public static boolean setPresence(int examId, boolean isTeacherSigned, ArrayList<String> presentStudents){
        try {
            StringBuilder data = new StringBuilder("{ \"exam_id\" : " + examId + " ,\n" +
                    "\"is_teacher_signed\" : " + (isTeacherSigned ? "true" : "false") + " ,\n" +
                    "\"present_students_list\" : [");
            for (String sid :
                    presentStudents) {
                data.append(" ").append(sid).append(",");
            }
            data.append("]\n}");
            HttpRequest.setRemoteData("api/attendance", data.toString());
            return true;
        } catch (Exception e){
            return false;
        }
    }
}
