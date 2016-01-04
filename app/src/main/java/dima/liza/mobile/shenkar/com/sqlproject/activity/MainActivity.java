package dima.liza.mobile.shenkar.com.sqlproject.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ListView;

import java.util.List;

import dima.liza.mobile.shenkar.com.sqlproject.R;
import dima.liza.mobile.shenkar.com.sqlproject.SQL.DataAccess;
import dima.liza.mobile.shenkar.com.sqlproject.courses.Course;
import dima.liza.mobile.shenkar.com.sqlproject.courses.CourseAdapter;
import dima.liza.mobile.shenkar.com.sqlproject.lectures.Lecture;
import dima.liza.mobile.shenkar.com.sqlproject.lectures.LectureAdapter;
import dima.liza.mobile.shenkar.com.sqlproject.students.Student;
import dima.liza.mobile.shenkar.com.sqlproject.students.StudentAdapter;

public class MainActivity extends AppCompatActivity {
    String TAG  = "MainActivity";
    ListView listView;
   DataAccess data;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG,"Start!");
        try {
            setContentView(R.layout.activity_main);
        }
        catch (Exception e){
            Log.d(TAG,"Exception!",e);
        }
        data = DataAccess.getInstatnce(this);
        listView = (ListView) findViewById(R.id.listView);

        //listView.setAdapter();
    }

    public void onClickButton(View view) {
        switch (view.getId()){
            case R.id.buttonGetStudentsList:{
                Log.i(TAG,"buttonGetStudentsList click");
                List<Student> students = data.getAllStudents();
                StudentAdapter studentAdapter = new StudentAdapter(this,students);
                listView.setAdapter(studentAdapter);
                break;
            }
            case R.id.buttonGetLecturesList:{
                Log.i(TAG,"buttonGetLecturesList click");
                List<Lecture> lectures = data.getAllLecture();
                LectureAdapter lectureAdapter = new LectureAdapter(this,lectures);
                listView.setAdapter(lectureAdapter);
                break;
            }
            case R.id.buttonGetCoursesList:{
                Log.i(TAG,"buttonGetCoursesList click");
                List<Course> lectures = data.getAllCourses();
                CourseAdapter courseAdapter = new CourseAdapter(this,lectures);
                listView.setAdapter(courseAdapter);
                break;
            }
            case R.id.buttonAddStudent:{
                Log.i(TAG, "buttonAddStudent click");
                break;
            }
            case R.id.buttonAddCourse:{
                Log.i(TAG,"buttonAddCourse click");
                break;
            }
            case R.id.buttonAddLecture:{
                Log.i(TAG,"buttonAddLecture click");
                break;
            }
            case R.id.buttonAddGrade:{
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
                Log.i(TAG,"Error in press button");
                break;
            }
        }
    }
}
