package com.flipkart.exception;
import com.flipkart.constant.ColourConstant;

public class StudentNotRegistered extends Exception{
    public String studentId;
    public String courseCode;

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

    public StudentNotRegistered(String studentId, String courseCode) {
        this.studentId = studentId;
        this.courseCode = courseCode;
    }

    @Override
    public String getMessage()
    {
        return ColourConstant.ANSI_YELLOW + "Student Id: "+studentId+" is not registered in the course: "+courseCode + ColourConstant.ANSI_RESET;
    }

}
