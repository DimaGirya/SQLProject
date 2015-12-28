package dima.liza.mobile.shenkar.com.sqlproject;

/**
 * Created by Girya on 12/28/2015.
 */
public class Grade {
    private int studentId;
    private int courseId;
    private int grade;

    public Grade(int studentId, int courseId, int grade) {
        this.studentId = studentId;
        this.courseId = courseId;
        this.grade = grade;
    }

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    public int getGrade() {
        return grade;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }
}
/*
StudentId int,
CourseId int,
Grade int
 */