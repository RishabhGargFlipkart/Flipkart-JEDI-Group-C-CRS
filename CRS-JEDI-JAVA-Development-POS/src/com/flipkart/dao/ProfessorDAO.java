package com.flipkart.dao;
import com.flipkart.bean.Course;
import com.flipkart.bean.EnrolledStudent;
import com.flipkart.bean.Professor;
import com.flipkart.exception.*;

import java.sql.SQLException;
import java.util.List;

/**
 * @author JEDI-Group-C Praneet, Rishabh, Akhil, Manan, Nidhi, Shivanshu, Divyansh
 * Interface for Professor Dao Operations
 *
 */
public interface ProfessorDAO {

    /**
     method to get list of courses taught by a professor
     * @param profId
     * @return List of courses
     * @throws ClassNotFoundException
     * @throws NoAssignedCourseException
     */
    public List<Course> getCourses(String profId) throws ClassNotFoundException, NoAssignedCourseException;

    /**
     * method to get enrolled students for given profId and course
     * @param profId
     * @param courseCode
     * @return List of Enrolled students
     * @throws NoEnrolledStudentsException
     * @throws ClassNotFoundException
     */
    public List<EnrolledStudent> getEnrolledStudent(String profId, String courseCode) throws NoEnrolledStudentsException, ClassNotFoundException;

    /**
     * method to add grade to a specified student course
     * @param studentId
     * @param courseCode
     * @param grade
     * @return if grade added or not
     * @throws StudentNotRegistered
     * @throws GradeAssignedException
     * @throws ClassNotFoundException
     */
    public boolean addGrade(String studentId,String courseCode,String grade) throws StudentNotRegistered, GradeAssignedException,ClassNotFoundException;

    /**
     * method to assign a professor to course
     * @param profId
     * @param courseCode
     * @return if course is assigned or not
     * @throws ProfessorAssignedException
     * @throws CourseNotFoundException
     * @throws ClassNotFoundException
     */
    public boolean assignCourse(String profId,String courseCode) throws ProfessorAssignedException, CourseNotFoundException, ClassNotFoundException;

    /**
     * method to check professor credentials
     * @param profId
     * @param password
     * @return if professor with given credentials exists or not
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    public boolean getProfessors(String profId,String password) throws SQLException, ClassNotFoundException;
}
