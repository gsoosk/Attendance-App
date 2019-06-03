package Data;

public class Student {
    private int chairNumber;
    private StudentID studentID;
    private SocialID socialID;

    public Student(StudentID studentID, SocialID socialID, int chairNumber) {
        this.studentID = studentID;
        this.chairNumber = chairNumber;
        this.socialID = socialID;
    }

    public StudentID getStudentID() {
        return studentID;
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
}
