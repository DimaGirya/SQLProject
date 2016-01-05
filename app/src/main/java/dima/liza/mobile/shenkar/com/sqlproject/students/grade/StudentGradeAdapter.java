package dima.liza.mobile.shenkar.com.sqlproject.students.grade;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import dima.liza.mobile.shenkar.com.sqlproject.R;

/**
 * Created by Girya on 1/5/2016.
 */
public class StudentGradeAdapter extends BaseAdapter {
    Context ctx;
    LayoutInflater lInflater;
    List<StudentGrade> studentGrades;

    public StudentGradeAdapter(Context ctx, List<StudentGrade> studentGrades) {
        this.ctx = ctx;
        this.studentGrades = studentGrades;
        lInflater = (LayoutInflater) ctx.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return studentGrades.size();
    }

    @Override
    public Object getItem(int position) {
        return studentGrades.get(position);
    }

    @Override
    public long getItemId(int position) {     //need to change??
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewStudentGradeRow viewStudentGradeRow;
        if(convertView==null){
            convertView = LayoutInflater.from(ctx).inflate(R.layout.student_grades, null);
            TextView studentFirstName = (TextView)convertView.findViewById(R.id.gradeShowStudentFirstName);
            TextView studentLastName = (TextView)convertView.findViewById(R.id.gradeShowStudentLastName);
            TextView studentId = (TextView)convertView.findViewById(R.id.gradeShowStudenId);
            TextView courseName = (TextView)convertView.findViewById(R.id.gradeShowCourse);
            TextView grade = (TextView)convertView.findViewById(R.id.gradeShowStudentGrade);
            viewStudentGradeRow = new ViewStudentGradeRow(studentFirstName,studentLastName,studentId,courseName,grade);
            convertView.setTag(viewStudentGradeRow);
        }
        else
        {
            viewStudentGradeRow = (ViewStudentGradeRow) convertView.getTag();
        }
        viewStudentGradeRow.studentFirstName.setText(studentGrades.get(position).getFirstName());
        viewStudentGradeRow.studentLastName.setText(studentGrades.get(position).getLastName());
        viewStudentGradeRow.studentId.setText(Integer.toString(studentGrades.get(position).getStudentId()));
        viewStudentGradeRow.courseName.setText(studentGrades.get(position).getCourseName());
        viewStudentGradeRow.grade.setText(Integer.toString(studentGrades.get(position).getGrades()));
        return convertView;

    }

}
