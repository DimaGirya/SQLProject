package dima.liza.mobile.shenkar.com.sqlproject;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Girya on 12/31/2015.
 */
public class StudentAdapter extends BaseAdapter {
    Context ctx;
    LayoutInflater lInflater;
    List<Student> students;

    public StudentAdapter(Context context, List<Student> students) {
        ctx = context;
        this.students = students;
        lInflater = (LayoutInflater) ctx.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }
    @Override
    public int getCount() {
        return students.size();
    }

    @Override
    public Object getItem(int position) {
        return students.get(position);
    }

    @Override
    public long getItemId(int position) {     //need to change??
        return position;
    }
    Student getStudent(int position) {
        return ((Student) getItem(position));
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if (view == null) {
            view = lInflater.inflate(R.layout.students_list, parent, false);
        }

        Student student = getStudent(position);

        ((TextView) view.findViewById(R.id.textViewStudentID)).setText(student.getStudentId());
        ((TextView) view.findViewById(R.id.textViewFirstNameStudent)).setText(student.getFirstName());
        ((TextView) view.findViewById(R.id.textViewLastNameStudent)).setText(student.getLastName());
        ((TextView) view.findViewById(R.id.textViewAddressStudent)).setText(student.getAddress());
        ((TextView) view.findViewById(R.id.textViewDateOfBirthStudent)).setText(student.getDateOfBirth());

        return view;
    }
}
/*
 private int studentId;
    private String firstName;
    private String lastName;
    private String address;
    private String dateOfBirth;
 */