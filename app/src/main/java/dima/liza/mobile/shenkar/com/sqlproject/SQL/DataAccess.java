package dima.liza.mobile.shenkar.com.sqlproject.SQL;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import dima.liza.mobile.shenkar.com.sqlproject.students.grade.StudentGrade;
import dima.liza.mobile.shenkar.com.sqlproject.courses.Course;
import dima.liza.mobile.shenkar.com.sqlproject.Grade;
import dima.liza.mobile.shenkar.com.sqlproject.lectures.Lecture;
import dima.liza.mobile.shenkar.com.sqlproject.students.Student;

/**
 * Created by Girya on 12/29/2015.
 */
public class DataAccess implements  iDataAccess {
    private static final String TAG = "SQL_DATA_ACCESS";
    SQLiteDatabase database;
    private static DataAccess instance;
    private Context context;
    private DBHelper dbHelper;
    private String[] studentColumns = { DbContract.StudentEntry.COLUMN_STUDENT_ID, DbContract.StudentEntry.COLUMN_FIRST_NAME,
            DbContract.StudentEntry.COLUMN_LAST_NAME, DbContract.StudentEntry.COLUMN_ADDRESS,
            DbContract.StudentEntry.COLUMN_DATE_OF_BIRTH};
    private String[] lectureColumns = { DbContract.LectureEntry.COLUMN_LECTURE_ID, DbContract.LectureEntry.COLUMN_FIRST_NAME,
            DbContract.LectureEntry.COLUMN_LAST_NAME, DbContract.LectureEntry.COLUMN_ADDRESS};
    private String[] courseColumns = { DbContract.CourseEntry.COLUMN_COURSE_ID, DbContract.CourseEntry.COLUMN_COURSE_NAME,
            DbContract.CourseEntry.COLUMN_SEMESTER, DbContract.CourseEntry.COLUMN_YEAR,DbContract.CourseEntry.COLUMN_LECTURE_ID};

    private DataAccess(Context context) {	//private constructor(singleton)
        try {
            this.context = context;
            dbHelper = new DBHelper(this.context);
        }
        catch (Exception e){
            Log.d(TAG, "Error in DataAccess:", e);
        }
    }

    public static DataAccess getInstatnce(Context context) {
        if (instance == null)
            instance = new DataAccess(context);
        return instance;
    }

