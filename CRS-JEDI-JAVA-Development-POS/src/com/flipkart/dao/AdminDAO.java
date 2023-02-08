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

/**
 * @author JEDI-Group-C Praneet, Rishabh, Akhil, Manan, Nidhi, Shivanshu, Divyansh
 * Interface for Admin Dao Operations
 *
 */

public interface AdminDAO {
    /**
     * This method finds the course with the given courseId and deletes it if it exists.
     * @param courseID
     * @throws CourseNotFoundException
     * @throws CourseNotDeletedException
     */
    public void deleteCourse(String courseID) throws CourseNotFoundException, CourseNotDeletedException;

    /**
     * This method adds a new course to the course catalog.
     * @param course
     * @throws CourseFoundException
     */
    public void addCourse(Course course) throws CourseFoundException;

    /**
     * This method generates the grade card for the student with the given studentId.
     * @param studentID
     * @throws StudentNotFoundForApprovalException
     */
    public void approveGradeCard(String studentID) throws StudentNotFoundForApprovalException;

    /**
     * This method approves the course package of the student with the given studentId.
     * @param studentID
     * @throws StudentNotFoundForApprovalException
     */
    public void approveRegistration(String studentID) throws StudentNotFoundForApprovalException;

    /**
     * This method approves the login for the newly registered student with the given studentId.
     * @param studentID
     * @throws StudentNotFoundForApprovalException
     */
    public void approveStudent(String studentID) throws StudentNotFoundForApprovalException;

    /**
     * This method add a new Professor to the database using the details passed as Professor object.
     * @param professor
     * @throws UserIdAlreadyInUseException
     * @throws ProfessorNotAddedException
     */
    public void addProfessor(Professor professor) throws UserIdAlreadyInUseException, ProfessorNotAddedException;

    /**
     * @return List of courses in the catalog.
     */
    public List<Course> viewCourses();

    /**
     * @return list of existing professors.
     */
    public List<Professor> viewProfessors();

    /**
     * @return list of students whose login needs to be approved.
     */
    public List<Student> viewPendingAdmission();

    /**
     * @return list of students whose course registration package needs to be approved.
     */
    public List<Student> viewPendingRegistration();

    /**
     * @return list of students whose grade cards needs to be generated.
     */
    public List<Student> viewPendingGradeCard();


    public List<Student> viewCompletedAdmission();

}
