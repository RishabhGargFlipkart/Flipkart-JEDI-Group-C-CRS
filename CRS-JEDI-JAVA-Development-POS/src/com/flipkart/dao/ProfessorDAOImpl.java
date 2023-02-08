package com.flipkart.dao;

import com.flipkart.bean.Course;
import com.flipkart.bean.EnrolledStudent;
import com.flipkart.bean.Professor;
import com.flipkart.constant.SQLQueriesProfessor;
import com.flipkart.exception.*;
import com.flipkart.utils.DBUtils;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProfessorDAOImpl implements ProfessorDAO {
    private static volatile ProfessorDAOImpl instance=null;

    public static ProfessorDAOImpl getInstance(){
        if(instance==null){
            synchronized (ProfessorDAOImpl.class){
                instance=new ProfessorDAOImpl();
            }
        }
        return instance;
    }
    @Override
    public List<Course> getCourses(String profId) throws ClassNotFoundException, NoAssignedCourseException {
        Connection conn = DBUtils.getConnection();

        Class.forName("com.mysql.cj.jdbc.Driver");
        List<Course> ans=new ArrayList<Course>();
        try{
            PreparedStatement statement= conn.prepareStatement(SQLQueriesProfessor.GET_COURSES);
            statement.setString(1,profId);
            ResultSet results=statement.executeQuery();
            int count=0;
            while(results.next())
            {
                count++;
                ans.add(new Course(results.getString("courseCode"),results.getString("courseName"),results.getString("profId"),results.getInt("seats"),results.getDouble("fee")));
            }
            if(count==0)
            {
                throw new NoAssignedCourseException(profId);
            }
        }
        catch (SQLException e) {
            System.out.println(e.getMessage());
            throw new NoAssignedCourseException(profId);
        }
        return ans;
    }

    @Override
    public List<EnrolledStudent> getEnrolledStudent(String profId, String courseCode) throws NoEnrolledStudentsException, ClassNotFoundException {
        Connection conn = DBUtils.getConnection();

        Class.forName("com.mysql.cj.jdbc.Driver");
        List<EnrolledStudent> enrolledStudents=new ArrayList<EnrolledStudent>();
        try{
            PreparedStatement statement= conn.prepareStatement(SQLQueriesProfessor.GET_ENROLLED_STUDENTS);
            statement.setString(1, profId);
            statement.setString(2,courseCode);

            ResultSet results = statement.executeQuery();
            int count=0;
            while(results.next())
            {
                count++;
//                System.out.println(results.getString("courseCode")+" "+results.getString("courseName")+" "+results.getString("studentId"));
                enrolledStudents.add(new EnrolledStudent(results.getString("courseCode"),results.getString("courseName"),results.getString("studentId")));
            }
            if(count==0)
            {
                throw new NoEnrolledStudentsException(profId, courseCode);
            }
        }
        catch(SQLException e){
            System.out.println(e.getMessage());
            throw new NoEnrolledStudentsException(profId,courseCode);
        }

        return enrolledStudents;
    }

    @Override
    public boolean addGrade(String studentId, String courseCode, String grade) throws StudentNotRegistered, GradeAssignedException, ClassNotFoundException {
        Connection conn = DBUtils.getConnection();

        Class.forName("com.mysql.cj.jdbc.Driver");
        boolean flag=false;
        try{
            PreparedStatement checkStatement=conn.prepareStatement(SQLQueriesProfessor.GET_ASSIGNED_GRADE);
            checkStatement.setString(1,courseCode);
            checkStatement.setString(2,studentId);
            ResultSet resultSet=checkStatement.executeQuery();
            if(resultSet.next())
            {
                String assignedGrade=resultSet.getString("grade");
                if(assignedGrade.equals("NA"))
                {
                    PreparedStatement statement= conn.prepareStatement(SQLQueriesProfessor.ADD_GRADE);
                    statement.setString(1, grade);
                    statement.setString(2,courseCode);
                    statement.setString(3,studentId);
                    statement.executeUpdate();
                    flag=true;

                }
                else{
                    throw new GradeAssignedException(studentId, courseCode);

                }
            }
            else {
                throw new StudentNotRegistered(studentId,courseCode);
            }


        } catch (SQLException e) {
            System.out.println(e.getMessage());

        }
        return flag;
    }


    public boolean assignCourse(String profId,String courseCode) throws ProfessorAssignedException, CourseNotFoundException,ClassNotFoundException {
        Connection conn = DBUtils.getConnection();

        Class.forName("com.mysql.cj.jdbc.Driver");
        boolean flag=false;
        try{
            PreparedStatement checkStatement=conn.prepareStatement(SQLQueriesProfessor.GET_ASSIGNED_PROF);
            checkStatement.setString(1,courseCode);
            ResultSet resultSet=checkStatement.executeQuery();
            if(resultSet.next())
            {
                String assignedProfId=resultSet.getString("profId");
                if(assignedProfId==null)
                {
                    PreparedStatement statement= conn.prepareStatement(SQLQueriesProfessor.ASSIGN_COURSE);
                    statement.setString(1, profId);
                    statement.setString(2,courseCode);

                    int rowsAffected=statement.executeUpdate();
                    if(rowsAffected>0) {
                        flag = true;
                    }
                }
                else{
                    throw new ProfessorAssignedException(courseCode);
                }
            }
            else{
                throw new CourseNotFoundException(courseCode);
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return flag;

    }


    @Override
    public boolean getProfessors(String profId,String password) throws SQLException, ClassNotFoundException {
        Connection conn = DBUtils.getConnection();

        Class.forName("com.mysql.cj.jdbc.Driver");

        List<Professor> professors=new ArrayList<>();

        try{
            PreparedStatement statement= conn.prepareStatement(SQLQueriesProfessor.GET_PROFS);
            statement.setString(1,profId);
            statement.setString(2,password);
            ResultSet results = statement.executeQuery();

            if (results.next())
                return true;
        }
        catch(SQLException e){
            System.out.println(e.getMessage());
        }

        return false;
    }
}
