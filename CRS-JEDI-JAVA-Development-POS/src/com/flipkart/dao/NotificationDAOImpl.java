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
    @Override
    public int sendNotification(int refId) {
        int notificationId=0;
        Connection connection=DBUtils.getConnection();
        try
        {
            //INSERT_NOTIFICATION = "insert into notification(studentId,type,referenceId) values(?,?,?);";
            PreparedStatement ps = connection.prepareStatement(SQLQueriesConstants.INSERT_NOTIFICATION);
            ps.setString(1, "Payment Successful");
            ps.setInt(2,refId);


            ps.executeUpdate();
            ResultSet results=ps.getGeneratedKeys();




        }
        catch(Exception ex)
        {
            System.out.println(ex);
        }
        return notificationId;
    }
    public UUID addPayment(String studentId, ModeOfPayment modeOfPayment,double amount)
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
        catch(Exception ex)
        {
            System.out.println(ex);
        }
        return referenceId;
    }

}