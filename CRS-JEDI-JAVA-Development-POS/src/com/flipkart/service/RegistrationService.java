package com.flipkart.service;

import com.flipkart.bean.Course;
import com.flipkart.bean.StudentGrade;

import java.util.List;
import java.sql.SQLException;

import com.flipkart.exception.CourseLimitExceedException;
import com.flipkart.exception.CourseNotFoundException;
import com.flipkart.exception.SeatNotAvailableException;

public interface RegistrationService {
    /**
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
     * @param CourseCode
     * @param studentId
     * @param registeredCourseList
     * @return
     * @throws CourseNotFoundException
     * @throws SQLException
     */
    public boolean dropCourse(String CourseCode,String studentId,List<Course> registeredCourseList) throws CourseNotFoundException, SQLException;

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
    public double calculateFee(String studentId) throws SQLException;

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