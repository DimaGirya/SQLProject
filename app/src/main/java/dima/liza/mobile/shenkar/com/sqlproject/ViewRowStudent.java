package dima.liza.mobile.shenkar.com.sqlproject;

import android.widget.TextView;

/**
 * Created by Girya on 1/2/2016.
 */
public class ViewRowStudent {

    TextView studentId;
    TextView firstName;
    TextView lastName;
    TextView address;
    TextView dateOfBirth;

    public ViewRowStudent(TextView studentId, TextView firstName, TextView lastName, TextView address, TextView dateOfBirth) {
        this.studentId = studentId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.dateOfBirth = dateOfBirth;
    }
}
