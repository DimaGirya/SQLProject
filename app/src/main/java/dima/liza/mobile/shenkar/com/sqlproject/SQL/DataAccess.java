package dima.liza.mobile.shenkar.com.sqlproject.SQL;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import dima.liza.mobile.shenkar.com.sqlproject.MyConstant;
import dima.liza.mobile.shenkar.com.sqlproject.grade.StudentGrade;
import dima.liza.mobile.shenkar.com.sqlproject.courses.Course;
import dima.liza.mobile.shenkar.com.sqlproject.grade.Grade;
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
            Log.i(TAG,"SELECT FROM "+DbContract.StudentEntry.TABLE_NAME + " "
                    + studentColumns[0] + "," + studentColumns[1] + ","
                    + studentColumns[2] + "," + studentColumns[3] +  "," +  studentColumns[4] + ";");

            /*
            SELECT * FROM Students;
             */
            Cursor cursor = database.query(DbContract.StudentEntry.TABLE_NAME, studentColumns, null, null, null, null, null);

            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                Student student = getStudentFromCursor(cursor);
                studentList.add(student);
                cursor.moveToNext();
            }
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
        return null;
    }

    private Student getStudentFromCursor(Cursor cursor) {
        int studentId = cursor.getInt(cursor.getColumnIndex(DbContract.StudentEntry.COLUMN_STUDENT_ID));
        String firstName = cursor.getString(cursor.getColumnIndex(DbContract.StudentEntry.COLUMN_FIRST_NAME));
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
            Log.i(TAG,"SELECT FROM "+DbContract.LectureEntry.TABLE_NAME + " "
                    + lectureColumns[0] + "," + lectureColumns[1] + ","
                    + lectureColumns[2] + "," + lectureColumns[3] + ";");

            /*
            SELECT * FROM Courses;
             */
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
            Log.d(TAG, "Error,Exception:", e);
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
            Log.i(TAG,"SELECT FROM "+DbContract.CourseEntry.TABLE_NAME + " "
                    + courseColumns[0] + "," + courseColumns[1] + ","
                    + courseColumns[2] + "," + courseColumns[3] +  "," +  courseColumns[4] + ";");
            /*
            SELECT * FROM Lectures;
             */
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
            Log.d(TAG, "Error,Exception:", e);
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
        String address = cursor.getString(cursor.getColumnIndex(DbContract.LectureEntry.COLUMN_ADDRESS));
        Lecture lecture = new Lecture(studentId,firstName,lastName,address);
        return lecture;
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
            Log.e(TAG, "Exception!", e);
            return false;
        } finally {
            if (database != null) {
                database.close();
            }
        }
    }

    @Override
    public boolean addLecture(Lecture lecture) {
        ContentValues content = new ContentValues();
        content.put(DbContract.LectureEntry.COLUMN_LECTURE_ID, lecture.getLectureId());
        content.put(DbContract.LectureEntry.COLUMN_FIRST_NAME, lecture.getFirstName());
        content.put(DbContract.LectureEntry.COLUMN_LAST_NAME, lecture.getLastName());
        content.put(DbContract.LectureEntry.COLUMN_ADDRESS, lecture.getAddress());
        try {
            database = dbHelper.getReadableDatabase();
            if(database.insert(DbContract.LectureEntry.TABLE_NAME,null,content)==-1){
                return false;
            }
            else{
                return true;
            }
        } catch (Exception e) {
            Log.e(TAG, "Exception!", e);
            return false;
        } finally {
            if (database != null) {
                database.close();
            }
        }
    }

    @Override
    public boolean addCourse(Course course) {
        ContentValues content = new ContentValues();
        content.put(DbContract.CourseEntry.COLUMN_COURSE_ID, course.getCourseId());
        content.put(DbContract.CourseEntry.COLUMN_COURSE_NAME, course.getCourseName());
        content.put(DbContract.CourseEntry.COLUMN_SEMESTER, course.getSemester());
        content.put(DbContract.CourseEntry.COLUMN_YEAR, course.getYear());
        content.put(DbContract.CourseEntry.COLUMN_LECTURE_ID, course.getLectureId());
        try {
            database = dbHelper.getReadableDatabase();
            if(database.insert(DbContract.CourseEntry.TABLE_NAME,null,content)==-1){
                return false;
            }
            else{
                return true;
            }
        } catch (Exception e) {
            Log.e(TAG,"Exception!",e);
            return false;
        } finally {
            if (database != null) {
                database.close();
            }
        }
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
            Log.e(TAG, "Exception!", e);
            return false;
        } finally {
            if (database != null) {
                database.close();
            }
        }
    }

    @Override
    public boolean removeStudent(int studentId) {
        database = dbHelper.getReadableDatabase();
        String whereClauseStudent = DbContract.StudentEntry.COLUMN_STUDENT_ID + " = ? ";
        String whereArgs[] = new String[1];
        String whereClauseGrade = DbContract.GradeEntry.COLUMN_STUDENT_ID + " = ? ";
        whereArgs[0] = Integer.toString(studentId);

        database.beginTransaction();
        try {
            if(database.delete(DbContract.StudentEntry.TABLE_NAME,whereClauseStudent,whereArgs)!=1){
                return false;
            }
            database.delete(DbContract.GradeEntry.TABLE_NAME,whereClauseGrade,whereArgs);
            database.setTransactionSuccessful();
            return true;
        }
        catch(Exception e){
            Log.e(TAG,"Exception!",e);
            return false;
        }
        finally {
            database.endTransaction();
        }
    }

    @Override
    public boolean removeLecture(int lectureId) {
        database = dbHelper.getReadableDatabase();
        String whereClauseLecture = DbContract.LectureEntry.COLUMN_LECTURE_ID + " = ? ";
        String whereArgs[] = new String[1];
        whereArgs[0] = Integer.toString(lectureId);
        Log.i(TAG,"Lecture id to delete:"+whereArgs[0]);
        String whereClauseCourse = DbContract.CourseEntry.COLUMN_LECTURE_ID + " = ?";
        ContentValues values = new ContentValues();
        values.put(DbContract.CourseEntry.COLUMN_LECTURE_ID,-1);
        database.beginTransaction();
        try {
            if(database.delete(DbContract.LectureEntry.TABLE_NAME,whereClauseLecture,whereArgs)!=1){
                return false;
            }
            database.update(DbContract.CourseEntry.TABLE_NAME, values, whereClauseCourse, whereArgs);
            database.setTransactionSuccessful();
            return true;
        }
        catch(Exception e){
            Log.e(TAG, "Exception!", e);
            return false;
        }
        finally {
            database.endTransaction();
        }
    }

    @Override
    public boolean removeCourse(int courseId) {
        database = dbHelper.getReadableDatabase();
        String whereClauseCourse = DbContract.CourseEntry.COLUMN_COURSE_ID + " = ? ";
        String whereArgs[] = new String[1];
        String whereClauseStudent = DbContract.GradeEntry.COLUMN_COURSE_ID + " = ? ";
        whereArgs[0] = Integer.toString(courseId);

        database.beginTransaction();
        try {
            if(database.delete(DbContract.CourseEntry.TABLE_NAME, whereClauseCourse, whereArgs)!=1){
                return false;
            }
            database.delete(DbContract.GradeEntry.TABLE_NAME,whereClauseStudent,whereArgs);
            database.setTransactionSuccessful();
            return true;
        }
        catch(Exception e){
            Log.e(TAG, "Exception!", e);
            return false;
        }
        finally {
            database.endTransaction();
        }
    }

    @Override
    public boolean removeGrade(int studentId, int courseId) {
        database = dbHelper.getReadableDatabase();
        String whereClauseGrade = DbContract.GradeEntry.COLUMN_STUDENT_ID + " = ? " + " AND "
                + DbContract.GradeEntry.COLUMN_COURSE_ID + " = ? ";
        String whereArgs[] = new String[2];
        whereArgs[0] = Integer.toString(studentId);
        whereArgs[1] = Integer.toString(courseId);
        try {
            if(database.delete(DbContract.GradeEntry.TABLE_NAME,whereClauseGrade,whereArgs)!=1){
                return false;
            }
            return true;
        }
        catch(Exception e){
            Log.e(TAG,"Exception!",e);
            return false;
        }
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
            Cursor cursor =  database.rawQuery(sqlJoin,selectionArgs);
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                Log.i(TAG,"cursor work 1");
                StudentGrade studentGrade = getStudentGradesFromCursor(cursor);
                Log.i(TAG,"cursor work 2");
                studentGrades.add(studentGrade);
                cursor.moveToNext();
            }
            cursor.close();
            return studentGrades;
        } catch (Exception e) {
            Log.d(TAG, "Error,Exception:", e);
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
            Log.d(TAG, " cursor.getCount():" + cursor.getCount());
            if(cursor.getCount()!=1){
                Log.d(TAG, "Error!! more than 1 course with id or no such course:"+courseId + " cursor.getCount():"+cursor.getCount());
                return null;
            }
            Course course = getCurseFromCursor(cursor);
            return course;
        } catch (Exception e) {
            Log.d(TAG, "Error,Exception:", e);
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
        } catch (Exception e) {
            Log.d(TAG, "Error,Exception:", e);
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
            Log.d(TAG, sqlSelect + " ?=" + selectionArgs[0]);
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
            Log.d(TAG, "Error,Exception:", e);
        }
        finally {
            if (database != null) {
                database.close();
            }
        }
        return null;
    }

    @Override
    public Grade getGradeById(String courseId, String studentId) {
        try {
            database = dbHelper.getReadableDatabase();
            String sqlSelect = "SELECT " + DbContract.GradeEntry.COLUMN_STUDENT_ID  + ","
                    +  DbContract.GradeEntry.COLUMN_COURSE_ID + "," + DbContract.GradeEntry.COLUMN_GRADE
                    + " FROM "+ DbContract.GradeEntry.TABLE_NAME
                    + " WHERE " +  DbContract.GradeEntry.COLUMN_COURSE_ID + "= ? "
                    + "AND " + DbContract.GradeEntry.COLUMN_STUDENT_ID + "= ?";
            String  selectionArgs[] = new String[2];
            selectionArgs[0] = courseId;
            selectionArgs[1] = studentId;
            Log.d(TAG,sqlSelect);
            Cursor cursor =  database.rawQuery(sqlSelect,selectionArgs);
            cursor.moveToFirst();
            if(cursor.getCount()!=1){
                Log.d(TAG, "Error!! more than 1 grade  with id or no such course:"+courseId + "student:" + studentId
                        + " cursor.getCount():"+cursor.getCount());
                return null;
            }
            Grade grade = getGradeFromCursor(cursor);
            return grade;
        } catch (Exception e) {
            Log.d(TAG, "Error,Exception:", e);
        }
        finally {
            if (database != null) {
                database.close();
            }
        }
        return null;
    }

    private Grade getGradeFromCursor(Cursor cursor) {
        int studentId = cursor.getInt(cursor.getColumnIndex(DbContract.GradeEntry.COLUMN_STUDENT_ID));
        int courseId = cursor.getInt(cursor.getColumnIndex(DbContract.GradeEntry.COLUMN_COURSE_ID));
        int gradeInt =cursor.getInt(cursor.getColumnIndex(DbContract.GradeEntry.COLUMN_GRADE));
        Grade grade = new Grade(studentId,courseId,gradeInt);
        return grade;
    }

    @Override
    public boolean editLecture(Lecture lecture) {
        ContentValues values = new ContentValues();
        String whereClauseLecture = DbContract.LectureEntry.COLUMN_LECTURE_ID + " = ? ";
        String whereArgs[] = new String[1];
        whereArgs[0] = Integer.toString(lecture.getLectureId());
        values.put(DbContract.LectureEntry.COLUMN_FIRST_NAME,lecture.getFirstName());
        values.put(DbContract.LectureEntry.COLUMN_LAST_NAME,lecture.getLastName());
        values.put(DbContract.LectureEntry.COLUMN_ADDRESS, lecture.getAddress());
        Log.i(TAG, "UPDATE " + DbContract.LectureEntry.TABLE_NAME + " SET "
                + DbContract.LectureEntry.COLUMN_FIRST_NAME + "=" +lecture.getFirstName() + ","
                + DbContract.LectureEntry.COLUMN_LAST_NAME + "=" +lecture.getLastName() + ","
                + DbContract.LectureEntry.COLUMN_ADDRESS + "=" +lecture.getAddress()
                + " WHERE " + DbContract.LectureEntry.COLUMN_LECTURE_ID + " = " +   whereArgs[0]+ ";");
        database = dbHelper.getReadableDatabase();
        database.beginTransaction();
        try {
            if(database.update(DbContract.LectureEntry.TABLE_NAME, values, whereClauseLecture, whereArgs)!=1){
                return false;
            }
            database.setTransactionSuccessful();
            return true;
        }
        catch(Exception e){
            Log.e(TAG, "Exception!", e);
            return false;
        }
        finally {
            database.endTransaction();
        }
    }

    @Override
    public boolean editStudent(Student student) {
        ContentValues values = new ContentValues();
        String whereClauseLecture = DbContract.StudentEntry.COLUMN_STUDENT_ID + " = ? ";
        String whereArgs[] = new String[1];
        whereArgs[0] = Integer.toString(student.getStudentId());


        values.put(DbContract.StudentEntry.COLUMN_FIRST_NAME, student.getFirstName());
        values.put(DbContract.StudentEntry.COLUMN_LAST_NAME, student.getLastName());
        values.put(DbContract.StudentEntry.COLUMN_DATE_OF_BIRTH, student.getDateOfBirth());
        values.put(DbContract.StudentEntry.COLUMN_ADDRESS, student.getAddress());
        database = dbHelper.getReadableDatabase();
        database.beginTransaction();
        try {
            Log.i(TAG, "UPDATE " + DbContract.StudentEntry.TABLE_NAME + " SET "
                    + DbContract.StudentEntry.COLUMN_FIRST_NAME + "=" + student.getFirstName() + ","
                    + DbContract.StudentEntry.COLUMN_LAST_NAME + "=" + student.getLastName() + ","
                    + DbContract.StudentEntry.COLUMN_DATE_OF_BIRTH + "=" + student.getDateOfBirth() + ","
                    + DbContract.StudentEntry.COLUMN_ADDRESS + "=" + student.getAddress() + " "
                    + " WHERE " + DbContract.StudentEntry.COLUMN_STUDENT_ID + " = " + whereArgs[0] + ";");
            if (database.update(DbContract.StudentEntry.TABLE_NAME, values, whereClauseLecture, whereArgs) != 1) {
                return false;
            }
            database.setTransactionSuccessful();
            return true;
        }
        catch(Exception e){
            Log.e(TAG,"Exception!",e);
            return false;
        }
        finally {
            database.endTransaction();
        }
    }
    @Override
    public boolean editCourse(Course course) {
        ContentValues values = new ContentValues();
        String whereClauseLecture = DbContract.CourseEntry.COLUMN_COURSE_ID + " = ? ";
        String whereArgs[] = new String[1];
        whereArgs[0] = Integer.toString(course.getCourseId());


        values.put(DbContract.CourseEntry.COLUMN_COURSE_NAME,course.getCourseName());
        values.put(DbContract.CourseEntry.COLUMN_YEAR,course.getYear());
        values.put(DbContract.CourseEntry.COLUMN_SEMESTER,course.getSemester());
        values.put(DbContract.CourseEntry.COLUMN_LECTURE_ID, course.getLectureId());
        database = dbHelper.getReadableDatabase();
        database.beginTransaction();
        try {
            Log.i(TAG, "UPDATE " + DbContract.CourseEntry.TABLE_NAME + " SET "
                    + DbContract.CourseEntry.COLUMN_COURSE_NAME + "=" + course.getCourseName() + ","
                    + DbContract.CourseEntry.COLUMN_YEAR + "=" + course.getYear() + ","
                    + DbContract.CourseEntry.COLUMN_SEMESTER + "=" + course.getSemester() + ","
                    + DbContract.CourseEntry.COLUMN_LECTURE_ID + "=" + course.getLectureId() + ","
                    + " WHERE " + DbContract.CourseEntry.COLUMN_COURSE_ID + " = " +   whereArgs[0]+ ";");
            if(database.update(DbContract.CourseEntry.TABLE_NAME,values,whereClauseLecture,whereArgs)!=1){
                return false;
            }
            database.setTransactionSuccessful();
            return true;
        }
        catch(Exception e){
            Log.e(TAG,"Exception!",e);
            return false;
        }
        finally {
            database.endTransaction();
        }
    }

    @Override
    public boolean editGrade(Grade grade) {
        ContentValues values = new ContentValues();
        String whereClause = DbContract.GradeEntry.COLUMN_COURSE_ID + " = ? "
                + " AND " + DbContract.GradeEntry.COLUMN_STUDENT_ID + " = ?";
        String whereArgs[] = new String[2];
        whereArgs[0] = Integer.toString(grade.getCourseId());
        whereArgs[1] = Integer.toString(grade.getStudentId());

        values.put(DbContract.GradeEntry.COLUMN_GRADE, grade.getGrade());
        database = dbHelper.getReadableDatabase();
        database.beginTransaction();
        try {
            Log.i(TAG, "UPDATE " + DbContract.GradeEntry.TABLE_NAME + " SET "
                    + DbContract.GradeEntry.COLUMN_GRADE + "=" + grade.getGrade() + ","
                    + " WHERE " + DbContract.GradeEntry.COLUMN_COURSE_ID + " = " +   whereArgs[0]
                    +  " AND " + DbContract.GradeEntry.COLUMN_STUDENT_ID +  whereArgs[1] + ";");
            if(database.update(DbContract.GradeEntry.TABLE_NAME,values,whereClause,whereArgs)!=1){
                return false;
            }
            database.setTransactionSuccessful();
            return true;
        }
        catch(Exception e){
            Log.e(TAG,"Exception!",e);
            return false;
        }
        finally {
            database.endTransaction();
        }
    }

    @Override
    public List<StudentGrade> getTopOrBottomStudent(String courseId, int topOrBottom) {
        try {
            database = dbHelper.getReadableDatabase();
            List<StudentGrade> studentGrades = new ArrayList<StudentGrade>();
            String arg;
            if(topOrBottom == MyConstant.ConstantEntry.TOP){
                arg  = " DESC ";
            } else{
                arg = " ASC ";

            }
                    String sqlJoin  = "SELECT  Students.LastName,Students.FirstName,Grades.StudentId, "
                            + "Grades.Grade,Courses.CourseName "
                            + " FROM Students "
                            + " INNER JOIN Grades "
                            + " ON Grades.StudentId = Students.StudentID "
                            +  " INNER JOIN Courses "
                            +  " ON Grades.CourseId = Courses.CourseID "
                            +  " WHERE Courses.CourseId = ? "
                            +  " ORDER BY Grades.Grade" + arg
                            + " Limit 3";

            String  selectionArgs[] = new String[1];
            selectionArgs[0] = courseId;

            Log.d(TAG,sqlJoin);
            Cursor cursor =  database.rawQuery(sqlJoin,selectionArgs);
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                StudentGrade studentGrade = getStudentGradesFromCursor(cursor);
                studentGrades.add(studentGrade);
                cursor.moveToNext();
            }
            cursor.close();
            return studentGrades;
        } catch (Exception e) {
            Log.d(TAG, "Error,Exception:", e);
        }
        finally {
            if (database != null) {
                database.close();
            }
        }
        return null;
    }


    private StudentGrade getStudentGradesFromCursor(Cursor cursor) {
        Log.i(TAG,"Cursor work start");
        int studentId = Integer.parseInt(cursor.getString(cursor.getColumnIndex("StudentId"))); // Exception!!!
        String firstName = cursor.getString(cursor.getColumnIndex("FirstName"));
        String lastName = cursor.getString(cursor.getColumnIndex("LastName"));
        String courseName = cursor.getString(cursor.getColumnIndex("CourseName"));
        int grades = Integer.parseInt(cursor.getString(cursor.getColumnIndex("Grade"))); // Exception!!!
        StudentGrade studentGrade = new StudentGrade(lastName,firstName,studentId ,courseName,grades);
        Log.i(TAG,"Cursor work end");
        return studentGrade;
    }
}
