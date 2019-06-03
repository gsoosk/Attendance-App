package Data;

public class Student extends Person{
    private int chairNumber;
    private StudentID studentID;
    private SocialID socialID;
    private Course course;

    public Student(StudentID studentID, SocialID socialID, int chairNumber, Course course) {
        this.studentID = studentID;
        this.chairNumber = chairNumber;
        this.socialID = socialID;
        this.course = course;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public StudentID getStudentID() {
        return studentID;
    }
    public int getId(){
        return studentID.getId();
    }
    public void setStudentID(StudentID studentID) {
        this.studentID = studentID;
    }

    public int getChairNumber() {
        return chairNumber;
    }

    public void setChairNumber(int chairNumber) {
        this.chairNumber = chairNumber;
    }

    public SocialID getSocialID() {
        return socialID;
    }

    public void setSocialID(SocialID socialID) {
        this.socialID = socialID;
    }

    public String getFirstName(){
        return studentID.getFirstName();
    }
    public String getLastName(){
        return studentID.getLastName();
    }
}
