package com.flipkart.bean;

/**
 * @author JEDI-Group-C Praneet, Rishabh, Akhil, Manan, Nidhi, Shivanshu, Divyansh
 * Class for Student
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

    /**
     * Method to get gender
     * @return
     */
    public String getGender() {
        return gender;
    }

    /**
     * Method to set gender
     * @param gender
     */
    public void setGender(String gender) {
        this.gender = gender;
    }

    /**
     * Method to get address
     * @return
     */
    public String getAddress() {
        return address;
    }

    /**
     * Method to set address
     * @param address
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * Method to get branch
     * @return
     */
    public String getBranchName() {
        return branchName;
    }

    /**
     * Method to set branch
     * @param branchName
     */
    public void setBranchName(String branchName) {
        this.branchName = branchName;
    }

    /**
     * Method to get batch
     * @return
     */
    public int getBatch() {
        return batch;
    }

    /**
     * Method to set batch
     * @param batch
     */
    public void setBatch(int batch) {
        this.batch = batch;
    }

    /**
     * Method to check grade card is approved
     * @return
     */
    public boolean isGradeCardApproved() {
        return isGradeCardApproved;
    }

    /**
     * Method to set grade card approval
     * @param gradeCardApproved
     */
    public void setGradeCardApproved(boolean gradeCardApproved) {
        isGradeCardApproved = gradeCardApproved;
    }

    /**
     * Method to check registration approval
     * @return
     */
    public boolean isRegistrationApproved() {
        return isRegistrationApproved;
    }

    /**
     * Method to set registration approval
     * @param registrationApproved
     */
    public void setRegistrationApproved(boolean registrationApproved) {
        isRegistrationApproved = registrationApproved;
    }

    /**
     * Method to check student approval
     * @return
     */
    public boolean isStudentApproved() {
        return isStudentApproved;
    }

    /**
     * Method to set student approval
     * @param studentApproved
     */
    public void setStudentApproved(boolean studentApproved) {
        isStudentApproved = studentApproved;
    }

}
