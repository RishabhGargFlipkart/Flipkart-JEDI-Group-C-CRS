package com.flipkart.exception;

/**
 * @author JEDI-Group-C Praneet, Rishabh, Akhil, Manan, Nidhi, Shivanshu, Divyansh
 * Exception if course is not assigned to any professor
 *
 */
public class NoAssignedCourseException extends Exception{
    /**
     * Constructor
     * @param profId
     */
    public NoAssignedCourseException(String profId) {
        this.profId = profId;
    }

    /**
     * getter method for profId
     * @return profId
     */
    public String getProfId() {
        return profId;
    }

    /**
     * setter method for profId
     * @param profId
     */
    public void setProfId(String profId) {
        this.profId = profId;
    }

    public String profId;

    /**
     * getter method
     * @return exception message
     */
    @Override
    public String getMessage()
    {
        return "No assigned courses for professor Id: "+profId;
    }

}
