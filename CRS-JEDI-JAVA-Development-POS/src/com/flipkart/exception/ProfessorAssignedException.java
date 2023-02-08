package com.flipkart.exception;
import com.flipkart.constant.ColourConstant;

public class ProfessorAssignedException extends Exception {
    public String courseId;

    public String getCourseId() {
        return courseId;
    }

    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }

    public ProfessorAssignedException(String courseId) {
        this.courseId = courseId;
    }

    @Override
    public String getMessage()
    {
        return ColourConstant.ANSI_YELLOW + "Course with Id: "+courseId+" is already assigned to a professor." + ColourConstant.ANSI_RESET;
    }
}
