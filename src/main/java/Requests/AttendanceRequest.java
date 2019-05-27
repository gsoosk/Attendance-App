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
