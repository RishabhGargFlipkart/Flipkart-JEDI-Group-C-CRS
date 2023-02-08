package com.flipkart.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.UUID;

import com.flipkart.constant.ModeOfPayment;
import com.flipkart.constant.NotificationType;
import com.flipkart.constant.SQLQueriesConstants;
import com.flipkart.service.NotificationServiceOperation;
import com.flipkart.utils.DBUtils;

public class NotificationDAOImpl implements NotificationDAO{
    private static volatile NotificationDAOImpl instance=null;
    private NotificationDAOImpl()
    {

    }
    public static NotificationDAOImpl getInstance()
    {
        if(instance==null)
        {
            // This is a synchronized block, when multiple threads will access this instance
            synchronized(NotificationDAOImpl.class){
                instance=new NotificationDAOImpl();
            }
        }
        return instance;
    }

    /**
     * @param refId
     * @param notifId
     * @throws SQLException
     */
    @Override
    public void sendNotification(int refId,int notifId) throws SQLException{
        Connection connection=DBUtils.getConnection();
        try
        {
            PreparedStatement ps = connection.prepareStatement(SQLQueriesConstants.INSERT_NOTIFICATION);
            ps.setInt(1,notifId);
            ps.setString(2, "Payment Successful");
            ps.setInt(3,refId);


            ps.executeUpdate();




        }
        catch(SQLException ex)
        {
            throw ex;
        }
    }
    public UUID addPayment(String studentId, ModeOfPayment modeOfPayment,double amount) throws SQLException
    {
        UUID referenceId;
        referenceId=UUID.randomUUID();
        Connection connection=DBUtils.getConnection();
        try
        {
            referenceId=UUID.randomUUID();
            //INSERT_NOTIFICATION = "insert into notification(studentId,type,referenceId) values(?,?,?);";
            PreparedStatement statement = connection.prepareStatement(SQLQueriesConstants.INSERT_PAYMENT);
            statement.setString(1, studentId);
            statement.setString(2, modeOfPayment.toString());
            statement.setString(3,referenceId.toString());
            statement.setDouble(4, amount);
            statement.executeUpdate();
            //check if record is added
        }
        catch(SQLException ex)
        {
            throw ex;
        }
        return referenceId;
    }

}