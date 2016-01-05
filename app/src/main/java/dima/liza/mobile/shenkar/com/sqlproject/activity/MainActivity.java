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

import dima.liza.mobile.shenkar.com.sqlproject.R;
import dima.liza.mobile.shenkar.com.sqlproject.SQL.DataAccess;
import dima.liza.mobile.shenkar.com.sqlproject.courses.Course;
import dima.liza.mobile.shenkar.com.sqlproject.courses.CourseAdapter;
import dima.liza.mobile.shenkar.com.sqlproject.lectures.Lecture;
import dima.liza.mobile.shenkar.com.sqlproject.lectures.LectureAdapter;
import dima.liza.mobile.shenkar.com.sqlproject.students.Student;
import dima.liza.mobile.shenkar.com.sqlproject.students.StudentAdapter;
import dima.liza.mobile.shenkar.com.sqlproject.students.grade.StudentGrade;
import dima.liza.mobile.shenkar.com.sqlproject.students.grade.StudentGradeAdapter;

public class MainActivity extends AppCompatActivity {
    final int REQUEST_ADD_STUDENT = 1;
    final int REQUEST_ADD_LECTURE = 2;
    final int REQUEST_ADD_COURSE = 3;
    final int REQUEST_ADD_GRADE = 4;
    String TAG  = "MainActivity";
    ListView listView;
    DataAccess dataAccess;
    EditText editTextStudentId;
    EditText editTextCourseId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dataAccess = DataAccess.getInstatnce(this);
        listView = (ListView) findViewById(R.id.listView);
        editTextStudentId = (EditText) findViewById(R.id.editTextStudentIdMain);
        editTextCourseId = (EditText) findViewById(R.id.editTextCourseIdMain);

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
                //todo
                }
                break;
            }
            case REQUEST_ADD_COURSE:{
                if(resultCode== Activity.RESULT_OK){
                    //todo
                }
                break;
            }
            case REQUEST_ADD_GRADE:{
                if(resultCode== Activity.RESULT_OK){
                    //todo
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
                startActivityForResult(intent, REQUEST_ADD_STUDENT);
                break;
            }
            case R.id.buttonAddCourse:{
                Log.i(TAG,"buttonAddCourse click");
                Intent intent = new Intent(this,AddCourseActivity.class);
                startActivityForResult(intent, REQUEST_ADD_COURSE);
                break;
            }
            case R.id.buttonAddLecture:{
                Intent intent = new Intent(this,AddLectureActivity.class);
                startActivityForResult(intent, REQUEST_ADD_LECTURE);
                Log.i(TAG,"buttonAddLecture click");
                break;
            }
            case R.id.buttonAddGrade:{
                Intent intent = new Intent(this,AddGradeActivity.class);
                startActivityForResult(intent, REQUEST_ADD_GRADE);
                Log.i(TAG,"buttonAddGrade click");
                break;
            }
            case R.id.buttonRemoveCourse:{
                Log.i(TAG,"buttonRemoveCourse click");
                break;
            }
            case R.id.buttonRemoveGrade:{
                Log.i(TAG,"buttonRemoveGrade click");
                break;
            }
            case R.id.buttonRemoveLecture:{
                Log.i(TAG,"buttonRemoveLecture click");
                break;
            }
            case R.id.buttonRemoveStudent:{
                Log.i(TAG,"buttonRemoveStudent click");
                break;
            }
            case R.id.buttonEditGrade:{
                Log.i(TAG,"buttonEditGrade click");
                break;
            }
            case R.id.buttonEditLecture:{
                Log.i(TAG,"buttonEditLecture click");
                break;
            }
            case R.id.buttonEditCourse:{
                Log.i(TAG,"buttonEditCourse click");
                break;
            }
            case R.id.buttonEditStudent:{
                Log.i(TAG,"buttonEditStudent click");
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
