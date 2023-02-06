package com.flipkart.dao;
import java.sql.SQLException;
import java.util.List;

import com.flipkart.bean.Course;
import com.flipkart.bean.StudentGrade;

public interface RegistrationDAO {
    public boolean addCourse(String courseCode, String studentId);


    public boolean dropCourse(String courseCode, String studentId);

    public List<Course> viewCourses(String studentId) throws SQLException;

    public List<Course> viewRegisteredCourses(String studentId);


    public List<StudentGrade> viewGradeCard(String studentId);


    public double calculateFee(String studentId);


    public boolean seatAvailable(String courseCode);


    public int numOfRegisteredCourses(String studentId);


    public boolean isRegistered(String courseCode, String studentId);


    public boolean getRegistrationStatus(String studentId);


    public void setRegistrationStatus(String studentId);

}