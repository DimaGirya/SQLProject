package dima.liza.mobile.shenkar.com.sqlproject.activity;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import dima.liza.mobile.shenkar.com.sqlproject.R;

public class AddLectureActivity extends AppCompatActivity {
    EditText editTextLectureId,editTextLastName,editTextFirstName,editTexAddress;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_lecture);
        editTextLectureId = (EditText) findViewById(R.id.editTextLectureId);
        editTextLastName = (EditText) findViewById(R.id.editTextLectureLastName);
        editTextFirstName = (EditText) findViewById(R.id.editTextLectureFirstName);
        editTexAddress = (EditText) findViewById(R.id.editTextLectureAddress);
    }

    public void onCLickAddLecture(View view) {
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
        Intent returnIntent = new Intent();
        returnIntent.putExtra("lectureId", lectureId);
        returnIntent.putExtra("lectureFirstName",lectureFirstName);
        returnIntent.putExtra("lectureLastName", lectureLastName);
        returnIntent.putExtra("lectureAddress", lectureAddress);
        setResult(Activity.RESULT_OK, returnIntent);
        finish();
    }
}
