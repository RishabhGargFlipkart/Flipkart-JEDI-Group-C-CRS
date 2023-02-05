package com.flipkart.dao;

import com.flipkart.bean.Course;
import com.flipkart.bean.EnrolledStudent;
import com.flipkart.bean.Professor;
import com.flipkart.constant.SQLQueriesConstants;
import com.flipkart.utils.DBUtils;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProfessorDAOImpl implements ProfessorDAO {

    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost/crsdatabase";

    //  Database credentials
    static final String USER = "root";
    static final String PASS = "Nidhi@2002"; //root
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
    public List<Course> getCourses(String profId) throws ClassNotFoundException, SQLException {

        Connection conn= DriverManager.getConnection(DB_URL,USER,PASS);
        Class.forName("com.mysql.jdbc.Driver");
        List<Course> ans=new ArrayList<Course>();
        try{
            PreparedStatement statement= conn.prepareStatement(SQLQueriesConstants.GET_COURSES);
            statement.setString(1,profId);
            ResultSet results=statement.executeQuery();
            while(results.next())
            {
                ans.add(new Course(results.getString("courseCode"),results.getString("courseName"),results.getString("profId"),results.getInt("seats"),results.getDouble("fee")));
            }
        }
        catch(SQLException e)
        {
            System.out.println("SQL Exception");
        }
        finally{
            try{
                conn.close();
            }
            catch(SQLException e){
                e.printStackTrace();
            }
        }
        return ans;
    }

    @Override
    public List<EnrolledStudent> getEnrolledStudent(String profId) throws SQLException, ClassNotFoundException {

        Connection conn= DriverManager.getConnection(DB_URL,USER,PASS);
        Class.forName("com.mysql.jdbc.Driver");
        List<EnrolledStudent> enrolledStudents=new ArrayList<EnrolledStudent>();
        try{
            PreparedStatement statement= conn.prepareStatement(SQLQueriesConstants.GET_ENROLLED_STUDENTS);
            statement.setString(1, profId);

            ResultSet results = statement.executeQuery();
            while(results.next())
            {
                //public EnrolledStudent(String courseCode, String courseName, int studentId)
                enrolledStudents.add(new EnrolledStudent(results.getString("courseCode"),results.getString("courseName"),results.getString("studentId")));
            }
        }
        catch(SQLException e){
            System.out.println("SQL Exception");
        }
        finally
        {
            try {
                conn.close();
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        return enrolledStudents;
    }

    @Override
    public boolean addGrade(String studentId, String courseCode, String grade) throws SQLException, ClassNotFoundException {
        Connection conn= DriverManager.getConnection(DB_URL,USER,PASS);
        Class.forName("com.mysql.jdbc.Driver");
        boolean flag=false;
        try{
            PreparedStatement statement= conn.prepareStatement(SQLQueriesConstants.ADD_GRADE);
            statement.setString(1, grade);
            statement.setString(2,courseCode);
            statement.setString(3,studentId);

            int rowsAffected= statement.executeUpdate();
            if(rowsAffected>0) {
                flag = true;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally
        {
            try {
                conn.close();
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        return flag;
    }


    public boolean assignCourse(String profId,String courseCode) throws SQLException, ClassNotFoundException {
        Connection conn= DriverManager.getConnection(DB_URL,USER,PASS);
        Class.forName("com.mysql.jdbc.Driver");
        boolean flag=false;
        try{
            PreparedStatement statement= conn.prepareStatement(SQLQueriesConstants.ASSIGN_COURSE);
            statement.setString(1, profId);
            statement.setString(2,courseCode);

            int rowsAffected=statement.executeUpdate();
            if(rowsAffected>0) {
                flag = true;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            try {
                conn.close();
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        return flag;

    }
    @Override
    public List<Professor> getProfessors() throws SQLException, ClassNotFoundException {

        Connection conn= DriverManager.getConnection(DB_URL,USER,PASS);
        Class.forName("com.mysql.jdbc.Driver");

        List<Professor> professors=new ArrayList<>();

        try{
            PreparedStatement statement= conn.prepareStatement(SQLQueriesConstants.GET_PROFS);
            ResultSet results = statement.executeQuery();
            while(results.next())
            {
                Professor professor=new Professor();
                professor.setUserId(results.getString("profId"));
                professor.setDepartment(results.getString("department"));
                professor.setPassword(results.getString("password"));
                professors.add(professor);
            }
        }
        catch(SQLException e){
            System.out.println("SQL Exception");
        }
        finally
        {
            try {
                conn.close();
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        return professors;
    }
}
