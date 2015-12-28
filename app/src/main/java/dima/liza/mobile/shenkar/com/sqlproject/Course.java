package dima.liza.mobile.shenkar.com.sqlproject;

/**
 * Created by Girya on 12/28/2015.
 */
public class Course {
    private int courseId;
    private String courseName;
    private String semester;
    private int year;
    private int lectureId;

    public Course(int courseId, String courseName, String semester, int year, int lectureId) {
        this.courseId = courseId;
        this.courseName = courseName;
        this.semester = semester;
        this.year = year;
        this.lectureId = lectureId;
    }

    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getSemester() {
        return semester;
    }

    public void setSemester(String semester) {
        this.semester = semester;
    }

    public int getLectureId() {
        return lectureId;
    }

    public void setLectureId(int lectureId) {
        this.lectureId = lectureId;
    }
}
/*
CourseId int NOT NULL PRIMARY KEY,
CourseName varchar(255),
Semester varchar(255),
Year int,
LecturerId int
 */