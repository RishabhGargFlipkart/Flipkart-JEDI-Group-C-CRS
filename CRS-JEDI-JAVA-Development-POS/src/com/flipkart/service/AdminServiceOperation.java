package com.flipkart.service;

import java.util.List;
import com.flipkart.bean.Course;
import com.flipkart.bean.Student;
import com.flipkart.bean.Professor;

public class AdminServiceOperation implements AdminService {
    public void deleteCourse(String courseID, List<Course> courseList){}
    public void addCourse(Course course, List<Course> courseList){}

    public List<Student> viewPendingAdmission() {
        return null;
    }

    public void approveStudent(int studentID, List<Student> studentList){}

    public void addProfessor(Professor professor){}

    public void assignCourse(String courseID, String professorID){}

    public List<Course> viewCourses(int catalogID) {
        return null;
    }

    public List<Professor> viewProfessors() {
        return null;
    }

}
