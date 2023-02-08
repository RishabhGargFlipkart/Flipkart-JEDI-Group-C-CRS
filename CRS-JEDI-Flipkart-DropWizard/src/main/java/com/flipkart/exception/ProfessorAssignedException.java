package com.flipkart.exception;
import com.flipkart.constant.ColourConstant;

/**
 * @author JEDI-Group-C Praneet, Rishabh, Akhil, Manan, Nidhi, Shivanshu, Divyansh
 * Exception if course has already been assigned a professor
 *
 */

public class ProfessorAssignedException extends Exception {
    public String courseId;

    /**
     * getter method
     * @return course id
     */
    public String getCourseId() {
        return courseId;
    }

    /**
     * setter method
     * @param courseId
     */
    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }

    /**
     * Constructor
     * @param courseId
     */
    public ProfessorAssignedException(String courseId) {
        this.courseId = courseId;
    }

    /**
     * getter method
     * @return exception messgae
     */
    @Override
    public String getMessage()
    {
        return ColourConstant.ANSI_YELLOW + "Course with Id: "+courseId+" is already assigned to a professor." + ColourConstant.ANSI_RESET;
    }
}
