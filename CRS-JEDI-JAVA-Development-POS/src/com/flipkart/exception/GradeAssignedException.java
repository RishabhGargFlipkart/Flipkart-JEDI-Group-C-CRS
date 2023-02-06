package com.flipkart.exception;

public class GradeAssignedException extends Exception {

    public String studentId;
    public String courseCode;

    public GradeAssignedException(String studentId, String courseCode) {
        this.studentId = studentId;
        this.courseCode = courseCode;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
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
        return "Student Id: "+studentId+" is already assigned with a grade for courseCode: "+courseCode;
    }
}
