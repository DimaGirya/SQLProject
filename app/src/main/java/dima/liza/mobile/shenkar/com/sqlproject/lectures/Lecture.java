package dima.liza.mobile.shenkar.com.sqlproject.lectures;

/**
 * Created by Girya on 12/28/2015.
 */
public class Lecture {
    private int lectureId;
    private String lastName;
    private String firstName;
    private String address;

    public Lecture(int lectureId, String lastName, String address, String firstName) {
        this.lectureId = lectureId;
        this.lastName = lastName;
        this.address = address;
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getLectureId() {
        return lectureId;
    }

    public void setLectureId(int lectureId) {
        this.lectureId = lectureId;
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
}
/*
LectureID int NOT NULL PRIMARY KEY,
LastName varchar(255) NOT NULL,
FirstName varchar(255) NOT NULL,
Address varchar(255)
 */