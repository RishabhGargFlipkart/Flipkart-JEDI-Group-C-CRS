package com.flipkart.service;

import com.flipkart.bean.Course;
import com.flipkart.bean.Student;
import com.flipkart.bean.EnrolledStudent;

import java.sql.SQLException;
import java.util.List;

public interface ProfessorService {
    public String addGrade(String profId, String studentId, String courseCode,String grade) throws SQLException, ClassNotFoundException;
    public List<EnrolledStudent> viewEnrolledStudents(String profId, String courseCode) throws SQLException, ClassNotFoundException;
    public List<Course> getCourses(String profId) throws SQLException, ClassNotFoundException;
    public boolean assignCourse(String profId,String courseCode) throws SQLException, ClassNotFoundException;
}