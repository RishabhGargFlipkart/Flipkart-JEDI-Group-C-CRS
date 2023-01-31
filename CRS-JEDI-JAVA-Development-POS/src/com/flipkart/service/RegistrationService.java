package com.flipkart.service;

import com.flipkart.bean.Course;
import com.flipkart.bean.StudentGrade;

import java.util.List;

public interface RegistrationService {
    public boolean addCourse();
    public boolean dropCourse();
    public List<StudentGrade> viewGradeCard();
    public List<Course> viewCourses();
    public List<Course> viewRegisteredCourses();
    public double calculateFee();
}