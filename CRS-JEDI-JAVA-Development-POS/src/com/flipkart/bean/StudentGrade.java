package com.flipkart.bean;

public class StudentGrade {
    private String studentID;
    private String courseID;
    private String courseName;
    private String grade;

//    public StudentGrade() {
//    }

//    public StudentGrade(String studentID, String courseID, String courseName, String grade) {
//        this.studentID = studentID;
//        this.courseID = courseID;
//        this.courseName = courseName;
//        this.grade = grade;
//    }

    public String getStudentID() {
        return studentID;
    }

    public void setStudentID(String studentID) {
        this.studentID = studentID;
    }

    public String getCourseID() {
        return courseID;
    }

    public void setCourseID(String courseID) {
        this.courseID = courseID;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }
}