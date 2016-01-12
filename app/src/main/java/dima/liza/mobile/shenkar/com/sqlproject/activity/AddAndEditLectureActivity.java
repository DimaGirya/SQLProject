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

public class AddAndEditLectureActivity extends AppCompatActivity {
    EditText editTextLectureId,editTextLastName,editTextFirstName,editTexAddress;
    DataAccess dataAccess;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_and_edit_lecture);
        editTextLectureId = (EditText) findViewById(R.id.editTextLectureId);
        editTextLastName = (EditText) findViewById(R.id.editTextLectureLastName);
        editTextFirstName = (EditText) findViewById(R.id.editTextLectureFirstName);
        editTexAddress = (EditText) findViewById(R.id.editTextLectureAddress);
        Intent intent = getIntent();
        if(intent.getIntExtra(MyConstant.ConstantEntry.MODE,MyConstant.ConstantEntry.MODE_CREATE) == MyConstant.ConstantEntry.MODE_CREATE) {
            String inputLectureId = intent.getStringExtra("lectureId");
            if (!inputLectureId.equals("NO_LECTURE_ID")) {
                editTextLectureId.setText(inputLectureId);
            }
        }else{
            editTextLectureId.setText(intent.getStringExtra("lectureId"));
            editTextFirstName.setText(intent.getStringExtra("lectureFirstName"));
            editTextLastName.setText(intent.getStringExtra("lectureLastName"));
            editTexAddress.setText(intent.getStringExtra("lectureAddress"));
        }
    }

    public void onClickAddOrEditLecture(View view) {
        int flag = 0;
        switch (view.getId()){
            case R.id.buttonAddLecture:{
                flag = 1;
                break;
            }
            case R.id.buttonEditLecture:{
                flag = 2;
                break;
            }
        }
        String lectureId = editTextLectureId.getText().toString();
        String lectureFirstName = editTextFirstName.getText().toString();
        String lectureLastName = editTextLastName.getText().toString();
        String lectureAddress= editTexAddress.getText().toString();

        if (lectureId.equals("")) {
            Toast.makeText(this, "You need to input a lecture ID", Toast.LENGTH_SHORT).show();
            return;
        }
        if (lectureFirstName.equals("")) {
            Toast.makeText(this, "You need to input a lecture first name", Toast.LENGTH_SHORT).show();
            return;
        }
        if (lectureLastName.equals("")) {
            Toast.makeText(this, "You need to input a lecture last name", Toast.LENGTH_SHORT).show();
            return;
        }
        if (lectureAddress.equals("")) {
            Toast.makeText(this, "You need to input a lecture address", Toast.LENGTH_SHORT).show();
            return;
        }
        if(flag == 1) {
            dataAccess = DataAccess.getInstatnce(this);
            if (dataAccess.getLectureById(lectureId) != null) {
                Toast.makeText(this, "The date base has a lectureId  with this ID.Additions impossible", Toast.LENGTH_SHORT).show();
                return;
            }
        }
        Intent returnIntent = new Intent();
        returnIntent.putExtra("lectureId", lectureId);
        returnIntent.putExtra("lectureFirstName",lectureFirstName);
        returnIntent.putExtra("lectureLastName", lectureLastName);
        returnIntent.putExtra("lectureAddress", lectureAddress);
        setResult(Activity.RESULT_OK, returnIntent);
        finish();
    }

}
