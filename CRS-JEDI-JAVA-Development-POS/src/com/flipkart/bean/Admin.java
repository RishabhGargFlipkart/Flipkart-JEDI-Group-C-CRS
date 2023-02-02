package com.flipkart.bean;
import java.util.Date;
public class Admin extends User{
    public Date getDateOfJoining() {
        return dateOfJoining;
    }

    public void setDateOfJoining(Date dateOfJoining) {
        this.dateOfJoining = dateOfJoining;
    }

    private Date dateOfJoining;

}
