package com.flipkart.bean;

public class Course {
    public Course(){

    }

    public Course(String courseCode, String courseName, String instructorId, int seats, double fee) {
        this.courseCode = courseCode;
        this.courseName = courseName;
        this.instructorId = instructorId;
        this.seats = seats;
        this.fee = fee;
    }

    public void display()
    {
        System.out.println(this.courseCode+" "+this.courseName);
    }
    public String getCourseCode() {
        return courseCode;
    }

    public void setCourseCode(String courseCode) {
        this.courseCode = courseCode;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getInstructorId() {
        return instructorId;
    }

    public void setInstructorId(String instructorId) {
        this.instructorId = instructorId;
    }

    private String courseCode;
    private String courseName;
    private String instructorId;

    public double getFee() {
        return fee;
    }

    public void setFee(double fee) {
        this.fee = fee;
    }

    private double fee;

    public int getSeats() {
        return seats;
    }

    public void setSeats(int seats) {
        this.seats = seats;
    }

    @Override
    public boolean equals(Object course){
        if(this==course) return true;
        if(!(course instanceof Course)) return false;
        return this.getCourseCode().equals(((Course)course).getCourseCode());
    }

    private int seats = 10;
}
