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
public class Student {
    private String branchName;
    private int studentId;
    private int batch;
    private boolean isApproved;
    public void setBranchName(String branchName) {
        this.branchName = branchName;
    }

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
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