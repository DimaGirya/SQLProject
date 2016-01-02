package dima.liza.mobile.shenkar.com.sqlproject;

import android.widget.TextView;

/**
 * Created by Girya on 12/28/2015.
 */
public class Student {
    private int studentId;
    private String firstName;
    private String lastName;
    private String address;
    private String dateOfBirth;

    public Student(int studentId,String firstName, String lastName, String address, String dateOfBirth) {
        this.firstName = firstName;
        this.studentId = studentId;
        this.lastName = lastName;
        this.address = address;
        this.dateOfBirth = dateOfBirth;
    }

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }
}




/*
StudentId int NOT NULL PRIMARY KEY,
LastName varchar(255) NOT NULL,
FirstName varchar(255) NOT NULL,
Address varchar(255),
DateOfBirth varchar(255)
 */