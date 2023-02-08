package com.flipkart.exception;

import java.sql.SQLException;
import com.flipkart.constant.ColourConstant;

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
        return ColourConstant.ANSI_YELLOW + "No assigned courses for professor Id: "+profId + ColourConstant.ANSI_RESET;
    }

}
