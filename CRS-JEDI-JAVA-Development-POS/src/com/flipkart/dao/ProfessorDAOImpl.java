package com.flipkart.dao;

import com.flipkart.bean.Course;
import com.flipkart.bean.EnrolledStudent;
import com.flipkart.constant.SQLQueriesConstants;
import com.flipkart.utils.DBUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
    public List<Course> getCourses(String profId) {

        Connection conn= DBUtils.getConnection();
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
    public List<EnrolledStudent> getEnrolledStudent(String profId) {

        Connection conn=DBUtils.getConnection();
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
    public Boolean addGrade(String studentId, String courseCode, String grade) {
        return null;
    }

    @Override
    public String getProfessorById(String profId) {
        return null;
    }
}
