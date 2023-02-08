package com.flipkart.service;

import com.flipkart.bean.Course;
import com.flipkart.bean.StudentGrade;

import java.util.List;
import java.sql.SQLException;

import com.flipkart.exception.CourseLimitExceedException;
import com.flipkart.exception.CourseNotFoundException;
import com.flipkart.exception.SeatNotAvailableException;

/**
 * @author JEDI-Group-C Praneet, Rishabh, Akhil, Manan, Nidhi, Shivanshu, Divyansh
 * Interface for Admin Dao Operations
 *
 */

public interface RegistrationService {
    /**
     * Method to add course
     * @param courseCode
     * @param studentId
     * @param courseList
     * @return
     * @throws CourseNotFoundException
     * @throws CourseLimitExceedException
     * @throws SeatNotAvailableException
     * @throws SQLException
     */
    public boolean addCourse(String courseCode,String studentId,List<Course> courseList) throws CourseNotFoundException, CourseLimitExceedException, SeatNotAvailableException, SQLException;

    /**
     * Method to drop course
     * @param CourseCode
     * @param studentId
     * @param registeredCourseList
     * @return
     * @throws CourseNotFoundException
     * @throws SQLException
     */
    public boolean dropCourse(String CourseCode,String studentId,List<Course> registeredCourseList) throws CourseNotFoundException, SQLException;

    /**
     * Method to view grade card
     * @param studentId
     * @return
     * @throws SQLException
     */
    public List<StudentGrade> viewGradeCard(String studentId) throws SQLException;

    /**
     * Method to view all courses
     * @param studentId
     * @return
     * @throws SQLException
     */
    public List<Course> viewCourses(String studentId) throws SQLException;

    /**
     * Method to view registered courses
     * @param studentId
     * @return
     * @throws SQLException
     */
    public List<Course> viewRegisteredCourses(String studentId) throws SQLException;

    /**
     * Method for payment
     * @param studentId
     * @return
     * @throws SQLException
     */
    public double calculateFee(String studentId) throws SQLException;

    /**
     * Method to get registration status
     * @param studentId
     * @return
     * @throws SQLException
     */
    public boolean getRegistrationStatus(String studentId) throws SQLException;

    /**
     * Method to set registration status
     * @param studentId
     * @throws SQLException
     */
    public void setRegistrationStatus(String studentId) throws SQLException;

    /**
     * Method to get login status
     * @param studentId
     * @return
     * @throws SQLException
     */
    public boolean getLoginStatus(String studentId) throws SQLException;
}