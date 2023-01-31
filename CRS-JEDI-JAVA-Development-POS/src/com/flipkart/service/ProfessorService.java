package com.flipkart.service;

import com.flipkart.bean.Course;
import com.flipkart.bean.Student;

import java.util.List;

public interface ProfessorService {
    public String addGrade(int profId, int studentId, String courseCode, int semester);
    public List<Student> viewEnrolledStudents(int profId, String courseCode);
    public List<Course> getCourses(int profId);
}
