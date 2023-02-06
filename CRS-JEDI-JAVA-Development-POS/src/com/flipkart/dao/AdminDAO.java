package com.flipkart.dao;

import com.flipkart.bean.Course;
import com.flipkart.bean.Professor;
import com.flipkart.bean.Student;

import java.util.List;
import com.flipkart.exception.CourseFoundException;
import com.flipkart.exception.CourseNotDeletedException;
import com.flipkart.exception.CourseNotFoundException;
import com.flipkart.exception.ProfessorNotAddedException;
import com.flipkart.exception.StudentNotFoundForApprovalException;
import com.flipkart.exception.UserIdAlreadyInUseException;
import com.flipkart.exception.UserNotAddedException;
import com.flipkart.exception.UserNotFoundException;

public interface AdminDAO {
    public void deleteCourse(String courseID) throws CourseNotFoundException, CourseNotDeletedException;

    public void addCourse(Course course) throws CourseFoundException;

    public void approveGradeCard(String studentID) throws StudentNotFoundForApprovalException;

    public void approveRegistration(String studentID) throws StudentNotFoundForApprovalException;

    public void approveStudent(String studentID) throws StudentNotFoundForApprovalException;

    public void addProfessor(Professor professor) throws UserIdAlreadyInUseException, ProfessorNotAddedException;

    public List<Course> viewCourses();

    public List<Professor> viewProfessors();

    public List<Student> viewPendingAdmission();
    public List<Student> viewPendingRegistration();
    public List<Student> viewPendingGradeCard();

}
