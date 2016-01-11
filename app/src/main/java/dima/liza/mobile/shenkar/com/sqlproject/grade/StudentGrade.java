package dima.liza.mobile.shenkar.com.sqlproject.grade;

/**
 * Created by Girya on 1/5/2016.
 */
public class StudentGrade {
    private String lastName;
    private String firstName;
    private int studentId;
    private String courseName;
    private int grades;

    public StudentGrade(String lastName, String firstName, int studentId, String courseName,int grades) {
        this.lastName = lastName;
        this.firstName = firstName;
        this.studentId = studentId;
        this.courseName = courseName;
        this.grades = grades;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public int getGrades() {
        return grades;
    }

    public void setGrades(int grades) {
        this.grades = grades;
    }
}
