package dima.liza.mobile.shenkar.com.sqlproject.SQL;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by Girya on 12/29/2015.
 */
public class DBHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "sqlProject.db";

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    private static final String TAG = "SQL_PROJECT";
    @Override
    public void onCreate(SQLiteDatabase db) {

        try {
            final String SQL_CREATE_LECTURE_TABLE = "CREATE TABLE "
                    + DbContract.LectureEntry.TABLE_NAME + " (" + DbContract.LectureEntry.COLUMN_LECTURE_ID
                    + " INTEGER PRIMARY KEY," + DbContract.LectureEntry.COLUMN_ADDRESS + ","+DbContract.LectureEntry.COLUMN_FIRST_NAME
                    +","+ DbContract.LectureEntry.COLUMN_LAST_NAME
                    + " TEXT NOT NULL  UNIQUE ON CONFLICT REPLACE)";

            final String SQL_CREATE_STUDENT_TABLE = "CREATE TABLE "
                    + DbContract.StudentEntry.TABLE_NAME + " (" + DbContract.StudentEntry.COLUMN_STUDENT_ID
                    + " INTEGER PRIMARY KEY," + DbContract.StudentEntry.COLUMN_ADDRESS + ","+DbContract.StudentEntry.COLUMN_FIRST_NAME
                    +","+ DbContract.StudentEntry.COLUMN_LAST_NAME +","+DbContract.StudentEntry.COLUMN_DATE_OF_BIRTH
                    + " TEXT NOT NULL  UNIQUE ON CONFLICT REPLACE)";

            final String SQL_CREATE_COURSE_TABLE = "CREATE TABLE "
                    + DbContract.CourseEntry.TABLE_NAME + " (" + DbContract.CourseEntry.COLUMN_COURSE_ID
                    + " INTEGER PRIMARY KEY," + DbContract.CourseEntry.COLUMN_COURSE_NAME + ","+DbContract.CourseEntry.COLUMN_LECTURE_ID
                    +","+ DbContract.CourseEntry.COLUMN_SEMESTER
                    + " TEXT NOT NULL  UNIQUE ON CONFLICT REPLACE)";

            final String SQL_CREATE_GRADES_TABLE = "CREATE TABLE "
                    + DbContract.GradeEntry.TABLE_NAME + " (" + DbContract.GradeEntry.COLUMN_LECTURE_ID
                     + DbContract.GradeEntry.COLUMN_STUDENT_ID + ","+DbContract.GradeEntry.COLUMN_GRADE
                    + " TEXT NOT NULL  UNIQUE ON CONFLICT REPLACE)";

            db.execSQL(SQL_CREATE_LECTURE_TABLE);
            db.execSQL(SQL_CREATE_STUDENT_TABLE);
            db.execSQL(SQL_CREATE_COURSE_TABLE);
            db.execSQL(SQL_CREATE_GRADES_TABLE);
            Log.d(TAG,SQL_CREATE_LECTURE_TABLE);
            Log.d(TAG,SQL_CREATE_STUDENT_TABLE);
            Log.d(TAG,SQL_CREATE_COURSE_TABLE);
            Log.d(TAG,SQL_CREATE_GRADES_TABLE);

        }
        catch (SQLException e){
            Log.d(TAG,"Exception:",e);
        }
        createDefaultData();
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " +DbContract.CourseEntry.TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " +DbContract.GradeEntry.TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " +DbContract.LectureEntry.TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " +DbContract.StudentEntry.TABLE_NAME);
        onCreate(db);
    }
    private void createDefaultData() {

    }
}
