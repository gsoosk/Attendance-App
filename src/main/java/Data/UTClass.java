package Data;

import java.util.ArrayList;

public class UTClass {
    private int examId;
    private int roomNumber;
    private String courseName;
    private String startAt;
    private String endAt;
    private Professor professor;
    private ArrayList<Student> students;

    public UTClass(int examId, int roomNumber, String courseName, String startAt, String endAt, Professor professor, ArrayList<Student> students) {
        this.examId = examId;
        this.roomNumber = roomNumber;
        this.courseName = courseName;
        this.startAt = startAt;
        this.endAt = endAt;
        this.professor = professor;
        this.students = students;
    }

    public int getExamId() {
        return examId;
    }

    public void setExamId(int examId) {
        this.examId = examId;
    }

    public int getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(int roomNumber) {
        this.roomNumber = roomNumber;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getStartAt() {
        return startAt;
    }

    public void setStartAt(String startAt) {
        this.startAt = startAt;
    }

    public String getEndAt() {
        return endAt;
    }

    public void setEndAt(String endAt) {
        this.endAt = endAt;
    }

    public Professor getProfessor() {
        return professor;
    }

    public void setProfessor(Professor professor) {
        this.professor = professor;
    }

    public ArrayList<Student> getStudents() {
        return students;
    }

    public void setStudents(ArrayList<Student> students) {
        this.students = students;
    }
}
