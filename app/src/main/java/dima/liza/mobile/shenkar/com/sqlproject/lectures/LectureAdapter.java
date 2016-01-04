package dima.liza.mobile.shenkar.com.sqlproject.lectures;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import dima.liza.mobile.shenkar.com.sqlproject.Course;
import dima.liza.mobile.shenkar.com.sqlproject.R;
import dima.liza.mobile.shenkar.com.sqlproject.activity.MainActivity;
import dima.liza.mobile.shenkar.com.sqlproject.students.ViewRowStudent;

/**
 * Created by Girya on 12/31/2015.
 */
public class LectureAdapter extends BaseAdapter {
    private static final String TAG = "LectureAdapter";
    Context ctx;
    LayoutInflater lInflater;
    List<Lecture> lectures;

    public LectureAdapter(Context context, List<Lecture> lectures) {
        ctx = context;
        this.lectures = lectures;
        lInflater = (LayoutInflater) ctx.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewLectureRow viewLectureRow;
        if(convertView==null){
            convertView = LayoutInflater.from(ctx).inflate(R.layout.lectures_list, null);
            TextView lectureId = (TextView)convertView.findViewById(R.id.textViewLectureID);
            TextView firstName = (TextView)convertView.findViewById(R.id.textViewLectureFirstName);
            TextView lastName = (TextView)convertView.findViewById(R.id.textViewLectureLastName);
            TextView address = (TextView)convertView.findViewById(R.id.textViewLectureAddress);
            viewLectureRow = new ViewLectureRow(lectureId,firstName,lastName,address);
            convertView.setTag(viewLectureRow);
        }
        else
        {
            viewLectureRow = (ViewLectureRow) convertView.getTag();
        }
        int id = lectures.get(position).getLectureId();
        String idStr = Integer.toString(id);
        viewLectureRow.lectureId.setText(idStr);
        viewLectureRow.firstName.setText(lectures.get(position).getFirstName());
        viewLectureRow.lastName.setText(lectures.get(position).getLastName());
        viewLectureRow.address.setText(lectures.get(position).getAddress());
        return convertView;
    }


    @Override
    public int getCount() {
        return lectures.size();
    }

    @Override
    public Object getItem(int position) {
        return lectures.get(position);
    }

    @Override
    public long getItemId(int position) {   //need to change??
        return position;
    }


}
