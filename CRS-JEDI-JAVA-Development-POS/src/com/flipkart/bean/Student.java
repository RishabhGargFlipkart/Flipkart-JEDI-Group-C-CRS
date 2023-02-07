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
    private int batch;
    private boolean isGradeCardApproved;
    private boolean isRegistrationApproved;
    private boolean isStudentApproved;
    private String gender;
    private String address;

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getBranchName() {
        return branchName;
    }

    public void setBranchName(String branchName) {
        this.branchName = branchName;
    }

    public int getBatch() {
        return batch;
    }

    public void setBatch(int batch) {
        this.batch = batch;
    }

    public boolean isGradeCardApproved() {
        return isGradeCardApproved;
    }

    public void setGradeCardApproved(boolean gradeCardApproved) {
        isGradeCardApproved = gradeCardApproved;
    }

    public boolean isRegistrationApproved() {
        return isRegistrationApproved;
    }

    public void setRegistrationApproved(boolean registrationApproved) {
        isRegistrationApproved = registrationApproved;
    }

    public boolean isStudentApproved() {
        return isStudentApproved;
    }

    public void setStudentApproved(boolean studentApproved) {
        isStudentApproved = studentApproved;
    }

}
