package com.flipkart.dao;

import com.flipkart.constant.SQLQueriesConstants;
import com.flipkart.utils.DBUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class UpiDAOImpl {
    private static volatile UpiDAOImpl instance=null;

    private UpiDAOImpl(){}

    public static UpiDAOImpl getInstance()
    {
        if(instance == null)
        {
            synchronized(UpiDAOImpl.class){
                instance = new UpiDAOImpl();
            }
        }
        return instance;
    }
    private PreparedStatement statement = null;
    Connection connection = DBUtils.getConnection();

    /**
     * @param refId
     * @param upiId
     * @param serviceProvider
     */
    public void addUPI(int refId,String upiId,String serviceProvider){
        try {
            String sql= SQLQueriesConstants.INSERT_UPI;
            statement=connection.prepareStatement(sql);
            statement.setInt(1,refId);
            statement.setString(2,upiId);
            statement.setString(3,serviceProvider);
            statement.executeUpdate();
        }catch (SQLException e)
        {
            System.out.println(e.getMessage());
        }
    }
}