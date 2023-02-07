package com.flipkart.exception;

import java.sql.SQLException;

public class NoAssignedCourseException extends Exception{
    public NoAssignedCourseException(String profId) {
        this.profId = profId;
    }

    public String getProfId() {
        return profId;
    }

    public void setProfId(String profId) {
        this.profId = profId;
    }

    public String profId;

    @Override
    public String getMessage()
    {
        return "No assigned courses for professor Id: "+profId;
    }

}
