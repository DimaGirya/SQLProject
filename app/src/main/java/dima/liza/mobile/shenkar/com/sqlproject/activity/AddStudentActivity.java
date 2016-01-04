package dima.liza.mobile.shenkar.com.sqlproject.activity;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import dima.liza.mobile.shenkar.com.sqlproject.R;

public class AddStudentActivity extends AppCompatActivity {
    EditText editTextStudentId;
    EditText editTextStudentFirsName;
    EditText editTextStudentLastName;
    EditText editTextStudentAddress;
    EditText editTextDateOfBirthday;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_student);
        editTextStudentId = (EditText)findViewById(R.id.editTextAddStudentId);
        editTextStudentFirsName = (EditText)findViewById(R.id.editTextAddStudentFirstName);
        editTextStudentLastName = (EditText)findViewById(R.id.editTextAddStudentLastName);
        editTextStudentAddress = (EditText)findViewById(R.id.editTextAddStudentAddress) ;
        editTextDateOfBirthday = (EditText)findViewById(R.id.editTextStudentDateOfBirhday);
    }

    public void onClickAddStudent(View view) {
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
