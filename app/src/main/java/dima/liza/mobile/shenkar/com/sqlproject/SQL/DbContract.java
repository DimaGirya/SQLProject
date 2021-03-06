package dima.liza.mobile.shenkar.com.sqlproject.SQL;

import android.provider.BaseColumns;

/**
 * Created by Girya on 12/29/2015.
 */
public class DbContract {
    /*
        CREATE TABLE Lecture
        (
        LectureID int NOT NULL PRIMARY KEY,
        LastName varchar(255) NOT NULL,
        FirstName varchar(255) NOT NULL,
        Address varchar(255)
        );
    */

    public  static final class LectureEntry{
        public static final String TABLE_NAME = "Lectures";
        public static final String  COLUMN_LECTURE_ID = "LectureId";
        public static final String  COLUMN_LAST_NAME = "LastName";
        public static final String  COLUMN_FIRST_NAME = "FirstName";
        public static final String  COLUMN_ADDRESS = "Address";
    }

    /*
        CREATE TABLE Students
        (
        StudentId int NOT NULL PRIMARY KEY,
        LastName varchar(255) NOT NULL,
        FirstName varchar(255) NOT NULL,
        Address varchar(255),
        DateOfBirth varchar(255)
        );
     */

    public  static final class StudentEntry{
        public static final String TABLE_NAME = "Students";
        public static final String  COLUMN_STUDENT_ID = "StudentId";
        public static final String  COLUMN_LAST_NAME = "LastName";
        public static final String  COLUMN_FIRST_NAME = "FirstName";
        public static final String  COLUMN_ADDRESS = "Address";
        public static final String  COLUMN_DATE_OF_BIRTH = "DateOfBirth";
    }

    /*
    CREATE TABLE Courses
    (
    CourseId int NOT NULL PRIMARY KEY,
    CourseName varchar(255),
    Semester varchar(255),
    Year int,
    LecturerId int
    );
     */

     public  static final class CourseEntry{
        public static final String  TABLE_NAME = "Courses";
        public static final String  COLUMN_COURSE_ID = "CourseId";
        public static final String  COLUMN_COURSE_NAME = "CourseName";
        public static final String  COLUMN_SEMESTER = "Semester"; //Semester
        public static final String  COLUMN_LECTURE_ID = "LecturerId";
        public static final String  COLUMN_YEAR = "Year" ;
    }


/*
    CREATE TABLE Grades
            (
                    StudentId int,
                    CourseId int,
                    Grade int,
                    PRIMARY KEY(StudentId,CourseId)
            );
*/
    public  static final class GradeEntry {
        public static final String TABLE_NAME = "Grades";
        public static final String  COLUMN_STUDENT_ID = "StudentId";
         public static final String  COLUMN_COURSE_ID = "CourseId";
        public static final String  COLUMN_GRADE = "Grade";
    }
}
