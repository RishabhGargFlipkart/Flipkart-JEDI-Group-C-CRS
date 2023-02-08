package com.flipkart.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.flipkart.utils.DBUtils;
import com.flipkart.constant.SQLQueriesAdmin;
import java.sql.SQLException;

import com.flipkart.exception.UserNotFoundException;

public class UserDAOImpl implements UserDAO{
    private static volatile UserDAOImpl instance=null;

    private UserDAOImpl(){}

    public static UserDAOImpl getInstance()
    {
        if(instance==null)
        {
            // This is a synchronized block, when multiple threads will access this instance
            synchronized(UserDAOImpl.class){
                instance=new UserDAOImpl();
            }
        }
        return instance;
    }

    /**
     * This method verifies if the user with userId exists and logs in the user if he/she enters the correct password.
     * @param userId
     * @param password
     * @return
     * @throws UserNotFoundException
     */
    @Override
    public boolean verifyCredentials(String userId, String password) throws UserNotFoundException{
        Connection connection = DBUtils.getConnection();
        try
        {
            //open db connection
            PreparedStatement preparedStatement=connection.prepareStatement(SQLQueriesAdmin.VERIFY_CREDENTIALS);
            preparedStatement.setString(1,userId);
            ResultSet resultSet = preparedStatement.executeQuery();

            if(!resultSet.next())
                throw new UserNotFoundException(userId);
            else if(password.equals(resultSet.getString("password")))
            {
                return true;
            }
            else
            {
                return false;
            }

        }
        catch(SQLException ex)
        {
            System.out.println("Something went wrong, please try again! "+ ex.getMessage());
        }
        catch(UserNotFoundException e)
        {
            System.out.println(e.getMessage());
        }

        return false;
    }

    /**
     * This method updates the password corresponding to the user with the given userId.
     * @param userId
     * @param newPassword
     * @return
     */
    @Override
    public boolean updatePassword(String userId, String newPassword) {
        Connection connection=DBUtils.getConnection();
        try {
            PreparedStatement statement = connection.prepareStatement(SQLQueriesAdmin.UPDATE_PASSWORD);

            statement.setString(1, newPassword);
            statement.setString(2, userId);

            int row = statement.executeUpdate();

            if(row==1)
                return true;
            else
                return false;
        }
        catch(SQLException e)
        {
            System.out.println(e.getMessage());
        }

        return false;
    }
}
