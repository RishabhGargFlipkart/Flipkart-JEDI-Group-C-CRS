package com.flipkart.service;

import com.flipkart.bean.Course;
import com.flipkart.bean.Professor;
import com.flipkart.bean.Student;
import com.flipkart.bean.StudentGrade;
import com.flipkart.exception.*;

import java.util.List;

/**
 * @author JEDI-Group-C Praneet, Rishabh, Akhil, Manan, Nidhi, Shivanshu, Divyansh
 * Interface for Admin Dao Operations
 *
 */
public interface AdminService {
    /**
     * This method finds the course with the given courseId and deletes it if it exists.
     * @param courseID
     * @param courseList
     * @throws CourseNotFoundException
     * @throws CourseNotDeletedException
     */
    public void deleteCourse(String courseID, List<Course> courseList) throws CourseNotFoundException, CourseNotDeletedException;

    /**
     * This method adds a new course to the course catalog.
     * @param course
     * @param courseList
     * @throws CourseFoundException
     */
    public void addCourse(Course course, List<Course> courseList) throws CourseFoundException;

    /**
     * Method to approve grade card
     * @param studentId
     * @param studentList
     * @throws StudentNotFoundForApprovalException
     */
    public void approveGradeCard(String studentId, List<Student> studentList) throws StudentNotFoundForApprovalException;


    /**
     * Method to view pending admissions
     * @return List
     */
    public List<Student> viewPendingAdmission();

    /**
     * Method to approve registration
     * @param studentId
     * @param studentList
     * @throws StudentNotFoundForApprovalException
     */
    public void approveRegistration(String studentId, List<Student> studentList) throws StudentNotFoundForApprovalException;

    /**
     * Method to view pending registration
     * @return list of students whose course registration package needs to be approved.
     */
    public List<Student> viewPendingRegistration();

    /**
     * This method approves the login for the newly registered student with the given studentId.
     * @param studentID
     * @param studentList
     * @throws StudentNotFoundForApprovalException
     */
    public void approveStudent(String studentID, List<Student> studentList) throws StudentNotFoundForApprovalException;

    /**
     * This method add a new Professor to the database using the details passed as Professor object.
     * @param professor
     * @throws UserIdAlreadyInUseException
     * @throws ProfessorNotAddedException
     */
    public void addProfessor(Professor professor) throws UserIdAlreadyInUseException, ProfessorNotAddedException;

    /**
     * Method to view courses
     * @return List of courses in the catalog.
     */
    public List<Course> viewCourses();

    /**
     * Method to view professors
     * @return list of existing professors.
     */
    public List<Professor> viewProfessors();

    /**
     * Method to view pending grade cards
     * @return list of students whose grade cards needs to be generated.
     */
    public List<Student> viewPendingGradeCard();

    /**
     * @return list of students who have been approved.
     */
    public List<Student> viewCompletedAdmission();
}
