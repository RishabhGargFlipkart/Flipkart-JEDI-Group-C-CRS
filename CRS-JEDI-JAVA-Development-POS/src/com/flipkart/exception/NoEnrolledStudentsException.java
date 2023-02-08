package com.flipkart.exception;
import com.flipkart.constant.ColourConstant;

/**
 * @author JEDI-Group-C Praneet, Rishabh, Akhil, Manan, Nidhi, Shivanshu, Divyansh
 * Exception to check if there are no enrolled students
 *
 */

public class NoEnrolledStudentsException extends Exception{
    public String profId;
    public String courseCode;

    /**
     * Constructor
     * @param profId
     * @param courseCode
     */
    public NoEnrolledStudentsException(String profId, String courseCode) {
        this.profId = profId;
        this.courseCode = courseCode;
    }

    /**
     * getter method
     * @return profId
     */
    public String getProfId() {
        return profId;
    }

    /**
     * setter method
     * @param profId
     */
    public void setProfId(String profId) {
        this.profId = profId;
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
     * getter method
     * @return exception message
     */
    @Override
    public String getMessage()
    {
        return ColourConstant.ANSI_YELLOW + "No enrolled students for profId: " + profId + " and courseCode: " + courseCode + ColourConstant.ANSI_RESET;
    }
}
