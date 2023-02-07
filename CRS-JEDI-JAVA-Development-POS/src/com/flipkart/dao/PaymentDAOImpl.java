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
    public int addPayment(String studentId, double amount, String type, String bank) {
        int refId=0;
        try {
            String sql = SQLQueriesConstants.INSERT_PAYMENT;
            statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1,studentId);
            statement.setDouble(2,amount);
            statement.setString(3,type);
            statement.setString(4,bank);
            statement.executeUpdate();
            ResultSet rs=statement.getGeneratedKeys();
            if(rs.next())
                refId=rs.getInt(1);
        }
        catch(SQLException e)
        {
            System.out.println(e.getMessage());
        }
        return refId;
    }

}
