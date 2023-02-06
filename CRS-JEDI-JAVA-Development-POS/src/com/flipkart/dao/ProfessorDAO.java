package com.flipkart.dao;
import com.flipkart.bean.Course;
import com.flipkart.bean.EnrolledStudent;
import com.flipkart.bean.Professor;

import java.sql.SQLException;
import java.util.List;

public interface ProfessorDAO {
    public List<Course> getCourses(String profId) throws ClassNotFoundException, SQLException;

    public List<EnrolledStudent> getEnrolledStudent(String profId, String courseCode) throws SQLException, ClassNotFoundException;

    public boolean addGrade(String studentId,String courseCode,String grade) throws SQLException, ClassNotFoundException;

    public boolean assignCourse(String profId,String courseCode) throws SQLException, ClassNotFoundException;
    public boolean getProfessors(String profId,String password) throws SQLException, ClassNotFoundException;
}
