package dima.liza.mobile.shenkar.com.sqlproject.SQL;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import dima.liza.mobile.shenkar.com.sqlproject.Course;
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
        //Log.d(TAG,"getTaskFromCursor start");
        int studentId = cursor.getInt(cursor.getColumnIndex(DbContract.StudentEntry.COLUMN_STUDENT_ID));
        String firstName = cursor.getString(cursor.getColumnIndex( DbContract.StudentEntry.COLUMN_FIRST_NAME));
        String lastName = cursor.getString(cursor.getColumnIndex(DbContract.StudentEntry.COLUMN_LAST_NAME));
        String address = cursor.getString(cursor.getColumnIndex( DbContract.StudentEntry.COLUMN_ADDRESS));
        String dateOfBirth = cursor.getString(cursor.getColumnIndex( DbContract.StudentEntry.COLUMN_DATE_OF_BIRTH));
        Student student = new Student(studentId,firstName,lastName,address, dateOfBirth);
        //Log.d(TAG,"getTaskFromCursor end");
        return student;
    }

    @Override
    public List<Course> getAllCourses() {
        return null;
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
            // java.util.Collections.sort(taskList);
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
        return null;    //warning
    }

    private Lecture getLectureFromCursor(Cursor cursor) {
        //Log.d(TAG,"getTaskFromCursor start");
        int studentId = cursor.getInt(cursor.getColumnIndex(DbContract.LectureEntry.COLUMN_LECTURE_ID));
        String firstName = cursor.getString(cursor.getColumnIndex( DbContract.LectureEntry.COLUMN_FIRST_NAME));
        String lastName = cursor.getString(cursor.getColumnIndex(DbContract.LectureEntry.COLUMN_LAST_NAME));
        String address = cursor.getString(cursor.getColumnIndex( DbContract.LectureEntry.COLUMN_ADDRESS));
        Lecture lecture = new Lecture(studentId,firstName,lastName,address);
        //Log.d(TAG,"getTaskFromCursor end");
        return lecture;
    }

    @Override
    public List<Grade> getAllGrades() {
        return null;
    }

    @Override
    public boolean addStudent(Student student) {
        return false;
    }

    @Override
    public boolean addLecture(Lecture lecture) {
        return false;
    }

    @Override
    public boolean addCourse(Course course) {
        return false;
    }

    @Override
    public boolean addGrade(Grade grade) {
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
}