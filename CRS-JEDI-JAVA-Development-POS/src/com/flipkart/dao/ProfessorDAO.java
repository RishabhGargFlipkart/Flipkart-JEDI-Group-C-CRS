package com.flipkart.dao;
import com.flipkart.bean.Course;
import com.flipkart.bean.EnrolledStudent;
import com.flipkart.bean.Professor;
import com.flipkart.exception.*;

import java.sql.SQLException;
import java.util.List;

public interface ProfessorDAO {
    public List<Course> getCourses(String profId) throws ClassNotFoundException, NoAssignedCourseException;

    public List<EnrolledStudent> getEnrolledStudent(String profId, String courseCode) throws NoEnrolledStudentsException, ClassNotFoundException;

    public boolean addGrade(String studentId,String courseCode,String grade) throws StudentNotRegistered, GradeAssignedException,ClassNotFoundException;

    public boolean assignCourse(String profId,String courseCode) throws ProfessorAssignedException, CourseNotFoundException, ClassNotFoundException;
    public boolean getProfessors(String profId,String password) throws SQLException, ClassNotFoundException;
}
