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
            String data = "{ \"exam_id\" : " + examId + " ,\n" +
                    "\"is_teacher_signed\" : " + (isTeacherSigned ? "true" : "false") + " ,\n" +
                    "\"present_students_list\" : [";
            for (String sid :
                    presentStudents) {
                data = data + " " + sid + ",";
            }
            data += "]\n}";
            HttpRequest.setRemoteData("api/attendance", data);
            return true;
        } catch (Exception e){
            return false;
        }
    }
}
