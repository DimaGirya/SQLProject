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

public class AddAndEditStudentActivity extends AppCompatActivity {
    EditText editTextStudentId;
    EditText editTextStudentFirsName;
    EditText editTextStudentLastName;
    EditText editTextStudentAddress;
    EditText editTextDateOfBirthday;
    DataAccess dataAccess;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_and_edit_student);
        editTextStudentId = (EditText)findViewById(R.id.editTextAddStudentId);
        editTextStudentFirsName = (EditText)findViewById(R.id.editTextAddStudentFirstName);
        editTextStudentLastName = (EditText)findViewById(R.id.editTextAddStudentLastName);
        editTextStudentAddress = (EditText)findViewById(R.id.editTextAddStudentAddress) ;
        editTextDateOfBirthday = (EditText)findViewById(R.id.editTextStudentDateOfBirhday);
        Intent intent = getIntent();
        if(intent.getIntExtra(MyConstant.ConstantEntry.MODE,MyConstant.ConstantEntry.MODE_CREATE) == MyConstant.ConstantEntry.MODE_CREATE) {
            String inputStudentId = intent.getStringExtra("studentId");
            if (!inputStudentId.equals("NO_STUDENT_ID")) {
                editTextStudentId.setText(inputStudentId);
            }
        }
        else{

            editTextStudentId.setText(intent.getStringExtra("studentId"));
            editTextStudentFirsName.setText(intent.getStringExtra("firstName"));
            editTextStudentLastName.setText(intent.getStringExtra("lastName"));
            editTextStudentAddress.setText(intent.getStringExtra("address"));
            editTextDateOfBirthday.setText(intent.getStringExtra("dateOfBirth"));
        }
    }

    public void onClickAddOrEditStudent(View view) {
        int flag = 0;
        switch (view.getId()){
            case R.id.buttonAddStudent:{
                flag = 1;
                break;
            }
            case R.id.buttonEditStudent:{
                flag = 2;
                break;
            }
        }
        String studentId = editTextStudentId.getText().toString();
        String studentFirstName = editTextStudentFirsName.getText().toString();
        String studentLastName = editTextStudentLastName.getText().toString();
        String studentAddress = editTextStudentAddress.getText().toString();
        String studentDateOfBirthday = editTextDateOfBirthday.getText().toString();
        if (studentId.equals("")) {
            Toast.makeText(this, "You need to input a student ID", Toast.LENGTH_SHORT).show();
            return;
        }
        if (studentFirstName.equals("")) {
            Toast.makeText(this, "You need to input a student first name", Toast.LENGTH_SHORT).show();
            return;
        }
        if (studentLastName.equals("")) {
            Toast.makeText(this, "You need to input a student last name", Toast.LENGTH_SHORT).show();
            return;
        }
        if (studentAddress.equals("")) {
            Toast.makeText(this, "You need to input a student address", Toast.LENGTH_SHORT).show();
            return;
        }  if (studentDateOfBirthday.equals("")) {
            Toast.makeText(this, "You need to input a student date of birthday", Toast.LENGTH_SHORT).show();
            return;
        }
        if(flag == 1) {
            dataAccess = DataAccess.getInstatnce(this);
                  if (dataAccess.getStudentById(studentId) != null) {
                      Toast.makeText(this, "The date base has a student  with this ID.Additions impossible", Toast.LENGTH_SHORT).show();
                      return;
              }
        }
        Intent returnIntent = new Intent();
        returnIntent.putExtra("studentId", studentId);
        returnIntent.putExtra("studentFirstName",studentFirstName);
        returnIntent.putExtra("studentLastName", studentLastName);
        returnIntent.putExtra("studentAddress", studentAddress);
        returnIntent.putExtra("studentDateOfBirthday", studentDateOfBirthday);
        setResult(Activity.RESULT_OK, returnIntent);
        finish();
    }


}
