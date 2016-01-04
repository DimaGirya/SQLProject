package dima.liza.mobile.shenkar.com.sqlproject.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ListView;

import java.util.List;

import dima.liza.mobile.shenkar.com.sqlproject.R;
import dima.liza.mobile.shenkar.com.sqlproject.SQL.DataAccess;
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
                List<Student> students = data.getAllStudents();
                StudentAdapter studentAdapter = new StudentAdapter(this,students);  //warning!!!!
                listView.setAdapter(studentAdapter);
                break;
            }
            case R.id.buttonGetLecturesList:{
                List<Lecture> lectures = data.getAllLecture();
                LectureAdapter lectureAdapter = new LectureAdapter(this,lectures);  //warning!!!!
                listView.setAdapter(lectureAdapter);
                break;
            }
            default:{
                break;
            }
        }
    }
}
