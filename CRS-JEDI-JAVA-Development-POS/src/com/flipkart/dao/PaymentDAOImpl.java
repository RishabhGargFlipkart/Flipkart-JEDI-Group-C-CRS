package com.flipkart.dao;

import com.flipkart.constant.SQLQueriesConstants;
import com.flipkart.utils.DBUtils;

import java.sql.*;

public class PaymentDAOImpl implements PaymentDAO{
    private static volatile PaymentDAOImpl instance=null;

    private PaymentDAOImpl(){}

    public static PaymentDAOImpl getInstance()
    {
        if(instance == null)
        {
            synchronized(PaymentDAOImpl.class){
                instance = new PaymentDAOImpl();
            }
        }
        return instance;
    }

    private PreparedStatement statement = null;
    Connection connection = DBUtils.getConnection();
    @Override
    public void addPayment(int refId,String studentId, double amount, String type, String bank) {

        try {
            String sql = SQLQueriesConstants.INSERT_PAYMENT;
            statement = connection.prepareStatement(sql);
            statement.setInt(1,refId);
            statement.setString(2,studentId);
            statement.setDouble(3,amount);
            statement.setString(4,type);
            statement.setString(5,bank);
            statement.executeUpdate();
        }
        catch(SQLException e)
        {
            System.out.println(e.getMessage());
        }
    }

}
