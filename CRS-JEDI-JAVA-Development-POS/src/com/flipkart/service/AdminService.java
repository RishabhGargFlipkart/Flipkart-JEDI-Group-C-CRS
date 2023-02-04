package com.flipkart.service;

import com.flipkart.bean.Course;
import com.flipkart.bean.Professor;
import com.flipkart.bean.Student;
import com.flipkart.bean.StudentGrade;
import com.flipkart.exception.*;

import java.util.List;

public interface AdminService {
    public void deleteCourse(String courseID, List<Course> courseList) throws CourseNotFoundException, CourseNotDeletedException;

    public void addCourse(Course course, List<Course> courseList) throws CourseFoundException;

    public void approveGradeCard(String studentId, List<Student> studentList) throws StudentNotFoundForApprovalException;

    public List<Student> viewPendingAdmission();

    public void approveRegistration(String studentId, List<Student> studentList) throws StudentNotFoundForApprovalException;

    public List<Student> viewPendingRegistration();

    public void approveStudent(String studentID, List<Student> studentList) throws StudentNotFoundForApprovalException;

    public void addProfessor(Professor professor) throws UserIdAlreadyInUseException, ProfessorNotAddedException;

    public List<Course> viewCourses();

    public List<Professor> viewProfessors();

    public List<Student> viewPendingGradeCard();

    public static void addStudentToList(Student s){};
}
