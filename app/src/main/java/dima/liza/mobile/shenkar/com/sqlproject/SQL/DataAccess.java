package dima.liza.mobile.shenkar.com.sqlproject.SQL;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.List;

import dima.liza.mobile.shenkar.com.sqlproject.Course;
import dima.liza.mobile.shenkar.com.sqlproject.Grade;
import dima.liza.mobile.shenkar.com.sqlproject.Lecture;
import dima.liza.mobile.shenkar.com.sqlproject.Student;

/**
 * Created by Girya on 12/29/2015.
 */
public class DataAccess implements  iDataAccess {
    private static final String TAG = "SQL_DATA_ACCESS";
    SQLiteDatabase database;
    private static DataAccess instance;
    private Context context;
    private DBHelper dbHelper;
    /*
    private String[] taskColumns = { TaskDbContract.TaskEntry._ID,TaskDbContract.TaskEntry.COLUMN_TASK_STR,
            TaskDbContract.TaskEntry.COLUMN_TASK_DATA_STR,TaskDbContract.TaskEntry.COLUMN_TASK_TIME_STR};
    */
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
        return null;
    }

    @Override
    public List<Course> getAllCourses() {
        return null;
    }

    @Override
    public List<Lecture> getAllLecture() {
        return null;
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
