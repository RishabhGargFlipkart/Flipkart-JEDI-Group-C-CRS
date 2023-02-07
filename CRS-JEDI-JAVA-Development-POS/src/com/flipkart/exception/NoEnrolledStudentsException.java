package com.flipkart.exception;

public class NoEnrolledStudentsException extends Exception{
    public String profId;
    public String courseCode;

    public NoEnrolledStudentsException(String profId, String courseCode) {
        this.profId = profId;
        this.courseCode = courseCode;
    }

    public String getProfId() {
        return profId;
    }

    public void setProfId(String profId) {
        this.profId = profId;
    }

    public String getCourseCode() {
        return courseCode;
    }

    public void setCourseCode(String courseCode) {
        this.courseCode = courseCode;
    }

    @Override
    public String getMessage()
    {
        return "No enrolled students for profId: " + profId + " and courseCode: " + courseCode;
    }
}
