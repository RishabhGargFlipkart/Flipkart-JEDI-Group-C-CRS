package com.flipkart.service;

import com.flipkart.bean.Course;
import com.flipkart.bean.Student;
import com.flipkart.bean.EnrolledStudent;
import com.flipkart.exception.*;

import java.sql.SQLException;
import java.util.List;

public interface ProfessorService {
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
    public String addGrade(String profId, String studentId, String courseCode,String grade) throws ClassNotFoundException, StudentNotRegistered, GradeAssignedException;
    /**
     * method to get enrolled students for given profId and course
     * @param profId
     * @param courseCode
     * @return List of Enrolled students
     * @throws NoEnrolledStudentsException
     * @throws ClassNotFoundException
     */
    public List<EnrolledStudent> viewEnrolledStudents(String profId, String courseCode) throws ClassNotFoundException, NoEnrolledStudentsException;

    /**
     method to get list of courses taught by a professor
     * @param profId
     * @return List of courses
     * @throws ClassNotFoundException
     * @throws NoAssignedCourseException
     */
    public List<Course> getCourses(String profId) throws NoAssignedCourseException, ClassNotFoundException;

    /**
     * method to assign a professor to course
     * @param profId
     * @param courseCode
     * @return if course is assigned or not
     * @throws ProfessorAssignedException
     * @throws CourseNotFoundException
     * @throws ClassNotFoundException
     */
     public boolean assignCourse(String profId,String courseCode) throws ClassNotFoundException, CourseNotFoundException, ProfessorAssignedException;
}