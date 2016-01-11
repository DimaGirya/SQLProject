package dima.liza.mobile.shenkar.com.sqlproject.activity;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

import dima.liza.mobile.shenkar.com.sqlproject.grade.Grade;
import dima.liza.mobile.shenkar.com.sqlproject.R;
import dima.liza.mobile.shenkar.com.sqlproject.SQL.DataAccess;
import dima.liza.mobile.shenkar.com.sqlproject.courses.Course;
import dima.liza.mobile.shenkar.com.sqlproject.courses.CourseAdapter;
import dima.liza.mobile.shenkar.com.sqlproject.lectures.Lecture;
import dima.liza.mobile.shenkar.com.sqlproject.lectures.LectureAdapter;
import dima.liza.mobile.shenkar.com.sqlproject.students.Student;
import dima.liza.mobile.shenkar.com.sqlproject.students.StudentAdapter;
import dima.liza.mobile.shenkar.com.sqlproject.grade.StudentGrade;
import dima.liza.mobile.shenkar.com.sqlproject.grade.StudentGradeAdapter;

public class MainActivity extends AppCompatActivity {
    final int REQUEST_ADD_STUDENT = 1;
    final int REQUEST_ADD_LECTURE = 2;
    final int REQUEST_ADD_COURSE = 3;
    final int REQUEST_ADD_GRADE = 4;
    final int REQUEST_EDIT_STUDENT = 5;
    final int REQUEST_EDIT_LECTURE = 6;
    final int REQUEST_EDIT_COURSE = 7;
    final int REQUEST_EDIT_GRADE = 8;
    final int MODE_CREATE = 1;
    final int MODE_EDIT = 2;
    String TAG  = "MainActivity";
    ListView listView;
    DataAccess dataAccess;
    EditText editTextStudentId;
    EditText editTextCourseId;
    EditText editTextLectureId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dataAccess = DataAccess.getInstatnce(this);
        listView = (ListView) findViewById(R.id.listView);
        editTextStudentId = (EditText) findViewById(R.id.editTextStudentId);
        editTextCourseId = (EditText) findViewById(R.id.editTextCourseId);
        editTextLectureId = (EditText)findViewById(R.id.editTextLectureId) ;
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        Log.d(TAG, "onActivityResult start");
        switch (requestCode){
            case REQUEST_ADD_STUDENT:{
                if(resultCode== Activity.RESULT_OK){
                    String strStudentId  = data.getStringExtra("studentId");
                    int studentId  = Integer.parseInt(strStudentId);
                    String studentFirstName =  data.getStringExtra("studentFirstName");
                    String studentLastName =   data.getStringExtra("studentLastName");
                    String studentAddress =  data.getStringExtra("studentAddress");
                    String studentDateOfBirthday = data.getStringExtra("studentDateOfBirthday");
                    Student newStudent = new Student(studentId,studentFirstName,studentLastName,studentAddress,studentDateOfBirthday);
                    if(dataAccess.addStudent(newStudent)){
                        Toast.makeText(this,"Student add to data base",Toast.LENGTH_LONG).show();
                    }
                    else{
                        Toast.makeText(this,"Student not add to data base,try again",Toast.LENGTH_LONG).show();
                    }
                }
                break;
            }
            case REQUEST_ADD_LECTURE:{
                if(resultCode== Activity.RESULT_OK){
                    String strLectureId  = data.getStringExtra("lectureId");
                    int lectureId  = Integer.parseInt(strLectureId);
                    String lectureFirstName =  data.getStringExtra("lectureFirstName");
                    String lectureLastName =   data.getStringExtra("lectureLastName");
                    String lectureAddress =  data.getStringExtra("lectureAddress");
                    Lecture newLecture = new Lecture(lectureId,lectureFirstName,lectureLastName,lectureAddress);
                    if(dataAccess.addLecture(newLecture)){
                        Toast.makeText(this,"Lecture add to data base",Toast.LENGTH_LONG).show();
                    }
                    else{
                        Toast.makeText(this,"Lecture not add to data base,try again",Toast.LENGTH_LONG).show();
                    }
                }
                break;
            }
            case REQUEST_ADD_COURSE:{
                if(resultCode== Activity.RESULT_OK){
                    String strCourseId  = data.getStringExtra("courseId");
                    int courseId  = Integer.parseInt(strCourseId);
                    String courseName  = data.getStringExtra("courseName");
                    String courseSemester  = data.getStringExtra("courseSemester");
                    String strCourseYear  = data.getStringExtra("courseYear");
                    int courseYear  = Integer.parseInt(strCourseYear);
                    String strLectureId  = data.getStringExtra("lectureId");
                    int lectureId  = Integer.parseInt(strLectureId);
                    Course courseGrade = new Course(courseId,courseName,courseSemester,courseYear,lectureId);
                    if(dataAccess.addCourse(courseGrade)){
                        Toast.makeText(this,"Course add to data base",Toast.LENGTH_LONG).show();
                    }
                    else{
                        Toast.makeText(this,"Course not add to data base,try again",Toast.LENGTH_LONG).show();
                    }
                }
                break;
            }
            case REQUEST_ADD_GRADE:{
                if(resultCode== Activity.RESULT_OK){
                    String stStudentId  = data.getStringExtra("studentId");
                    int studentId  = Integer.parseInt(stStudentId);
                    String stCourseId  = data.getStringExtra("courseId");
                    int courseId  = Integer.parseInt(stCourseId);
                    String strGrade  = data.getStringExtra("grade");
                    int grade  = Integer.parseInt(strGrade);
                    Grade newGrade = new Grade(studentId,courseId,grade);
                    if(dataAccess.addGrade(newGrade)){
                        Toast.makeText(this,"Grade add to data base",Toast.LENGTH_LONG).show();
                    }
                    else{
                        Toast.makeText(this,"Grade not add to data base,try again",Toast.LENGTH_LONG).show();
                    }
                }
                break;
            }
        }

    }



    public void onClickButton(View view) {
        switch (view.getId()){
            case R.id.buttonGetStudentsList:{
                Log.i(TAG,"buttonGetStudentsList click");
                List<Student> students = dataAccess.getAllStudents();
                StudentAdapter studentAdapter = new StudentAdapter(this,students);
                listView.setAdapter(studentAdapter);
                break;
            }
            case R.id.buttonGetLecturesList:{
                Log.i(TAG,"buttonGetLecturesList click");
                List<Lecture> lectures = dataAccess.getAllLecture();
                LectureAdapter lectureAdapter = new LectureAdapter(this,lectures);
                listView.setAdapter(lectureAdapter);
                break;
            }
            case R.id.buttonGetCoursesList:{
                Log.i(TAG,"buttonGetCoursesList click");
                List<Course> lectures = dataAccess.getAllCourses();
                CourseAdapter courseAdapter = new CourseAdapter(this,lectures);
                listView.setAdapter(courseAdapter);
                break;
            }
            case R.id.buttonAddStudent:{
                Log.i(TAG, "buttonAddStudent click");
                Intent intent = new Intent(this,AddStudentActivity.class);
                intent.putExtra("mode",MODE_CREATE);
                String studentId = editTextStudentId.getText().toString();
                if (studentId.equals("")) {
                    studentId = "NO_STUDENT_ID";
                }
                intent.putExtra("studentId", studentId);
                startActivityForResult(intent, REQUEST_ADD_STUDENT);
                break;
            }
            case R.id.buttonAddCourse:{
                Log.i(TAG,"buttonAddCourse click");
                Intent intent = new Intent(this,AddCourseActivity.class);
                intent.putExtra("mode",MODE_CREATE);
                String lectureId = editTextLectureId.getText().toString();
                if (lectureId.equals("")) {
                    lectureId = "NO_LECTURE_ID";
                }
                intent.putExtra("lectureId", lectureId);
                String courseId = editTextCourseId.getText().toString();
                if (courseId.equals("")) {
                    courseId = "NO_COURSE_ID";
                }
                intent.putExtra("courseId",courseId);
                startActivityForResult(intent, REQUEST_ADD_COURSE);
                break;
            }
            case R.id.buttonAddLecture:{
                Log.i(TAG,"buttonAddLecture click");
                Intent intent = new Intent(this,AddLectureActivity.class);
                intent.putExtra("mode",MODE_CREATE);
                String lectureId = editTextLectureId.getText().toString();
                if (lectureId.equals("")) {
                    lectureId = "NO_LECTURE_ID";
                }
                intent.putExtra("lectureId", lectureId);
                startActivityForResult(intent, REQUEST_ADD_LECTURE);
                break;
            }
            case R.id.buttonAddGrade:{
                Log.i(TAG,"buttonAddGrade click");
                Intent intent = new Intent(this,AddGradeActivity.class);
                intent.putExtra("mode",MODE_CREATE);
                String studentId = editTextStudentId.getText().toString();
                if (studentId.equals("")) {
                    studentId = "NO_STUDENT_ID";
                }
                intent.putExtra("studentId", studentId);
                String courseId = editTextCourseId.getText().toString();
                if (courseId.equals("")) {
                    courseId = "NO_COURSE_ID";
                }
                intent.putExtra("courseId", courseId);
                startActivityForResult(intent, REQUEST_ADD_GRADE);

                break;
            }
            case R.id.buttonRemoveCourse:{
                Log.i(TAG,"buttonRemoveCourse click");
                try {
                    int courseId = Integer.parseInt(editTextCourseId.getText().toString());
                    if(dataAccess.removeCourse(courseId)){
                        Toast.makeText(this,"Course remove succeed",Toast.LENGTH_LONG).show();
                    }
                    else{
                        Toast.makeText(this,"Course remove unsuccessful.No such course Id",Toast.LENGTH_LONG).show();
                    }
                }
                catch (NumberFormatException e){
                    Toast.makeText(this,"illegally course id",Toast.LENGTH_LONG).show();
                }

                break;
            }
            case R.id.buttonRemoveGrade:{
                try{
                    int studentId = Integer.parseInt(editTextStudentId.getText().toString());
                    int courseId = Integer.parseInt(editTextCourseId.getText().toString());
                    if(dataAccess.removeGrade(studentId, courseId)){
                        Toast.makeText(this,"Grade remove succeed",Toast.LENGTH_LONG).show();
                    }
                    else{
                        Toast.makeText(this,"Grade remove unsuccessful.No such grade to remove",Toast.LENGTH_LONG).show();
                    }
                }
                catch(NumberFormatException e){
                    Toast.makeText(this,"illegally student or course id",Toast.LENGTH_LONG).show();
                }
                Log.i(TAG,"buttonRemoveGrade click");
                break;
            }
            case R.id.buttonRemoveLecture:{
                Log.i(TAG,"buttonRemoveLecture click");
                try {
                    int lectureId = Integer.parseInt(editTextLectureId.getText().toString());
                    if(dataAccess.removeLecture(lectureId)){
                        Toast.makeText(this,"Lecture remove succeed",Toast.LENGTH_LONG).show();
                    }
                    else{
                        Toast.makeText(this,"Lecture remove unsuccessful.No such lecture Id",Toast.LENGTH_LONG).show();
                    }
                }
                catch (NumberFormatException e){
                    Toast.makeText(this,"illegally lecture id",Toast.LENGTH_LONG).show();
                }

                break;
            }
            case R.id.buttonRemoveStudent:{
                Log.i(TAG,"buttonRemoveStudent click");
                try {
                    int studentId = Integer.parseInt(editTextStudentId.getText().toString());
                    if(dataAccess.removeStudent(studentId)){
                        Toast.makeText(this,"Student remove succeed",Toast.LENGTH_LONG).show();
                    }
                    else{
                        Toast.makeText(this,"Student remove unsuccessful.No such student Id",Toast.LENGTH_LONG).show();
                    }
                }
                catch (NumberFormatException e){
                    Toast.makeText(this,"illegally student id",Toast.LENGTH_LONG).show();
                }
                break;
            }
            case R.id.buttonEditGrade:{
                Log.i(TAG, "buttonEditGrade click");
                Intent intent = new Intent(this,AddGradeActivity.class);
                intent.putExtra("mode",MODE_EDIT);



                startActivityForResult(intent, REQUEST_EDIT_GRADE);
                break;
            }
            case R.id.buttonEditLecture:{
                Log.i(TAG,"buttonEditLecture click");
                Intent intent = new Intent(this,AddLectureActivity.class);
                intent.putExtra("mode",MODE_EDIT);



                startActivityForResult(intent, REQUEST_EDIT_LECTURE);
                break;
            }
            case R.id.buttonEditCourse:{
                Log.i(TAG,"buttonEditCourse click");
                Intent intent = new Intent(this,AddCourseActivity.class);
                intent.putExtra("mode",MODE_EDIT);



                startActivityForResult(intent, REQUEST_EDIT_COURSE);
                break;
            }
            case R.id.buttonEditStudent:{
                Log.i(TAG,"buttonEditStudent click");
                Intent intent = new Intent(this,AddStudentActivity.class);
                intent.putExtra("mode",MODE_EDIT);



                startActivityForResult(intent, REQUEST_EDIT_STUDENT);
                break;
            }
            case R.id.buttonShowGradesOfStudent:{
                Log.i(TAG,"buttonShowGradesOfStudent click");
                String studentId = editTextStudentId.getText().toString();
                List<StudentGrade> studentGrade = dataAccess.getStudentGrades(studentId);
                StudentGradeAdapter studentGradeAdapter = new StudentGradeAdapter(this,studentGrade);
                listView.setAdapter(studentGradeAdapter);
                break;
            }
            case R.id.buttonTop3Students:{
                Log.i(TAG,"buttonTop3Students click");
                break;
            }
            case R.id.buttonBottom3Students:{
                Log.i(TAG,"buttonBottom3Students click");
                break;
            }
            default:{
                Log.e(TAG, "Error in press button");
                break;
            }
        }
    }
}
