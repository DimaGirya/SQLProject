package dima.liza.mobile.shenkar.com.sqlproject.SQL;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

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
                    + DbContract.LectureEntry.TABLE_NAME + " (" + DbContract.LectureEntry.COLUMN_LECTURE_ID  + " INTEGER PRIMARY KEY,"
                    + DbContract.LectureEntry.COLUMN_LAST_NAME + "," + DbContract.LectureEntry.COLUMN_FIRST_NAME
                    + "," + DbContract.LectureEntry.COLUMN_ADDRESS
                    + " TEXT NOT NULL)";

            final String SQL_CREATE_STUDENT_TABLE = "CREATE TABLE "
                    + DbContract.StudentEntry.TABLE_NAME + " (" + DbContract.StudentEntry.COLUMN_STUDENT_ID  + " INTEGER PRIMARY KEY,"
                    + DbContract.StudentEntry.COLUMN_FIRST_NAME + "," + DbContract.StudentEntry.COLUMN_LAST_NAME
                    + "," + DbContract.StudentEntry.COLUMN_ADDRESS + "," + DbContract.StudentEntry.COLUMN_DATE_OF_BIRTH
                    + " TEXT NOT NULL)";

            final String SQL_CREATE_COURSE_TABLE = "CREATE TABLE "
                    + DbContract.CourseEntry.TABLE_NAME + " (" + DbContract.CourseEntry.COLUMN_COURSE_ID + " INTEGER PRIMARY KEY,"
                    + DbContract.CourseEntry.COLUMN_COURSE_NAME + "," + DbContract.CourseEntry.COLUMN_LECTURE_ID + " INTEGER"
                    + "," + DbContract.CourseEntry.COLUMN_SEMESTER + "," + DbContract.CourseEntry.COLUMN_YEAR
                    + " INTEGER TEXT NOT NULL)";

            final String SQL_CREATE_GRADES_TABLE = "CREATE TABLE "
                    + DbContract.GradeEntry.TABLE_NAME + " ("+ DbContract.GradeEntry.COLUMN_COURSE_ID  + " INTEGER NOT NULL,"
                    + DbContract.GradeEntry.COLUMN_STUDENT_ID + " INTEGER NOT NULL," + DbContract.GradeEntry.COLUMN_GRADE
                    + " INTEGER NOT NULL,"
                    + "PRIMARY KEY  (" +DbContract.GradeEntry.COLUMN_COURSE_ID  +","+  DbContract.GradeEntry.COLUMN_STUDENT_ID +") )";

            db.execSQL(SQL_CREATE_LECTURE_TABLE);
            db.execSQL(SQL_CREATE_STUDENT_TABLE);
            db.execSQL(SQL_CREATE_COURSE_TABLE);
            db.execSQL(SQL_CREATE_GRADES_TABLE);
            Log.d(TAG, SQL_CREATE_LECTURE_TABLE);
            Log.d(TAG, SQL_CREATE_STUDENT_TABLE);
            Log.d(TAG, SQL_CREATE_COURSE_TABLE);
            Log.d(TAG, SQL_CREATE_GRADES_TABLE);

        } catch (SQLException e) {
            Log.d(TAG, "Exception:", e);
        }
        createDefaultData(db);
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + DbContract.CourseEntry.TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + DbContract.GradeEntry.TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + DbContract.LectureEntry.TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + DbContract.StudentEntry.TABLE_NAME);
        onCreate(db);
    }

    private void createDefaultData(SQLiteDatabase db) {

        String defaultDataStudents[] = {
                "INSERT INTO Students (StudentId, LastName, FirstName, Address, DateOfBirth) VALUES ('101','Gilman','Liza','zahal kiryat-ono','22/10/91');",
                "INSERT INTO Students (StudentId, LastName, FirstName, Address, DateOfBirth) VALUES ('102','Girya','Dima','geva netanya','14/01/90');",
                " INSERT INTO Students (StudentId, LastName, FirstName, Address, DateOfBirth) VALUES ('103','Cohen','Asi','alenby tel-aviv','01/01/89');",
                "INSERT INTO Students (StudentId, LastName, FirstName, Address, DateOfBirth) VALUES ('104','Israeli','Israel','bialik ramat-gan','22/10/91');",
                "INSERT INTO Students (StudentId, LastName, FirstName, Address, DateOfBirth) VALUES ('105','Alis','Yuval','Herzliya','01/01/80');",
                " INSERT INTO Students (StudentId, LastName, FirstName, Address, DateOfBirth) VALUES ('106','Amit','Or','Haifa','03/03/87');",
                " INSERT INTO Students (StudentId, LastName, FirstName, Address, DateOfBirth) VALUES ('107','Assulin','Almog','Tel-aviv','03/08/91');",
                "INSERT INTO Students (StudentId, LastName, FirstName, Address, DateOfBirth) VALUES ('108','Gendalman','Alexs','Netanya','05/01/93');",
                " INSERT INTO Students (StudentId, LastName, FirstName, Address, DateOfBirth) VALUES ('109','Zak','Emil','Netanya','03/08/92');",
                " INSERT INTO Students (StudentId, LastName, FirstName, Address, DateOfBirth) VALUES ('110','Kapah','Nama','Herzliya','03/08/93');"

        };
        for (int i = 0; i < defaultDataStudents.length; i++) {
            db.execSQL(defaultDataStudents[i]);
            Log.d(TAG,defaultDataStudents[i]);
        }

        String defaultDataLectures[] = {
                "INSERT INTO Lectures(LectureId, FirstName, LastName, Address) VALUES ('1001','Yigal','Hoffner','bialik ramat-gan')",
                "INSERT INTO Lectures(LectureId, FirstName, LastName, Address) VALUES  ('1002','Riva','Shalom','Ben-Gurion givataim');",
                "INSERT INTO Lectures(LectureId, FirstName, LastName, Address) VALUES ('1003','Marselo','Shichman','sokolov Hod-HasSharon')",
                "INSERT INTO Lectures(LectureId, FirstName, LastName, Address) VALUES ('1004','Yehiel','Kimhi','moria Haifa')",
                "INSERT INTO Lectures(LectureId, FirstName, LastName, Address) VALUES ('1005','Ichak','Nudler','Tel-aviv')",
                "INSERT INTO Lectures(LectureId, FirstName, LastName, Address) VALUES  ('1006','Nezer','Zidbnger','Haifa');",
                "INSERT INTO Lectures(LectureId, FirstName, LastName, Address) VALUES ('1007','Yonit','Risho','Hadera')",
                "INSERT INTO Lectures(LectureId, FirstName, LastName, Address) VALUES ('1008','Haim','Mihael','Haifa')",
                "INSERT INTO Lectures(LectureId, FirstName, LastName, Address) VALUES ('1009','Noa','Levinshten','Bne-brak')",
                "INSERT INTO Lectures(LectureId, FirstName, LastName, Address) VALUES ('1010','Amanuel','Grindar','Haifa')"
        };
        for (int i = 0; i < defaultDataLectures.length; i++) {
            db.execSQL(defaultDataLectures[i]);
            Log.d(TAG, defaultDataLectures[i]);
        }

        String defaultDataCourses[] = {
                "INSERT INTO Courses(CourseId, CourseName, Semester, Year, LecturerId) VALUES ('10001','Assembly','A','2015','1001')",
                "INSERT INTO Courses(CourseId, CourseName, Semester, Year, LecturerId) VALUES ('10002','Digital Systems','B','2015','1002')",
                "INSERT INTO Courses(CourseId, CourseName, Semester, Year, LecturerId) VALUES ('10003','C++','A','2015','1003')",
                "INSERT INTO Courses(CourseId, CourseName, Semester, Year, LecturerId) VALUES ('10004','Software Design','B','2015','1004')",
                "INSERT INTO Courses(CourseId, CourseName, Semester, Year, LecturerId) VALUES ('10005','Tikshoret','A','2015','1005')",
                "INSERT INTO Courses(CourseId, CourseName, Semester, Year, LecturerId) VALUES ('10006','Unix','B','2015','1006')",
                "INSERT INTO Courses(CourseId, CourseName, Semester, Year, LecturerId) VALUES ('10007','Web','A','2015','1007')",
                "INSERT INTO Courses(CourseId, CourseName, Semester, Year, LecturerId) VALUES ('10008','Java','B','2015','1008')",
                "INSERT INTO Courses(CourseId, CourseName, Semester, Year, LecturerId) VALUES ('10009','Formal languages','A','2015','1009')",
                "INSERT INTO Courses(CourseId, CourseName, Semester, Year, LecturerId) VALUES ('10010','Operating Systems','B','2015','1010')"
        };
        for (int i = 0; i < defaultDataCourses.length; i++) {
            db.execSQL(defaultDataCourses[i]);
            Log.d(TAG, defaultDataCourses[i]);
        }

        String defaultDataGrades[] = {
                "INSERT INTO Grades(StudentId, CourseId, Grade) VALUES ('101','10001','95')",
                "INSERT INTO Grades(StudentId, CourseId, Grade) VALUES ('101','10002','90')",
                "INSERT INTO Grades(StudentId, CourseId, Grade) VALUES ('101','10003','80')",
                "INSERT INTO Grades(StudentId, CourseId, Grade) VALUES ('101','10004','100')",
                "INSERT INTO Grades(StudentId, CourseId, Grade) VALUES ('101','10005','89')",
                "INSERT INTO Grades(StudentId, CourseId, Grade) VALUES ('101','10006','95')",
                "INSERT INTO Grades(StudentId, CourseId, Grade) VALUES ('101','10007','89')",
                "INSERT INTO Grades(StudentId, CourseId, Grade) VALUES ('101','10008','93')",
                "INSERT INTO Grades(StudentId, CourseId, Grade) VALUES ('101','10009','87')",
                "INSERT INTO Grades(StudentId, CourseId, Grade) VALUES ('101','10010','99')",

                "INSERT INTO Grades(StudentId, CourseId, Grade) VALUES ('102','10001','95')",
                "INSERT INTO Grades(StudentId, CourseId, Grade) VALUES ('102','10002','100')",
                "INSERT INTO Grades(StudentId, CourseId, Grade) VALUES ('102','10003','90')",
                "INSERT INTO Grades(StudentId, CourseId, Grade) VALUES ('102','10004','85')",

                "INSERT INTO Grades(StudentId, CourseId, Grade) VALUES ('103','10001','95')",
                "INSERT INTO Grades(StudentId, CourseId, Grade) VALUES ('103','10002','80')",
                "INSERT INTO Grades(StudentId, CourseId, Grade) VALUES ('103','10003','60')",
                "INSERT INTO Grades(StudentId, CourseId, Grade) VALUES ('103','10004','45')",

                "INSERT INTO Grades(StudentId, CourseId, Grade) VALUES ('104','10001','88')",
                "INSERT INTO Grades(StudentId, CourseId, Grade) VALUES ('104','10002','80')",
                "INSERT INTO Grades(StudentId, CourseId, Grade) VALUES ('104','10003','70')",
                "INSERT INTO Grades(StudentId, CourseId, Grade) VALUES ('104','10004','95')",
        };
        for (int i = 0; i < defaultDataGrades.length; i++) {
            db.execSQL(defaultDataGrades[i]);
            Log.d(TAG, defaultDataGrades[i]);
        }

    }
}