    @Override
    public List<Student> getAllStudents() {

        try {
            database = dbHelper.getReadableDatabase();
            List<Student> studentList = new ArrayList<Student>();

            Cursor cursor = database.query(DbContract.StudentEntry.TABLE_NAME, studentColumns, null, null, null, null, null);

            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                Student student = getStudentFromCursor(cursor);
                studentList.add(student);
                cursor.moveToNext();
            }
            // java.util.Collections.sort(taskList);
            cursor.close();
            return studentList;
        }
        catch (Exception  e){
            Log.d(TAG, "Error,Exception:",e);
        }
        finally {
            if (database != null) {
                database.close();
            }
        }
        return null;    //warning
    }

    private Student getStudentFromCursor(Cursor cursor) {
        int studentId = cursor.getInt(cursor.getColumnIndex(DbContract.StudentEntry.COLUMN_STUDENT_ID));
        String firstName = cursor.getString(cursor.getColumnIndex( DbContract.StudentEntry.COLUMN_FIRST_NAME));
        String lastName = cursor.getString(cursor.getColumnIndex(DbContract.StudentEntry.COLUMN_LAST_NAME));
        String address = cursor.getString(cursor.getColumnIndex( DbContract.StudentEntry.COLUMN_ADDRESS));
        String dateOfBirth = cursor.getString(cursor.getColumnIndex( DbContract.StudentEntry.COLUMN_DATE_OF_BIRTH));
        Student student = new Student(studentId,firstName,lastName,address, dateOfBirth);
        return student;
    }

    @Override
    public List<Course> getAllCourses() {

        try {
            database = dbHelper.getReadableDatabase();
            List<Course> courseList = new ArrayList<Course>();

            Cursor cursor = database.query(DbContract.CourseEntry.TABLE_NAME, courseColumns, null, null, null, null, null);

            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                Course course = getCurseFromCursor(cursor);
                courseList.add(course);
                cursor.moveToNext();
            }
            cursor.close();
            return courseList;
        }
        catch (Exception  e){
            Log.d(TAG, "Error,Exception:",e);
        }
        finally {
            if (database != null) {
                database.close();
            }
        }
        return null;
    }

    private Course getCurseFromCursor(Cursor cursor) {
        int courseId = cursor.getInt(cursor.getColumnIndex(DbContract.CourseEntry.COLUMN_COURSE_ID));
        String courseName = cursor.getString(cursor.getColumnIndex(DbContract.CourseEntry.COLUMN_COURSE_NAME));
        String courseSemester = cursor.getString(cursor.getColumnIndex( DbContract.CourseEntry.COLUMN_SEMESTER));
        int courseYear =  cursor.getInt(cursor.getColumnIndex(DbContract.CourseEntry.COLUMN_YEAR));
        int courseLectureId =  cursor.getInt(cursor.getColumnIndex(DbContract.CourseEntry.COLUMN_LECTURE_ID));
        return new Course(courseId,courseName,courseSemester,courseYear,courseLectureId);
    }

    @Override
    public List<Lecture> getAllLecture() {
        try {
            database = dbHelper.getReadableDatabase();
            List<Lecture> lectureList = new ArrayList<Lecture>();

            Cursor cursor = database.query(DbContract.LectureEntry.TABLE_NAME, lectureColumns, null, null, null, null, null);

            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                Lecture lecture = getLectureFromCursor(cursor);
                lectureList.add(lecture);
                cursor.moveToNext();
            }
            cursor.close();
            return lectureList;
        }
        catch (Exception  e){
            Log.d(TAG, "Error,Exception:",e);
        }
        finally {
            if (database != null) {
                database.close();
            }
        }
        return null;
    }

    private Lecture getLectureFromCursor(Cursor cursor) {
        int studentId = cursor.getInt(cursor.getColumnIndex(DbContract.LectureEntry.COLUMN_LECTURE_ID));
        String firstName = cursor.getString(cursor.getColumnIndex( DbContract.LectureEntry.COLUMN_FIRST_NAME));
        String lastName = cursor.getString(cursor.getColumnIndex(DbContract.LectureEntry.COLUMN_LAST_NAME));
        String address = cursor.getString(cursor.getColumnIndex( DbContract.LectureEntry.COLUMN_ADDRESS));
        Lecture lecture = new Lecture(studentId,firstName,lastName,address);
        return lecture;
    }

    @Override
    public List<Grade> getAllGrades() {
        return null;
    }

    @Override
    public boolean addStudent(Student student) {
        ContentValues content = new ContentValues();
        content.put(DbContract.StudentEntry.COLUMN_STUDENT_ID, student.getStudentId());
        content.put(DbContract.StudentEntry.COLUMN_FIRST_NAME, student.getFirstName());
        content.put(DbContract.StudentEntry.COLUMN_LAST_NAME, student.getLastName());
        content.put(DbContract.StudentEntry.COLUMN_ADDRESS, student.getAddress());
        content.put(DbContract.StudentEntry.COLUMN_DATE_OF_BIRTH, student.getDateOfBirth());
        try {
            database = dbHelper.getReadableDatabase();
            if(database.insert(DbContract.StudentEntry.TABLE_NAME,null,content)==-1){
                return false;
            }
            else{
                return true;
            }
        } catch (Exception e) {
            Log.e(TAG,"Exception!",e);
        } finally {
            if (database != null) {
                database.close();
            }
        }
        return false;
    }

    @Override
    public boolean addLecture(Lecture lecture) {
        ContentValues content = new ContentValues();
        content.put(DbContract.LectureEntry.COLUMN_LECTURE_ID, lecture.getLectureId());
        content.put(DbContract.LectureEntry.COLUMN_FIRST_NAME, lecture.getFirstName());
        content.put(DbContract.LectureEntry.COLUMN_LAST_NAME, lecture.getLastName());
        content.put(DbContract.LectureEntry.COLUMN_ADDRESS,lecture.getAddress());
        try {
            database = dbHelper.getReadableDatabase();
            if(database.insert(DbContract.LectureEntry.TABLE_NAME,null,content)==-1){
                return false;
            }
            else{
                return true;
            }
        } catch (Exception e) {
            Log.e(TAG,"Exception!",e);
        } finally {
            if (database != null) {
                database.close();
            }
        }
        return false;
    }

    @Override
    public boolean addCourse(Course course) {
        return false;
    }

    @Override
    public boolean addGrade(Grade grade) {
        ContentValues content = new ContentValues();
        content.put(DbContract.GradeEntry.COLUMN_COURSE_ID, grade.getCourseId());
        content.put(DbContract.GradeEntry.COLUMN_STUDENT_ID, grade.getStudentId());
        content.put(DbContract.GradeEntry.COLUMN_GRADE, grade.getGrade());
        try {
            database = dbHelper.getReadableDatabase();
            if(database.insert(DbContract.GradeEntry.TABLE_NAME,null,content)==-1){
                return false;
            }
            else{
                return true;
            }
        } catch (Exception e) {
            Log.e(TAG,"Exception!",e);
        } finally {
            if (database != null) {
                database.close();
            }
        }
        return false;
    }

    @Override
    public boolean removeStudent(Student student) {
        return false;
    }

    @Override
    public boolean removeLecture(Lecture lecture) {
        return false;
    }

    @Override
    public boolean removeCourse(Course course) {
        return false;
    }

    @Override
    public boolean removeGrade(Grade grade) {
        return false;
    }

    @Override
    public List<StudentGrade> getStudentGrades(String studentId) {
        try {
            database = dbHelper.getReadableDatabase();
            List<StudentGrade> studentGrades = new ArrayList<StudentGrade>();
    String sqlJoin  = "SELECT Students.LastName,Students.FirstName,Grades.StudentId, Grades.Grade,Courses.CourseName "
             + "FROM Students "
             + "INNER JOIN Grades "
             + "ON Grades.StudentId = Students.StudentID "
             +  "INNER JOIN Courses "
             +  "ON Grades.CourseId = Courses.CourseID "
             +  "WHERE Students.StudentID = ? ";
            String  selectionArgs[] = new String[1];
            selectionArgs[0] = studentId;
            Log.d(TAG,sqlJoin);
            Cursor cursor =  database.rawQuery(sqlJoin,selectionArgs);  // error !!! exeption
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                StudentGrade studentGrade = getStudentGradesFromCursor(cursor);
                studentGrades.add(studentGrade);
                cursor.moveToNext();
            }
            cursor.close();
            return studentGrades;
        }
        catch (Exception  e){
            Log.d(TAG, "Error,Exception:",e);
        }
        finally {
            if (database != null) {
                database.close();
            }
        }
        return null;
    }

    @Override
    public Course getCourseById(String courseId) {
        try {
            database = dbHelper.getReadableDatabase();
            String sqlSelect = "SELECT " + DbContract.CourseEntry.COLUMN_COURSE_ID + ","
                    + DbContract.CourseEntry.COLUMN_COURSE_NAME + "," + DbContract.CourseEntry.COLUMN_LECTURE_ID
                    + "," + DbContract.CourseEntry.COLUMN_SEMESTER + "," + DbContract.CourseEntry.COLUMN_YEAR
                    + " FROM "+ DbContract.CourseEntry.TABLE_NAME
                    + " WHERE " + DbContract.CourseEntry.COLUMN_COURSE_ID + "= ?" ;
            String  selectionArgs[] = new String[1];
            selectionArgs[0] = courseId;
            Log.d(TAG,sqlSelect+ selectionArgs[0]);
            Cursor cursor =  database.rawQuery(sqlSelect,selectionArgs);
            cursor.moveToFirst();
            Log.d(TAG," cursor.getCount():"+ cursor.getCount());
            if(cursor.getCount()!=1){
                Log.d(TAG, "Error!! more than 1 course with id or no such course:"+courseId + " cursor.getCount():"+cursor.getCount());
                return null;
            }
            Course course = getCurseFromCursor(cursor);
            return course;
        }
        catch (Exception  e){
            Log.d(TAG, "Error,Exception:",e);
        }
        finally {
            if (database != null) {
                database.close();
            }
        }
        return null;
    }

    @Override
    public Student getStudentById(String studentId) {
        try {
            database = dbHelper.getReadableDatabase();
            String sqlSelect = "SELECT " + DbContract.StudentEntry.COLUMN_STUDENT_ID  + ","
                    + DbContract.StudentEntry.COLUMN_FIRST_NAME + "," + DbContract.StudentEntry.COLUMN_LAST_NAME
                    + "," + DbContract.StudentEntry.COLUMN_ADDRESS + "," + DbContract.StudentEntry.COLUMN_DATE_OF_BIRTH
                    + " FROM "+ DbContract.StudentEntry.TABLE_NAME
                    + " WHERE " + DbContract.StudentEntry.COLUMN_STUDENT_ID + "= ?" ;
            String  selectionArgs[] = new String[1];
            selectionArgs[0] = studentId;
            Log.d(TAG,sqlSelect);
            Cursor cursor =  database.rawQuery(sqlSelect,selectionArgs);
            cursor.moveToFirst();
            if(cursor.getCount()!=1){
                Log.d(TAG, "Error!! more than 1 course with id or no such course:"+studentId + " cursor.getCount():"+cursor.getCount());
                return null;
            }
            Student student = getStudentFromCursor(cursor);
            return student;
        }
        catch (Exception  e){
            Log.d(TAG, "Error,Exception:",e);
        }
        finally {
            if (database != null) {
                database.close();
            }
        }
        return null;
    }

    @Override
    public Lecture getLectureById(String lectureId) {
        try {
            database = dbHelper.getReadableDatabase();
            String sqlSelect = "SELECT " + DbContract.LectureEntry.COLUMN_LECTURE_ID  + ","
                    + DbContract.LectureEntry.COLUMN_LAST_NAME + "," + DbContract.LectureEntry.COLUMN_FIRST_NAME
                    + "," + DbContract.LectureEntry.COLUMN_ADDRESS
                    + " FROM "+ DbContract.LectureEntry.TABLE_NAME
                    + " WHERE " + DbContract.LectureEntry.COLUMN_LECTURE_ID + "= ?" ;
            String  selectionArgs[] = new String[1];
            selectionArgs[0] = lectureId;
            Log.d(TAG,sqlSelect);
            Cursor cursor =  database.rawQuery(sqlSelect,selectionArgs);
            cursor.moveToFirst();
            if(cursor.getCount()!=1){
                Log.d(TAG, "Error!! more than 1 course with id or no such course:"+lectureId + " cursor.getCount():"+cursor.getCount());
                return null;
            }
            Lecture lecture = getLectureFromCursor(cursor);
            return lecture;
        }
        catch (Exception  e){
            Log.d(TAG, "Error,Exception:",e);
        }
        finally {
            if (database != null) {
                database.close();
            }
        }
        return null;
    }

    private StudentGrade getStudentGradesFromCursor(Cursor cursor) {
        int studentId = Integer.parseInt(cursor.getString(cursor.getColumnIndex("Grades.StudentId")));
        String firstName = cursor.getString(cursor.getColumnIndex("Students.FirstName"));
        String lastName = cursor.getString(cursor.getColumnIndex("Students.LastName"));
        String courseName = cursor.getString(cursor.getColumnIndex( "Courses.CourseName"));
        int grades = Integer.parseInt(cursor.getString(cursor.getColumnIndex("Grades.Grade")));
        StudentGrade studentGrade = new StudentGrade(lastName,firstName,studentId ,courseName,grades);
        return studentGrade;
    }
}
