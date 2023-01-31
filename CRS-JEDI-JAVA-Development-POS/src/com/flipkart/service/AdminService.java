package com.flipkart.service;

import com.flipkart.bean.Course;
import com.flipkart.bean.Professor;
import com.flipkart.bean.Student;

import java.util.List;

public interface AdminService {
    public void deleteCourse(String courseID, List<Course> courseList);
    public void addCourse(Course course, List<Course> courseList);
    public List<Student> viewPendingAdmission();
    public void approveStudent(int studentID, List<Student> studentList);
    public void addProfessor(Professor professor);
    public void assignCourse(String courseID, String professorID);
    public List<Course> viewCourses(int catalogID);
    public List<Professor> viewProfessors();
}
