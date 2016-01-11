package dima.liza.mobile.shenkar.com.sqlproject.activity;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import dima.liza.mobile.shenkar.com.sqlproject.R;
import dima.liza.mobile.shenkar.com.sqlproject.SQL.DataAccess;

public class AddCourseActivity extends AppCompatActivity {
    EditText editTextCourseId,editTextCourseName,editTextCourseSemester,editTextCourseYear,editTextLectureId;
    DataAccess dataAccess;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_course);
        editTextCourseId = (EditText)findViewById(R.id.editTextCourseId);
        editTextCourseName = (EditText)findViewById(R.id.editTextCourseName);
        editTextCourseSemester  = (EditText)findViewById(R.id.editTextCourseSemester);
        editTextCourseYear = (EditText)findViewById(R.id.editTextCourseYear);
        editTextLectureId = (EditText)findViewById(R.id.editTextCourseLectureId);
        Intent intent = getIntent();
        String inputLectureId = intent.getStringExtra("lectureId");
        if(!inputLectureId.equals("NO_LECTURE_ID")){
            editTextLectureId.setText(inputLectureId);
        }
        String inputCourseId = intent.getStringExtra("courseId");
        if(!inputCourseId.equals("NO_COURSE_ID")){
            editTextCourseId.setText(inputCourseId);
        }
    }

    public void onClickButtonAddCourse(View view) {
        dataAccess = DataAccess.getInstatnce(this);
        String courseId = editTextCourseId.getText().toString();
        String curseName = editTextCourseName.getText().toString();
        String curseSemester = editTextCourseSemester.getText().toString();
        String curseYear = editTextCourseYear.getText().toString();
        String lectureId = editTextLectureId.getText().toString();
        if (courseId.equals("")) {
            Toast.makeText(this, "You need to input a course ID", Toast.LENGTH_SHORT).show();
            return;
        }
        if(dataAccess.getCourseById(courseId)!=null){
            Toast.makeText(this, "The date base has a course  with this ID.Additions impossible", Toast.LENGTH_SHORT).show();
            return;
        }
        if (curseName.equals("")) {
            Toast.makeText(this, "You need to input a course first name", Toast.LENGTH_SHORT).show();
            return;
        }

        if (curseSemester.equals("")) {
            Toast.makeText(this, "You need to input a course semester", Toast.LENGTH_SHORT).show();
            return;
        }
        if (curseYear.equals("")) {
            Toast.makeText(this, "You need to input a course year", Toast.LENGTH_SHORT).show();
            return;
        }
        if (lectureId.equals("")) {
            Toast.makeText(this, "You add course without  lecture", Toast.LENGTH_SHORT).show();
            lectureId = "-1";
        }
        else if(dataAccess.getLectureById(lectureId)==null){
            Toast.makeText(this, "No such lecture in data base", Toast.LENGTH_SHORT).show();
            return;
        }
        Intent returnIntent = new Intent();
        returnIntent.putExtra("courseId", courseId);
        returnIntent.putExtra("courseName",curseName);
        returnIntent.putExtra("courseSemester", curseSemester);
        returnIntent.putExtra("courseYear",curseYear);
        returnIntent.putExtra("lectureId", lectureId);
        setResult(Activity.RESULT_OK, returnIntent);
        finish();
    }

    public void onClickEditCourse(View view) {

    }
}
