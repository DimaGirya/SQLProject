package dima.liza.mobile.shenkar.com.sqlproject.grade;

import android.widget.TextView;

/**
 * Created by Girya on 1/5/2016.
 */
public class ViewStudentGradeRow {
    TextView studentFirstName;
    TextView studentLastName;
    TextView studentId;
    TextView courseName;
    TextView grade;

    public ViewStudentGradeRow(TextView studentFirstName, TextView studentLastName, TextView studentId, TextView courseName, TextView grade) {
        this.studentFirstName = studentFirstName;
        this.studentLastName = studentLastName;
        this.studentId = studentId;
        this.courseName = courseName;
        this.grade = grade;
    }
}
