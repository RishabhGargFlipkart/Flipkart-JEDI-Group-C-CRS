package com.flipkart.service;

import com.flipkart.bean.Course;
import com.flipkart.bean.Professor;
import com.flipkart.bean.Student;
import com.flipkart.bean.StudentGrade;

import java.util.List;

public interface AdminService {
    public void deleteCourse(String courseID, List<Course> courseList);
    public void addCourse(Course course, List<Course> courseList);
    public List<Student> viewPendingAdmission();
    public void approveStudent(String studentID, List<Student> studentList);
    public void addProfessor(Professor professor);
    public List<Course> viewCourses(int catalogID);
    public List<Professor> viewProfessors();
    public void approveGradeCard(String studentId);
}
