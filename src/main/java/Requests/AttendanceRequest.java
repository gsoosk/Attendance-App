package Requests;

import Data.AttendanceData;
import Tools.JSONDecoder;

import java.io.IOException;
import java.util.ArrayList;

public class AttendanceRequest {
    public static AttendanceData getClasses() {
        String classesInfo = null;
        AttendanceData attendanceData = new AttendanceData();
        try {
            classesInfo = HttpRequest.getRemoteData("api/attendance");
            attendanceData = JSONDecoder.decodeJSONReq(classesInfo);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return attendanceData;
    }
    public static boolean setPresence(String data){
        try {
            HttpRequest.setRemoteData("api/attendance", data);
            return true;
        } catch (Exception e){
            return false;
        }
    }
    public static String getSendingData(int examId, boolean isTeacherSigned, ArrayList<Integer> presentStudents){
        StringBuilder data = new StringBuilder("{ \"exam_id\": " + examId + " ,\n" +
                "\"is_teacher_signed\": " + (isTeacherSigned ? "\"true\"": "\"false\"") + " ,\n" +
                "\"present_students_list\": [");
        for (Integer sid :
                presentStudents) {
            data.append(" ").append(sid).append(",");
        }
        data.append("]\n}");
        return data.toString();
    }
}
