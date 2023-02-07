package com.flipkart.dao;
import java.sql.SQLException;
import java.util.List;

import com.flipkart.bean.Course;
import com.flipkart.bean.StudentGrade;

import com.flipkart.exception.CourseNotFoundException;

public interface RegistrationDAO {
    /**
     * @param courseCode
     * @param studentId
     * @return
     * @throws SQLException
     */
    public boolean addCourse(String courseCode, String studentId) throws SQLException;


    /**
     * @param courseCode
     * @param studentId
     * @return
     * @throws SQLException
     */
    public boolean dropCourse(String courseCode, String studentId) throws SQLException;

    /**
     * @param studentId
     * @return
     * @throws SQLException
     */
    public List<Course> viewCourses(String studentId) throws SQLException;

    /**
     * @param studentId
     * @return
     * @throws SQLException
     */
    public List<Course> viewRegisteredCourses(String studentId) throws SQLException;


    /**
     * @param studentId
     * @return
     * @throws SQLException
     */
    public List<StudentGrade> viewGradeCard(String studentId) throws SQLException;


    /**
     * @param studentId
     * @return
     * @throws SQLException
     */
    public double calculateFee(String studentId) throws SQLException;


    /**
     * @param courseCode
     * @return
     * @throws SQLException
     */
    public boolean seatAvailable(String courseCode) throws SQLException;


    /**
     * @param studentId
     * @return
     * @throws SQLException
     */
    public int numOfRegisteredCourses(String studentId) throws SQLException;


    /**
     * @param courseCode
     * @param studentId
     * @return
     * @throws SQLException
     */
    public boolean isRegistered(String courseCode, String studentId) throws SQLException;


    /**
     * @param studentId
     * @return
     * @throws SQLException
     */
    public boolean getRegistrationStatus(String studentId) throws SQLException;


    /**
     * @param studentId
     * @throws SQLException
     */
    public void setRegistrationStatus(String studentId) throws SQLException;

    /**
     * @param studentId
     * @return
     * @throws SQLException
     */
    public boolean getLoginStatus(String studentId) throws SQLException;

}