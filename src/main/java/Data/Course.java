package Data;

public class Course {
    private MainCourse mainCourse;
    private int examId;
    Course(int id, String courseName)
    {
        mainCourse = new MainCourse();
        mainCourse.setName(courseName);
        this.examId = id;
    }

    public int getExamId() {
        return examId;
    }

    public void setExamId(int examId) {
        this.examId = examId;
    }

    public String getCourseName()
    {
        return mainCourse.getName();
    }
}
