package com.flipkart.exception;

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
        return "Course with Id: "+courseId+" is already assigned to a professor.";
    }
}
