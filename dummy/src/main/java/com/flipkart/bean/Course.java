package com.flipkart.bean;
/**
 * @author JEDI-Group-C Praneet, Rishabh, Akhil, Manan, Nidhi, Shivanshu, Divyansh
 * Class for Courses
 *
 */
public class Course {
    /**
     * Default constructor
     */
    public Course(){}


    /**
     * Paramterized construction
     * @param courseCode
     * @param courseName
     * @param instructorId
     * @param seats
     * @param fee
     */
    public Course(String courseCode, String courseName, String instructorId, int seats, double fee) {
        this.courseCode = courseCode;
        this.courseName = courseName;
        this.instructorId = instructorId;
        this.seats = seats;
        this.fee = fee;
    }

    /**
     *Print course details
     */
    public void display()
    {
        System.out.println(this.courseCode+" "+this.courseName);
    }

    /**
     * Get course code
     * @return courseCode
     */
    public String getCourseCode() {
        return courseCode;
    }

    /**
     * Set course code
     * @param courseCode
     */
    public void setCourseCode(String courseCode) {
        this.courseCode = courseCode;
    }

    /**
     * Get course name
     * @return courseName
     */
    public String getCourseName() {
        return courseName;
    }

    /**
     * Set course name
     * @param courseName
     */
    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    /**
     * Get Id of Professor
     * @return instructorId
     */
    public String getInstructorId() {
        return instructorId;
    }

    /**
     * Set Id of Professor
     * @param instructorId
     */
    public void setInstructorId(String instructorId) {
        this.instructorId = instructorId;
    }

    private String courseCode;
    private String courseName;
    private String instructorId;

    /**
     * Get Fee of Course
     * @return fee
     */
    public double getFee() {
        return fee;
    }

    /**
     * Set Fee of Course
     * @param fee
     */
    public void setFee(double fee) {
        this.fee = fee;
    }

    private double fee;

    /**
     * Get number of seats
     * @return seats
     */
    public int getSeats() {
        return seats;
    }

    /**
     * Set number of seats
     * @param seats
     */
    public void setSeats(int seats) {
        this.seats = seats;
    }

    /**
     * Equate 2 courses, via object, instance or courseCode
     * @param course
     * @return
     */
    @Override
    public boolean equals(Object course){
        if(this==course) return true;
        if(!(course instanceof Course)) return false;
        return this.getCourseCode().equals(((Course)course).getCourseCode());
    }

    private int seats = 10;
}
