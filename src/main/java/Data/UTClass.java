package Data;

import java.util.ArrayList;

public class UTClass {
    private UTClassDate date;
    private UTClassLocation location;
    private Course course;
    private Professor professor;
    private ArrayList<Student> students;

    public UTClass(int examId, int roomNumber, String courseName, String startAt, String endAt, Professor professor, ArrayList<Student> students) {
        this.course = new Course(examId, courseName);
        this.location = new UTClassLocation();
        this.location.setRoomNumber(roomNumber);
        this.date = new UTClassDate();
        this.date.setStartAt(startAt);
        this.date.setEndAt(endAt);
        this.professor = professor;
        this.students = students;
    }

    public int getExamId() {
        return this.course.getExamId();
    }

    public void setExamId(int examId) {
        this.course.setExamId(examId);
    }


    public UTClassLocation getLocation() {
        return location;
    }

    public void setLocation(UTClassLocation location) {
        this.location = location;
    }

    public String getCourseName() {
        return this.course.getCourseName();
    }

    public Course getCourse() { return course; }

    public UTClassDate getDate() {
        return date;
    }

    public void setDate(UTClassDate date) {
        this.date = date;
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
