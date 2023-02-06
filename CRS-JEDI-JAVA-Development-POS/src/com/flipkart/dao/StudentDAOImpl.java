package com.flipkart.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.flipkart.bean.Student;
import com.flipkart.constant.SQLQueriesConstants;
import com.flipkart.utils.DBUtils;
public class StudentDAOImpl implements StudentDAO {

    private static volatile StudentDAOImpl instance=null;
    private StudentDAOImpl()
    {

    }
    public static StudentDAOImpl getInstance()
    {
        if(instance==null)
        {
            synchronized(StudentDAOImpl.class){
                instance=new StudentDAOImpl();
            }
        }
        return instance;
    }
    @Override
    public void addStudent(Student student) {
        Connection connection=DBUtils.getConnection();
        try
        {
            //open db connection
            PreparedStatement preparedStatement=connection.prepareStatement(SQLQueriesConstants.ADD_USER_QUERY);
            preparedStatement.setString(1, student.getUserId());
            preparedStatement.setString(2, student.getName());
            preparedStatement.setString(3, student.getRole());
            preparedStatement.setString(4, student.getPassword());
            preparedStatement.setString(5, student.getGender());
            preparedStatement.setString(6, student.getAddress());
            int rowsAffected=preparedStatement.executeUpdate();
            if(rowsAffected==1)
            {
                //add the student record
                //"insert into student (userId,branchName,batch,isApproved) values (?,?,?,?)";
                PreparedStatement preparedStatementStudent;
                preparedStatementStudent=connection.prepareStatement(SQLQueriesConstants.ADD_STUDENT,Statement.RETURN_GENERATED_KEYS);
                preparedStatementStudent.setString(1,student.getUserId());
                preparedStatementStudent.setString(2, student.getBranchName());
                preparedStatementStudent.setInt(3, student.getBatch());
                preparedStatementStudent.setBoolean(4, false);
                preparedStatementStudent.setBoolean(5, false);
                preparedStatementStudent.setBoolean(6, false);
                preparedStatementStudent.executeUpdate();
                ResultSet results=preparedStatementStudent.getGeneratedKeys();
                if(results.next())
                    results.getInt(1);
            }


        }
        catch(Exception ex)
        {
            System.out.println(ex);
        }
        finally
        {
            try {
                connection.close();
            } catch (SQLException e) {
                System.out.println(e.getMessage()+"SQL error");
                e.printStackTrace();
            }
        }
    }
    @Override
    public int getStudentId(String userId) {
        Connection connection=DBUtils.getConnection();
        try {
            PreparedStatement statement = connection.prepareStatement(SQLQueriesConstants.GET_STUDENT_ID);
            statement.setString(1, userId);
            ResultSet rs = statement.executeQuery();

            if(rs.next())
            {
                return rs.getInt("studentId");
            }

        }
        catch(SQLException e)
        {
           System.out.println(e);
        }

        return 0;
    }
    @Override
    public boolean isApproved(int studentId) {
        Connection connection=DBUtils.getConnection();
        try {
            PreparedStatement statement = connection.prepareStatement(SQLQueriesConstants.IS_APPROVED);
            statement.setInt(1, studentId);
            ResultSet rs = statement.executeQuery();

            if(rs.next())
            {
                return rs.getBoolean("isApproved");
            }

        }
        catch(SQLException e)
        {
            System.out.println(e);
        }

        return false;
    }

}