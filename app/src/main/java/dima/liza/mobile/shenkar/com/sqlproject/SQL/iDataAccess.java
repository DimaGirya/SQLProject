package dima.liza.mobile.shenkar.com.sqlproject.SQL;

import java.util.List;

import dima.liza.mobile.shenkar.com.sqlproject.grade.StudentGrade;
import dima.liza.mobile.shenkar.com.sqlproject.courses.Course;
import dima.liza.mobile.shenkar.com.sqlproject.grade.Grade;
import dima.liza.mobile.shenkar.com.sqlproject.lectures.Lecture;
import dima.liza.mobile.shenkar.com.sqlproject.students.Student;

/**
 * Created by Girya on 12/29/2015.
 */
public interface iDataAccess {
    List<Student> getAllStudents();
    List<Course> getAllCourses();
    List<Lecture> getAllLecture();


    boolean addStudent(Student student);
    boolean addLecture(Lecture lecture);
    boolean addCourse(Course course);
    boolean addGrade(Grade grade);

    boolean removeStudent(int studentId);
    boolean removeLecture(int lectureId);
    boolean removeCourse(int courseId);
    boolean removeGrade(int studentId,int courseId);

    List<StudentGrade> getStudentGrades(String studentId);

    Course getCourseById(String courseId);
    Student getStudentById(String studentId);
    Lecture getLectureById(String lectureId);
}
