package dima.liza.mobile.shenkar.com.sqlproject.SQL;

import java.util.List;

import dima.liza.mobile.shenkar.com.sqlproject.students.grade.StudentGrade;
import dima.liza.mobile.shenkar.com.sqlproject.courses.Course;
import dima.liza.mobile.shenkar.com.sqlproject.Grade;
import dima.liza.mobile.shenkar.com.sqlproject.lectures.Lecture;
import dima.liza.mobile.shenkar.com.sqlproject.students.Student;

/**
 * Created by Girya on 12/29/2015.
 */
public interface iDataAccess {
    List<Student> getAllStudents();
    List<Course> getAllCourses();
    List<Lecture> getAllLecture();
    List<Grade> getAllGrades(); // need?

    boolean addStudent(Student student);
    boolean addLecture(Lecture lecture);
    boolean addCourse(Course course);
    boolean addGrade(Grade grade);

    boolean removeStudent(Student student);
    boolean removeLecture(Lecture lecture);
    boolean removeCourse(Course course);
    boolean removeGrade(Grade grade);


    List<StudentGrade> getStudentGrades(String studentId);
}
