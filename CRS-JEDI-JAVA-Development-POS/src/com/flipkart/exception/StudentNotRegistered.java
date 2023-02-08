package com.flipkart.exception;
import com.flipkart.constant.ColourConstant;

/**
 * @author JEDI-Group-C Praneet, Rishabh, Akhil, Manan, Nidhi, Shivanshu, Divyansh
 * Exception if student is not registered
 *
 */
public class StudentNotRegistered extends Exception{
    public String studentId;
    public String courseCode;

    /**
     * getter method
     * @return student id
     */
    public String getStudentId() {
        return studentId;
    }

    /**
     * setter method
     * @param studentId
     */
    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    /**
     * getter method
     * @return course code
     */
    public String getCourseCode() {
        return courseCode;
    }

    /**
     * setter method
     * @param courseCode
     */
    public void setCourseCode(String courseCode) {
        this.courseCode = courseCode;
    }

    /**
     * Constructor
     * @param studentId
     * @param courseCode
     */
    public StudentNotRegistered(String studentId, String courseCode) {
        this.studentId = studentId;
        this.courseCode = courseCode;
    }

    /**
     * getter method
     * @return exception message
     */
    @Override
    public String getMessage()
    {
        return ColourConstant.ANSI_YELLOW + "Student Id: "+studentId+" is not registered in the course: "+courseCode + ColourConstant.ANSI_RESET;
    }

}
