package com.flipkart.bean;
/**
 * @author JEDI-Group-C Praneet, Rishabh, Akhil, Manan, Nidhi, Shivanshu, Divyansh
 * Class for Student, post registration
 *
 */
public class EnrolledStudent {
    private String courseCode;
    private String courseName;
    private String studentId;

    /**
     * Method to get course code
     * @return
     */
    public String getCourseCode() {
        return courseCode;
    }

    /**
     * Method to set course code
     * @param courseCode
     */
    public void setCourseCode(String courseCode) {
        this.courseCode = courseCode;
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
     * Method to get student id
     * @return
     */
    public String getStudentId() {
        return studentId;
    }

    /**
     * Method to set student id
     * @param studentId
     */
    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }


    /**
     *Default constructor
     */
    public EnrolledStudent(){}

    /**
     * Constructor to return enrolled student
     * @param courseCode
     * @param courseName
     * @param studentId
     */
    public EnrolledStudent(String courseCode, String courseName, String studentId) {
        this.courseCode = courseCode;
        this.courseName = courseName;
        this.studentId = studentId;
    }
}
