/**
 *
 */
package com.flipkart.bean;

/**
 *
 * @author JEDI-03
 * Student Class
 *
 */
public class Student extends User{
    private String branchName;
    //private String studentId;
    private int batch;
    private boolean isApproved;
    public void setBranchName(String branchName) {
        this.branchName = branchName;
    }

    public String getStudentId() {
        return this.getUserId();
    }

    public void setStudentId(String studentId) {
        this.setUserId(studentId);
    }

    public int getBatch() {
        return batch;
    }

    public void setBatch(int batch) {
        this.batch = batch;
    }

    public boolean isApproved() {
        return isApproved;
    }

    public void setApproved(boolean approved) {
        isApproved = approved;
    }

    public String getBranchName(){
        return branchName;
    }
}
