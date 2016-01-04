package dima.liza.mobile.shenkar.com.sqlproject.courses;

import android.widget.TextView;

/**
 * Created by Girya on 1/4/2016.
 */
public class ViewCourseRow {
    TextView courseID;
    TextView courseName;
    TextView courseSemester;
    TextView courseYear;
    TextView courseLectureId;

    public ViewCourseRow(TextView courseID, TextView courseLectureId, TextView courseName, TextView courseSemester, TextView courseYear) {
        this.courseID = courseID;
        this.courseLectureId = courseLectureId;
        this.courseName = courseName;
        this.courseSemester = courseSemester;
        this.courseYear = courseYear;
    }
}
