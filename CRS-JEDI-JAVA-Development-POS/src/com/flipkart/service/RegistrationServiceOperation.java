package com.flipkart.service;

import com.flipkart.bean.Course;
import com.flipkart.bean.StudentGrade;

import java.util.List;

public class RegistrationServiceOperation implements RegistrationService{
    public boolean addCourse(){
        return false;
    }

    public boolean dropCourse(){
        return false;
    }

    public List<StudentGrade> viewGradeCard(){
        return null;
    }

    public List<Course> viewCourses(){
        return null;
    }

    public List<Course> viewRegisteredCourses(){
        return null;
    }

    public double calculateFee(){
        return 0.0;
    }
}