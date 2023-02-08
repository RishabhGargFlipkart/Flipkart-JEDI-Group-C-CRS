package com.flipkart.dao;
import java.sql.SQLException;
import java.util.List;

import com.flipkart.bean.Course;
import com.flipkart.bean.StudentGrade;

import com.flipkart.exception.CourseNotFoundException;

/**
 * @author JEDI-Group-C Praneet, Rishabh, Akhil, Manan, Nidhi, Shivanshu, Divyansh
 * Interface for Registration Dao Operations
 *
 */

public interface RegistrationDAO {
    /**
     * This methods adds a course for registration and add/drop window
     * @param courseCode
     * @param studentId
     * @return
     * @throws SQLException
     */
    public boolean addCourse(String courseCode, String studentId) throws SQLException;


    /**
     * This method drops a course to remove from registration
     * @param courseCode
     * @param studentId
     * @return
     * @throws SQLException
     */
    public boolean dropCourse(String courseCode, String studentId) throws SQLException;

    /**
     * This method displays all available courses for registration
     * @param studentId
     * @return
     * @throws SQLException
     */
    public List<Course> viewCourses(String studentId) throws SQLException;

    /**
     * This method displays the registered courses
     * @param studentId
     * @return
     * @throws SQLException
     */
    public List<Course> viewRegisteredCourses(String studentId) throws SQLException;


    /**
     * This method displays the grade card after approval from admin
     * @param studentId
     * @return
     * @throws SQLException
     */
    public List<StudentGrade> viewGradeCard(String studentId) throws SQLException;


    /**
     * This method calculates the payment fee for registration
     * @param studentId
     * @return
     * @throws SQLException
     */
    public double calculateFee(String studentId) throws SQLException;


    /**
     * This method displays the number of seats available for registration of course
     * @param courseCode
     * @return
     * @throws SQLException
     */
    public boolean seatAvailable(String courseCode) throws SQLException;


    /**
     * This method displays the number of registered courses
     * @param studentId
     * @return
     * @throws SQLException
     */
    public int numOfRegisteredCourses(String studentId) throws SQLException;


    /**
     * This method returns whether student is registered
     * @param courseCode
     * @param studentId
     * @return
     * @throws SQLException
     */
    public boolean isRegistered(String courseCode, String studentId) throws SQLException;


    /**
     * This method passes the registration status
     * @param studentId
     * @return
     * @throws SQLException
     */
    public boolean getRegistrationStatus(String studentId) throws SQLException;


    /**
     * This method sets the registration status
     * @param studentId
     * @throws SQLException
     */
    public void setRegistrationStatus(String studentId) throws SQLException;

    /**
     * This method returns the login status
     * @param studentId
     * @return
     * @throws SQLException
     */
    public boolean getLoginStatus(String studentId) throws SQLException;

}