package com.flipkart.service;

import com.flipkart.bean.Course;
import com.flipkart.bean.StudentGrade;

import java.util.ArrayList;
import java.util.List;
import java.sql.SQLException;

import com.flipkart.dao.RegistrationDAO;
import com.flipkart.dao.RegistrationDAOImpl;

import com.flipkart.exception.CourseLimitExceedException;
import com.flipkart.exception.CourseNotFoundException;
import com.flipkart.exception.SeatNotAvailableException;

import com.flipkart.validator.StudentValidator;
public class RegistrationServiceOperation implements RegistrationService{
    private static volatile RegistrationServiceOperation instance = null;

    private RegistrationServiceOperation() {

    }
    public static RegistrationServiceOperation getInstance() {
        if (instance == null) {
            synchronized (RegistrationServiceOperation.class) {
                instance = new RegistrationServiceOperation();
            }
        }
        return instance;
    }

    RegistrationDAO registrationDaoInterface = RegistrationDAOImpl.getInstance();
    @Override
    public boolean addCourse(String courseCode, String studentId,List<Course> availableCourseList) throws CourseNotFoundException, CourseLimitExceedException, SeatNotAvailableException, SQLException
    {
        if (registrationDaoInterface.numOfRegisteredCourses(studentId) >= 6)
        {
            throw new CourseLimitExceedException(6);
        }
        else if (registrationDaoInterface.isRegistered(courseCode, studentId))
        {
            return false;
        }
        else if (!registrationDaoInterface.seatAvailable(courseCode))
        {
            throw new SeatNotAvailableException(courseCode);
        }
        else if(!StudentValidator.isValidCourseCode(courseCode, availableCourseList))
        {
            throw new CourseNotFoundException(courseCode);
        }



        return registrationDaoInterface.addCourse(courseCode, studentId);

    }

    @Override
    public boolean dropCourse(String courseCode, String studentId,List<Course> registeredCourseList) throws CourseNotFoundException, SQLException {
        if(!StudentValidator.isRegistered(courseCode, studentId, registeredCourseList))
        {
            throw new CourseNotFoundException(courseCode);
        }

        return registrationDaoInterface.dropCourse(courseCode, studentId);

    }

    @Override
    public List<StudentGrade> viewGradeCard(String studentId) throws SQLException {
        return registrationDaoInterface.viewGradeCard(studentId);
    }
    public List<Course> viewCourses(String studentId) throws SQLException {
        return registrationDaoInterface.viewCourses(studentId);
    }

    @Override
    public List<Course> viewRegisteredCourses(String studentId) throws SQLException {
        return registrationDaoInterface.viewRegisteredCourses(studentId);
    }


    @Override
    public double calculateFee(String studentId) throws SQLException {
        return registrationDaoInterface.calculateFee(studentId);
    }
    @Override
    public boolean getRegistrationStatus(String studentId) throws SQLException  {
        return registrationDaoInterface.getRegistrationStatus(studentId);
    }
    @Override
    public void setRegistrationStatus(String studentId) throws SQLException {
        registrationDaoInterface.setRegistrationStatus(studentId);

    }

    @Override
    public boolean getLoginStatus(String studentId) throws SQLException {

        return registrationDaoInterface.getLoginStatus(studentId);
    }
}