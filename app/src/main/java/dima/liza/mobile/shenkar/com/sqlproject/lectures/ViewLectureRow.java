package dima.liza.mobile.shenkar.com.sqlproject.lectures;

import android.widget.TextView;

/**
 * Created by Girya on 1/4/2016.
 */
public class ViewLectureRow {
    TextView lectureId;
    TextView firstName;
    TextView lastName;
    TextView address;

    public ViewLectureRow(TextView lectureId, TextView firstName, TextView lastName, TextView address) {
        this.lectureId = lectureId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
    }
}
