package com.flipkart.bean;
/**
 * @author JEDI-Group-C Praneet, Rishabh, Akhil, Manan, Nidhi, Shivanshu, Divyansh
 * Class for Student GradeConstant
 *
 */
public class StudentGrade {
    private String studentID;
    private String courseID;
    private String courseName;
    private String grade;
    private int semester;

//    public StudentGrade() {
//    }

//    public StudentGrade(String studentID, String courseID, String courseName, String grade) {
//        this.studentID = studentID;
//        this.courseID = courseID;
//        this.courseName = courseName;
//        this.grade = grade;
//    }

    /**
     * Method to get student id
     * @return
     */
    public String getStudentID() {
        return studentID;
    }

    /**
     * Method to set student id
     * @param studentID
     */
    public void setStudentID(String studentID) {
        this.studentID = studentID;
    }

    /**
     * Method to get course id
     * @return
     */
    public String getCourseID() {
        return courseID;
    }

    /**
     * Method to set course id
     * @param courseID
     */
    public void setCourseID(String courseID) {
        this.courseID = courseID;
    }

    /**
     * Method to get course name
     * @return
     */
    public String getCourseName() {
        return courseName;
    }

    /**
     * Method to set course name
     * @param courseName
     */
    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    /**
     * Method to get grade
     * @return
     */
    public String getGrade() {
        return grade;
    }

    /**
     * Method to set grade
     * @param grade
     */
    public void setGrade(String grade) {
        this.grade = grade;
    }

    /**
     * Method to get semester
     * @return
     */
    public int getSemester() {
        return semester;
    }

    /**
     * Method to set semester
     * @param semester
     */
    public void setSemester(int semester) {
        this.semester = semester;
    }
}