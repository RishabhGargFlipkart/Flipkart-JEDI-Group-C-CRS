package com.flipkart.service;

import com.flipkart.bean.Course;
import com.flipkart.bean.StudentGrade;

import java.util.List;

public interface RegistrationService {
    public boolean addCourse(String courseCode,String studentId,List<Course> courseList);
    public boolean dropCourse(String CourseCode,String studentId,List<Course> registeredCourseList);
    public List<StudentGrade> viewGradeCard(String studentId);
    public List<Course> viewCourses(String studentId);
    public List<Course> viewRegisteredCourses(String studentId);
    public double calculateFee(String studentId);
    public boolean getRegistrationStatus(String studentId);
    public void setRegistrationStatus(String studentId);
}