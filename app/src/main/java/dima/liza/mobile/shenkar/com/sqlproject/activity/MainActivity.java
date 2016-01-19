package dima.liza.mobile.shenkar.com.sqlproject.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

import dima.liza.mobile.shenkar.com.sqlproject.MyConstant;
import dima.liza.mobile.shenkar.com.sqlproject.Validation;
import dima.liza.mobile.shenkar.com.sqlproject.grade.Grade;
import dima.liza.mobile.shenkar.com.sqlproject.R;
import dima.liza.mobile.shenkar.com.sqlproject.SQL.DataAccess;
import dima.liza.mobile.shenkar.com.sqlproject.courses.Course;
import dima.liza.mobile.shenkar.com.sqlproject.courses.CourseAdapter;
import dima.liza.mobile.shenkar.com.sqlproject.lectures.Lecture;
import dima.liza.mobile.shenkar.com.sqlproject.lectures.LectureAdapter;
import dima.liza.mobile.shenkar.com.sqlproject.students.Student;
import dima.liza.mobile.shenkar.com.sqlproject.students.StudentAdapter;
import dima.liza.mobile.shenkar.com.sqlproject.grade.StudentGrade;
import dima.liza.mobile.shenkar.com.sqlproject.grade.StudentGradeAdapter;

public class MainActivity extends AppCompatActivity {
    String TAG  = "MainActivity";
    String messageErrorStudentId;
    String messageErrorLectureId;
    String messageErrorCourseId;
    String messageErrorGrade;
    String messageErrorYear;
    ListView listView;
    DataAccess dataAccess;
    EditText editTextStudentId;
    EditText editTextCourseId;
    EditText editTextLectureId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dataAccess = DataAccess.getInstatnce(this);
        listView = (ListView) findViewById(R.id.listView);
        editTextStudentId = (EditText) findViewById(R.id.editTextStudentId);
        editTextCourseId = (EditText) findViewById(R.id.editTextCourseId);
        editTextLectureId = (EditText)findViewById(R.id.editTextLectureId) ;
         messageErrorStudentId =  getResources().getString(R.string.messageErrorStudentId);
         messageErrorLectureId = getResources().getString(R.string.messageErrorLectureId);
         messageErrorCourseId =  getResources().getString(R.string.messageErrorCourseId);
         messageErrorGrade =  getResources().getString(R.string.messageErrorGrade);
         messageErrorYear =  getResources().getString(R.string.messageErrorYear);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        Log.d(TAG, "onActivityResult start");
        switch (requestCode){
            case MyConstant.ConstantEntry.REQUEST_ADD_STUDENT:{
                if(resultCode== Activity.RESULT_OK){
                    String strStudentId  = data.getStringExtra("studentId");
                    if(!Validation.intValidation(strStudentId,messageErrorStudentId,this)){
                        return;
                    }
                    int studentId  = Integer.parseInt(strStudentId);
                    String studentFirstName =  data.getStringExtra("studentFirstName");
                    String studentLastName =   data.getStringExtra("studentLastName");
                    String studentAddress =  data.getStringExtra("studentAddress");
                    String studentDateOfBirthday = data.getStringExtra("studentDateOfBirthday");
                    Student newStudent = new Student(studentId,studentFirstName,studentLastName,studentAddress,studentDateOfBirthday);
                    if(dataAccess.addStudent(newStudent)){
                        Toast.makeText(this,"Student add to data base",Toast.LENGTH_LONG).show();
                    }
                    else{
                        Toast.makeText(this,"Student not add to data base,try again",Toast.LENGTH_LONG).show();
                    }
                }
                break;
            }
            case MyConstant.ConstantEntry.REQUEST_ADD_LECTURE:{
                if(resultCode== Activity.RESULT_OK){
                    String strLectureId  = data.getStringExtra("lectureId");
                    if(!Validation.intValidation(strLectureId,messageErrorLectureId,this)){
                        return;
                    }
                    int lectureId  = Integer.parseInt(strLectureId);
                    String lectureFirstName =  data.getStringExtra("lectureFirstName");
                    String lectureLastName =   data.getStringExtra("lectureLastName");
                    String lectureAddress =  data.getStringExtra("lectureAddress");
                    Lecture newLecture = new Lecture(lectureId,lectureFirstName,lectureLastName,lectureAddress);
                    if(dataAccess.addLecture(newLecture)){
                        Toast.makeText(this,"Lecture add to data base",Toast.LENGTH_LONG).show();
                    }
                    else{
                        Toast.makeText(this,"Lecture not add to data base,try again",Toast.LENGTH_LONG).show();
                    }
                }
                break;
            }
            case MyConstant.ConstantEntry.REQUEST_ADD_COURSE:{
                if(resultCode== Activity.RESULT_OK){
                    String strCourseId  = data.getStringExtra("courseId");
                    if(!Validation.intValidation(strCourseId,messageErrorCourseId,this)){
                        return;
                    }
                    int courseId  = Integer.parseInt(strCourseId);
                    String courseName  = data.getStringExtra("courseName");
                    String courseSemester  = data.getStringExtra("courseSemester");
                    String strCourseYear  = data.getStringExtra("courseYear");
                    if(!Validation.intValidation(strCourseYear,messageErrorYear,this)){
                        return;
                    }
                    int courseYear  = Integer.parseInt(strCourseYear);
                    String strLectureId  = data.getStringExtra("lectureId");
                    if(!Validation.intValidation(strCourseId,messageErrorLectureId,this)){
                        return;
                    }
                    int lectureId  = Integer.parseInt(strLectureId);
                    Course courseGrade = new Course(courseId,courseName,courseSemester,courseYear,lectureId);
                    if(dataAccess.addCourse(courseGrade)){
                        Toast.makeText(this,"Course add to data base",Toast.LENGTH_LONG).show();
                    }
                    else{
                        Toast.makeText(this,"Course not add to data base,try again",Toast.LENGTH_LONG).show();
                    }
                }
                break;
            }
            case MyConstant.ConstantEntry.REQUEST_ADD_GRADE:{
                if(resultCode== Activity.RESULT_OK){
                    String strStudentId  = data.getStringExtra("studentId");
                    if(!Validation.intValidation(strStudentId,messageErrorStudentId,this)){
                        return;
                    }
                    int studentId  = Integer.parseInt(strStudentId);

                    String stCourseId  = data.getStringExtra("courseId");
                    int courseId  = Integer.parseInt(stCourseId);
                    if(!Validation.intValidation(stCourseId,messageErrorCourseId,this)){
                        return;
                    }
                    String strGrade  = data.getStringExtra("grade");
                    if(!Validation.intValidation(strGrade,messageErrorGrade,this)){
                        return;
                    }
                    int grade  = Integer.parseInt(strGrade);
                    if(!Validation.gradeValidation(grade)){
                        Toast.makeText(this,"Grade must by between 0 to 100.",Toast.LENGTH_LONG).show();
                        return;
                    }
                    Grade newGrade = new Grade(studentId,courseId,grade);
                    if(dataAccess.addGrade(newGrade)){
                        Toast.makeText(this,"Grade add to data base.",Toast.LENGTH_LONG).show();
                    }
                    else{
                        Toast.makeText(this,"This student already have grade in this course.",Toast.LENGTH_LONG).show();
                    }
                }
                break;
            }
            case MyConstant.ConstantEntry.REQUEST_EDIT_LECTURE:{
                if(resultCode== Activity.RESULT_OK){
                    String strLectureId  = data.getStringExtra("lectureId");
                    if(!Validation.intValidation(strLectureId,messageErrorLectureId,this)){
                        return;
                    }
                    int lectureId  = Integer.parseInt(strLectureId);
                    String lectureFirstName =  data.getStringExtra("lectureFirstName");
                    String lectureLastName =   data.getStringExtra("lectureLastName");
                    String lectureAddress =  data.getStringExtra("lectureAddress");
                    Lecture newLecture = new Lecture(lectureId,lectureFirstName,lectureLastName,lectureAddress);
                    if(dataAccess.editLecture(newLecture)){
                        Toast.makeText(this,"Lecture edit successfully",Toast.LENGTH_LONG).show();
                    }
                    else{
                        Toast.makeText(this,"Lecture edit fail.No lecture with such id.",Toast.LENGTH_LONG).show();
                    }
                }
                break;
            }
            case MyConstant.ConstantEntry.REQUEST_EDIT_COURSE:{
                if(resultCode== Activity.RESULT_OK){
                    String strCourseId  = data.getStringExtra("courseId");
                    if(!Validation.intValidation(strCourseId,messageErrorCourseId,this)){
                        return;
                    }
                    int courseId  = Integer.parseInt(strCourseId);
                    if(!Validation.intValidation(strCourseId,messageErrorCourseId,this)){
                        return;
                    }
                    String courseName =  data.getStringExtra("courseName");
                    String courseSemester =   data.getStringExtra("courseSemester");
                    String strCourseYear =  data.getStringExtra("courseYear");
                    String strLectureId =  data.getStringExtra("lectureId");
                    if(!Validation.intValidation(strCourseYear,messageErrorYear,this)){
                        return;
                    }
                    if(!Validation.intValidation(strLectureId,messageErrorLectureId,this)){
                        return;
                    }
                    int courseYear  = Integer.parseInt(strCourseYear);
                    int lectureId  = Integer.parseInt(strLectureId);
                    Course course = new Course(courseId,courseName,courseSemester,courseYear,lectureId);
                    if(dataAccess.editCourse(course)){
                        Toast.makeText(this,"Course edit successfully",Toast.LENGTH_LONG).show();
                    }
                    else{
                        Toast.makeText(this,"Course edit fail.No course with such id.",Toast.LENGTH_LONG).show();
                    }
                }
                break;
            }
            case MyConstant.ConstantEntry.REQUEST_EDIT_STUDENT:{
                if(resultCode== Activity.RESULT_OK){

                    String strStudentId  = data.getStringExtra("studentId");
                    if(!Validation.intValidation(strStudentId,messageErrorStudentId,this)){
                        return;
                    }
                    int studentId  = Integer.parseInt(strStudentId);
                    String studentFirstName =  data.getStringExtra("studentFirstName");
                    String studentLastName =   data.getStringExtra("studentLastName");
                    String studentAddress =  data.getStringExtra("studentAddress");
                    String studentDateOfBirthday =   data.getStringExtra("studentDateOfBirthday");
                    Student student = new Student(studentId,studentFirstName,studentLastName,studentAddress,studentDateOfBirthday);
                    if(dataAccess.editStudent(student)){
                        Toast.makeText(this,"Student edit successfully.",Toast.LENGTH_LONG).show();
                    }
                    else{
                        Toast.makeText(this,"Student edit fail.No Student with such id.",Toast.LENGTH_LONG).show();
                    }
                }
                break;
            }
            case MyConstant.ConstantEntry.REQUEST_EDIT_GRADE:{
                if(resultCode== Activity.RESULT_OK){
                    String strStudentId  = data.getStringExtra("studentId");
                    if(!Validation.intValidation(strStudentId,messageErrorStudentId,this)){
                        return;
                    }
                    int studentId  = Integer.parseInt(strStudentId);
                    String strCourseId  = data.getStringExtra("courseId");
                    if(!Validation.intValidation(strCourseId,messageErrorCourseId,this)){
                        return;
                    }
                    int courseId  = Integer.parseInt(strCourseId);
                    String strGrade  = data.getStringExtra("grade");
                    if(!Validation.intValidation(strGrade,messageErrorGrade,this)){
                        return;
                    }
                    int grade  = Integer.parseInt(strGrade);
                    if(!Validation.gradeValidation(grade)){
                        Toast.makeText(this,"Grade must by between 0 to 100.",Toast.LENGTH_LONG).show();
                        return;
                    }
                    Grade gradeEdit = new Grade(studentId,courseId,grade);
                    if(dataAccess.editGrade(gradeEdit)){
                        Toast.makeText(this,"Grade edit successfully.",Toast.LENGTH_LONG).show();
                    }
                    else{
                        Toast.makeText(this,"Grade edit fail.No such  grade in data base.",Toast.LENGTH_LONG).show();
                    }
                }
                break;
            }
        }

    }



    public void onClickButton(View view) {
        switch (view.getId()){
            case R.id.buttonGetStudentsList:{
                Log.i(TAG,"buttonGetStudentsList click");
                List<Student> students = dataAccess.getAllStudents();
                StudentAdapter studentAdapter = new StudentAdapter(this,students);
                listView.setAdapter(studentAdapter);
                break;
            }
            case R.id.buttonGetLecturesList:{
                Log.i(TAG,"buttonGetLecturesList click");
                List<Lecture> lectures = dataAccess.getAllLecture();
                LectureAdapter lectureAdapter = new LectureAdapter(this,lectures);
                listView.setAdapter(lectureAdapter);
                break;
            }
            case R.id.buttonGetCoursesList:{
                Log.i(TAG,"buttonGetCoursesList click");
                List<Course> lectures = dataAccess.getAllCourses();
                CourseAdapter courseAdapter = new CourseAdapter(this,lectures);
                listView.setAdapter(courseAdapter);
                break;
            }
            case R.id.buttonAddStudent:{
                Log.i(TAG, "buttonAddStudent click");
                Intent intent = new Intent(this,AddAndEditStudentActivity.class);
                intent.putExtra("mode",MyConstant.ConstantEntry.MODE_CREATE);
                String studentId = editTextStudentId.getText().toString();
                if (studentId.equals("")) {
                    studentId = "NO_STUDENT_ID";
                }else{
                    if(!Validation.intValidation(studentId,messageErrorStudentId,this)){
                        return;
                    }
                }
                intent.putExtra("studentId", studentId);
                startActivityForResult(intent, MyConstant.ConstantEntry.REQUEST_ADD_STUDENT);
                break;
            }
            case R.id.buttonAddCourse:{
                Log.i(TAG,"buttonAddCourse click");
                Intent intent = new Intent(this,AddAndEditCourseActivity.class);
                intent.putExtra("mode",MyConstant.ConstantEntry.MODE_CREATE);
                String lectureId = editTextLectureId.getText().toString();
                if (lectureId.equals("")) {
                    lectureId = "NO_LECTURE_ID";
                }else{
                    if(!Validation.intValidation(lectureId,messageErrorLectureId,this)){
                        return;
                    }
                }
                intent.putExtra("lectureId", lectureId);
                String courseId = editTextCourseId.getText().toString();
                if (courseId.equals("")) {
                    courseId = "NO_COURSE_ID";
                }else{
                    if(!Validation.intValidation(courseId,messageErrorCourseId,this)){
                        return;
                    }
                }
                intent.putExtra("courseId",courseId);
                startActivityForResult(intent, MyConstant.ConstantEntry.REQUEST_ADD_COURSE);
                break;
            }
            case R.id.buttonAddLecture:{
                Log.i(TAG,"buttonAddLecture click");
                Intent intent = new Intent(this,AddAndEditLectureActivity.class);
                intent.putExtra("mode",MyConstant.ConstantEntry.MODE_CREATE);
                String lectureId = editTextLectureId.getText().toString();
                if (lectureId.equals("")) {
                    lectureId = "NO_LECTURE_ID";
                }else{
                    if(!Validation.intValidation(lectureId,messageErrorLectureId,this)){
                        return;
                    }
                }
                intent.putExtra("lectureId", lectureId);
                startActivityForResult(intent, MyConstant.ConstantEntry.REQUEST_ADD_LECTURE);
                break;
            }
            case R.id.buttonAddGrade:{
                Log.i(TAG,"buttonAddGrade click");
                Intent intent = new Intent(this,AddAndEditGradeActivity.class);
                intent.putExtra("mode",MyConstant.ConstantEntry.MODE_CREATE);
                String studentId = editTextStudentId.getText().toString();
                if (studentId.equals("")) {
                    studentId = "NO_STUDENT_ID";
                }else{
                    if(!Validation.intValidation(studentId,messageErrorStudentId,this)){
                        return;
                    }
                }
                intent.putExtra("studentId", studentId);
                String courseId = editTextCourseId.getText().toString();
                if (courseId.equals("")) {
                    courseId = "NO_COURSE_ID";
                }else{
                    if(!Validation.intValidation(courseId,messageErrorCourseId,this)){
                        return;
                    }
                }
                intent.putExtra("courseId", courseId);
                startActivityForResult(intent, MyConstant.ConstantEntry.REQUEST_ADD_GRADE);

                break;
            }
            case R.id.buttonRemoveCourse:{
                Log.i(TAG, "buttonRemoveCourse click");
                    String strCourseId = editTextCourseId.getText().toString();
                    if(!Validation.intValidation(strCourseId,messageErrorCourseId,this)){
                        return;
                    }
                    int courseId = Integer.parseInt(strCourseId);
                    if(dataAccess.removeCourse(courseId)){
                        Toast.makeText(this,"Course remove succeed",Toast.LENGTH_LONG).show();
                    }
                    else{
                        Toast.makeText(this,"Course remove unsuccessful.No such course Id",Toast.LENGTH_LONG).show();
                    }
                break;
            }
            case R.id.buttonRemoveGrade:{
                   Log.i(TAG, "buttonRemoveGrade click");
                    String strStudentId = editTextStudentId.getText().toString();
                    String strCourseId = editTextCourseId.getText().toString();
                    if(!Validation.intValidation(strStudentId,messageErrorStudentId,this)){
                        return;
                    }
                    if(!Validation.intValidation(strCourseId,messageErrorCourseId,this)){
                        return;
                    }
                    int studentId = Integer.parseInt(strStudentId);
                    int courseId = Integer.parseInt(strCourseId);
                    if(dataAccess.removeGrade(studentId, courseId)){
                        Toast.makeText(this,"Grade remove succeed",Toast.LENGTH_LONG).show();
                    }
                    else{
                        Toast.makeText(this,"Grade remove unsuccessful.No such grade to remove",Toast.LENGTH_LONG).show();
                    }

                break;
            }
            case R.id.buttonRemoveLecture:{
                Log.i(TAG, "buttonRemoveLecture click");
                String strLectureId = editTextLectureId.getText().toString();
                 if(!Validation.intValidation(strLectureId,messageErrorLectureId,this)){
                    return;
                  }
                    int lectureId = Integer.parseInt(strLectureId);
                    if(dataAccess.removeLecture(lectureId)){
                        Toast.makeText(this,"Lecture remove succeed",Toast.LENGTH_LONG).show();
                    }
                    else{
                        Toast.makeText(this,"Lecture remove unsuccessful.No such lecture Id",Toast.LENGTH_LONG).show();
                    }
                break;
            }
            case R.id.buttonRemoveStudent:{
                Log.i(TAG, "buttonRemoveStudent click");
                    String strStudentId = editTextStudentId.getText().toString() ;
                    if(!Validation.intValidation(strStudentId,messageErrorStudentId,this)){
                        return;
                    }
                    int studentId = Integer.parseInt(strStudentId);
                    if(dataAccess.removeStudent(studentId)){
                        Toast.makeText(this,"Student remove succeed",Toast.LENGTH_LONG).show();
                    }
                    else{
                        Toast.makeText(this,"Student remove unsuccessful.No such student Id",Toast.LENGTH_LONG).show();
                    }

                break;
            }
            case R.id.buttonEditGrade:{
                Log.i(TAG, "buttonEditGrade click");

                Intent intent = new Intent(this,AddAndEditGradeActivity.class);
                intent.putExtra(MyConstant.ConstantEntry.MODE,MyConstant.ConstantEntry.MODE_EDIT);

                String courseId  = editTextCourseId.getText().toString();
                if(courseId.equals("")){
                    Toast.makeText(this,"Id course field is empty",Toast.LENGTH_LONG).show();
                    return;
                }
                if(!Validation.intValidation(courseId, messageErrorCourseId, this)){
                    return;
                }
                String studentId = editTextStudentId.getText().toString();
                if(!Validation.intValidation(studentId, messageErrorStudentId, this)){
                    return;
                }
                Grade grade = dataAccess.getGradeById(courseId,studentId);
                if(grade==null){
                    Toast.makeText(this,"No such grade in data base",Toast.LENGTH_LONG).show();
                    return;
                }
                intent.putExtra("studentId",Integer.toString(grade.getStudentId()));
                intent.putExtra("courseId",Integer.toString(grade.getCourseId()));
                intent.putExtra("grade",Integer.toString(grade.getGrade()));

                startActivityForResult(intent, MyConstant.ConstantEntry.REQUEST_EDIT_GRADE);
                break;
            }
            case R.id.buttonEditLecture:{
                Log.i(TAG, "buttonEditLecture click");
                Intent intent = new Intent(this,AddAndEditLectureActivity.class);
                intent.putExtra(MyConstant.ConstantEntry.MODE, MyConstant.ConstantEntry.MODE_EDIT);

                String lectureId = editTextLectureId.getText().toString();
                if(!Validation.intValidation(lectureId, messageErrorLectureId, this)){
                    return;
                }
                Lecture lecture = dataAccess.getLectureById(lectureId);
                if(lecture==null){
                    Toast.makeText(this,"No such lecture in data base",Toast.LENGTH_LONG).show();
                    return;
                }
                intent.putExtra(MyConstant.ConstantEntry.MODE,MyConstant.ConstantEntry.MODE_EDIT);
                intent.putExtra("lectureId",Integer.toString(lecture.getLectureId()));
                intent.putExtra("lectureFirstName",lecture.getFirstName());
                intent.putExtra("lectureLastName",lecture.getLastName());
                intent.putExtra("lectureAddress",lecture.getAddress());
                startActivityForResult(intent, MyConstant.ConstantEntry.REQUEST_EDIT_LECTURE);
                break;
            }
            case R.id.buttonEditCourse:{
                Log.i(TAG,"buttonEditCourse click");
                Intent intent = new Intent(this,AddAndEditCourseActivity.class);
                intent.putExtra(MyConstant.ConstantEntry.MODE,MyConstant.ConstantEntry.MODE_EDIT);


                String courseId = editTextCourseId.getText().toString();
                if(!Validation.intValidation(courseId, messageErrorCourseId, this)){
                    return;
                }
                Course course = dataAccess.getCourseById(courseId);
                if(course==null){
                    Toast.makeText(this,"No such course in data base",Toast.LENGTH_LONG).show();
                    return;
                }
                intent.putExtra(MyConstant.ConstantEntry.MODE,MyConstant.ConstantEntry.MODE_EDIT);
                intent.putExtra("courseId",Integer.toString(course.getCourseId()));
                intent.putExtra("courseName",course.getCourseName());
                intent.putExtra("courseSemester", course.getSemester());
                intent.putExtra("courseLectureId",Integer.toString(course.getLectureId()));
                intent.putExtra("courseYear",Integer.toString(course.getYear()));
                startActivityForResult(intent, MyConstant.ConstantEntry.REQUEST_EDIT_COURSE);
                break;
            }
            case R.id.buttonEditStudent:{
                Log.i(TAG,"buttonEditStudent click");
                Intent intent = new Intent(this,AddAndEditStudentActivity.class);
                intent.putExtra(MyConstant.ConstantEntry.MODE, MyConstant.ConstantEntry.MODE_EDIT);

                String studentId = editTextStudentId.getText().toString();
                if(!Validation.intValidation(studentId, messageErrorStudentId, this)){
                    return;
                }
                Student student = dataAccess.getStudentById(studentId);
                if(student==null){
                    Toast.makeText(this,"No such student in data base",Toast.LENGTH_LONG).show();
                    return;
                }

                intent.putExtra(MyConstant.ConstantEntry.MODE,MyConstant.ConstantEntry.MODE_EDIT);
                intent.putExtra("studentId",Integer.toString(student.getStudentId()));
                intent.putExtra("firstName",student.getFirstName());
                intent.putExtra("lastName", student.getLastName());
                intent.putExtra("address",student.getAddress());
                intent.putExtra("dateOfBirth", student.getDateOfBirth());

                startActivityForResult(intent, MyConstant.ConstantEntry.REQUEST_EDIT_STUDENT);
                break;
            }
            case R.id.buttonShowGradesOfStudent:{
                Log.i(TAG,"buttonShowGradesOfStudent click");
                String studentId = editTextStudentId.getText().toString();
                if(!Validation.intValidation(studentId,messageErrorStudentId,this)){
                    return;
                }
                List<StudentGrade> studentGrade = dataAccess.getStudentGrades(studentId);
                StudentGradeAdapter studentGradeAdapter = new StudentGradeAdapter(this,studentGrade);
                listView.setAdapter(studentGradeAdapter);
                break;
            }
            case R.id.buttonTop3Students:{
                Log.i(TAG,"buttonTop3Students click");
                String courseId = editTextCourseId.getText().toString();
                if(!Validation.intValidation(courseId, messageErrorCourseId, this)){
                    return;
                }
                List<StudentGrade> studentGrade = dataAccess.getTopOrBottomStudent(courseId, MyConstant.ConstantEntry.TOP);
                StudentGradeAdapter studentGradeAdapter = new StudentGradeAdapter(this,studentGrade);
                listView.setAdapter(studentGradeAdapter);
                break;
            }
            case R.id.buttonBottom3Students:{
                Log.i(TAG, "buttonBottom3Students click");
                String courseId = editTextCourseId.getText().toString();
                if(!Validation.intValidation(courseId, messageErrorCourseId, this)){
                    return;
                }
                List<StudentGrade> studentGrade = dataAccess.getTopOrBottomStudent(courseId, MyConstant.ConstantEntry.BOTTOM);
                StudentGradeAdapter studentGradeAdapter = new StudentGradeAdapter(this,studentGrade);
                listView.setAdapter(studentGradeAdapter);
                break;
            }
            default:{
                Log.e(TAG, "Error in press button");
                break;
            }
        }
    }
}
