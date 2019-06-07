package Tools;

import Data.*;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

public class JSONDecoder {
    static public AttendanceData decodeJSONReq(String data) throws IOException {
        AttendanceData attendanceData = new AttendanceData();
        JSONObject req = new JSONObject(data);
        attendanceData.setStatus(req.getInt("status"));
        attendanceData.setDate(req.getString("date"));
        attendanceData.setClasses(extractClasses(req));
        return attendanceData;
    }

    static private ArrayList<UTClass> extractClasses(JSONObject req) {
        ArrayList<UTClass> classes = new ArrayList<UTClass>();
        JSONArray classesToProcess = req.getJSONArray("classes");
        for(int i = 0; i < classesToProcess.length(); i++) {
            classes.add(decodeJSONToUTClass(classesToProcess.getJSONObject(i)));
        }
        return classes;
    }

    static private UTClass decodeJSONToUTClass(JSONObject info) {
        int examId = info.getInt("exam_id");
        int roomNumber = info.getInt("room_number");
        String courseName = info.getString("course_name");
        String startAt = info.getString("start_at");
        String endAt = info.getString("end_at");
        Professor professor = decodeJSONToProfessor(info.getJSONObject("professor"));
        ArrayList<Student> students = decodeJSONToStudents(info.getJSONArray("students"), courseName, examId);

        return new UTClass(examId, roomNumber, courseName, startAt, endAt, professor, students);
    }

    private static ArrayList<Student> decodeJSONToStudents(JSONArray info, String courseName, int examId) {
        ArrayList <Student> students = new ArrayList<Student>();
        Course course = new Course(examId, courseName);
        for(int i = 0; i < info.length(); i++) {
            JSONObject newInfo = info.getJSONObject(i);
            String firstName = newInfo.getString("first_name");
            String lastName = newInfo.getString("last_name");
            int id = newInfo.getInt("id");
            SocialID socialID = new SocialID(firstName, lastName, "");
            StudentID studentID = new StudentID(firstName, lastName, id);
            int chairNumber = newInfo.getInt("chair_number");
            students.add(new Student(studentID, socialID, chairNumber));
        }
        return students;
    }

    private static Professor decodeJSONToProfessor(JSONObject info) {
        String firstName = info.getString("first_name");
        String lastName = info.getString("last_name");
        String id = info.getString("id");
        return new Professor(firstName, lastName, id);
    }


}
