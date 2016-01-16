package dima.liza.mobile.shenkar.com.sqlproject.activity;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import dima.liza.mobile.shenkar.com.sqlproject.MyConstant;
import dima.liza.mobile.shenkar.com.sqlproject.R;
import dima.liza.mobile.shenkar.com.sqlproject.SQL.DataAccess;

public class AddAndEditGradeActivity extends AppCompatActivity {
    EditText editTextCourseId;
    EditText editTextStudentId;
    EditText editTextGrade;
    DataAccess dataAccess;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_and_edit_grade);
         editTextCourseId = (EditText) findViewById(R.id.editTextAddGradeCourseId);
         editTextStudentId =  (EditText) findViewById(R.id.editTextAddGradeStudentId);
         editTextGrade = (EditText)findViewById(R.id.editTexdAddGradeGrade);
        Intent intent = getIntent();
        if(intent.getIntExtra(MyConstant.ConstantEntry.MODE,MyConstant.ConstantEntry.MODE_CREATE) == MyConstant.ConstantEntry.MODE_CREATE) {
            String inputCourseId = intent.getStringExtra("courseId");
            if (!inputCourseId.equals("NO_COURSE_ID")) {
                editTextCourseId.setText(inputCourseId);
            }
            String inputStudentId = intent.getStringExtra("studentId");
            if (!inputStudentId.equals("NO_STUDENT_ID")) {
                editTextStudentId.setText(inputStudentId);
            }
        }
        else{
            editTextCourseId.setText(intent.getStringExtra("courseId"));
            editTextStudentId.setText(intent.getStringExtra("studentId"));
            editTextGrade.setText(intent.getStringExtra("grade"));
        }
    }

    public void onClickButtonAddOrEditGrade(View view) {
        /*
        int flag = 0;
        switch (view.getId()) {
            case R.id.buttonAddGrade: {
                flag = 1;
                break;
            }
            case R.id.buttonEditGrade: {
                flag = 2;
                break;
            }
        }
        */
        dataAccess = DataAccess.getInstatnce(this);
        String courseId = editTextCourseId.getText().toString();
        String studentId = editTextStudentId.getText().toString();
        String grade = editTextGrade.getText().toString();

        if (courseId.equals("")) {
            Toast.makeText(this, "You need to input a student ID", Toast.LENGTH_SHORT).show();
            return;
        }
        if(dataAccess.getCourseById(courseId)==null){
            Toast.makeText(this, "No such course in data base", Toast.LENGTH_SHORT).show();
            return;
        }
        if (studentId.equals("")) {
            Toast.makeText(this, "You need to input a student first name", Toast.LENGTH_SHORT).show();
            return;
        }
        if(dataAccess.getStudentById(studentId)==null){
            Toast.makeText(this, "No such student in data base", Toast.LENGTH_SHORT).show();
            return;
        }
        if (grade.equals("")) {
            Toast.makeText(this, "You need to input a student last name", Toast.LENGTH_SHORT).show();
            return;
        }
        Intent returnIntent = new Intent();
        returnIntent.putExtra("courseId", courseId);
        returnIntent.putExtra("studentId",studentId);
        returnIntent.putExtra("grade", grade);
        setResult(Activity.RESULT_OK, returnIntent);
        finish();
    }
}
