package dima.liza.mobile.shenkar.com.sqlproject.courses;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import dima.liza.mobile.shenkar.com.sqlproject.R;
import dima.liza.mobile.shenkar.com.sqlproject.activity.MainActivity;
import dima.liza.mobile.shenkar.com.sqlproject.lectures.ViewLectureRow;

/**
 * Created by Girya on 12/31/2015.
 */
public class CourseAdapter extends BaseAdapter {
    Context ctx;
    LayoutInflater lInflater;
    List<Course> courses;

    public CourseAdapter(Context context,   List<Course> courses) {
        ctx = context;
        this.courses = courses;
        lInflater = (LayoutInflater) ctx.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return courses.size();
    }

    @Override
    public Object getItem(int position) {
        return courses.get(position);
    }

    @Override
    public long getItemId(int position) {     //need to change??
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewCourseRow viewCourseRow;
        if(convertView==null){
            convertView = LayoutInflater.from(ctx).inflate(R.layout.course_list, null);
            TextView courseID = (TextView)convertView.findViewById(R.id.textViewCourseId);
            TextView courseName = (TextView)convertView.findViewById(R.id.textViewCourseName);
            TextView courseSemester = (TextView)convertView.findViewById(R.id.textViewCourseSemester);
            TextView courseYear = (TextView)convertView.findViewById(R.id.textViewCourseYear);
            TextView courseLectureId = (TextView)convertView.findViewById(R.id.textViewCourseLectureId);
            viewCourseRow = new ViewCourseRow(courseID,courseName,courseSemester,courseYear,courseLectureId);
            convertView.setTag(viewCourseRow);
        }
        else
        {
            viewCourseRow = (ViewCourseRow) convertView.getTag();
        }
        int idCourse = courses.get(position).getCourseId();
        String idCourseStr = Integer.toString(idCourse);
        viewCourseRow.courseID.setText(idCourseStr);
        viewCourseRow.courseName.setText(courses.get(position).getCourseName());
        viewCourseRow.courseSemester.setText(courses.get(position).getSemester());
        int idYear = courses.get(position).getYear();
        String courseYear = Integer.toString(idYear);
        viewCourseRow.courseYear.setText(courseYear);

        int idLecture = courses.get(position).getLectureId();
        String idLectureStr = Integer.toString(idLecture);
        viewCourseRow.courseLectureId.setText(idLectureStr);
        return convertView;

    }
}
