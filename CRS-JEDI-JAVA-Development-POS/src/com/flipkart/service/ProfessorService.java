package com.flipkart.service;

import com.flipkart.bean.Course;
import com.flipkart.bean.Student;
import com.flipkart.bean.EnrolledStudent;
import java.util.List;

public interface ProfessorService {
    public String addGrade(String profId, String studentId, String courseCode,String grade);
    public List<EnrolledStudent> viewEnrolledStudents(String profId, String courseCode);
    public List<Course> getCourses(String profId);
}