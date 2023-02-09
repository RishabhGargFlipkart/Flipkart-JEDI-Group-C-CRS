package com.flipkart.service;

import java.sql.SQLException;
import java.util.UUID;

import com.flipkart.bean.Notification;
import com.flipkart.dao.NotificationDAOImpl;

import com.flipkart.dao.NotificationDAO;

public class NotificationServiceOperation implements NotificationService {

    private static volatile NotificationServiceOperation instance=null;
    NotificationDAO notificationDaoInterface= NotificationDAOImpl.getInstance();
    private NotificationServiceOperation()
    {

    }

    public static NotificationServiceOperation getInstance()
    {
        if(instance==null)
        {
            // This is a synchronized block, when multiple threads will access this instance
            synchronized(NotificationServiceOperation.class){
                instance=new NotificationServiceOperation();
            }
        }
        return instance;
    }

    /**
     * @param refId
     * @param notifId
     */
    @Override
    public Notification sendNotification(int refId,int notifId) {
        Notification notification = new Notification();
        try
        {
            notification = notificationDaoInterface.sendNotification(refId,notifId);

        }
        catch(SQLException ex)
        {
            System.out.println("Error occurred "+ex.getMessage());
        }
        return notification;
    }

    /**
     * @param notificationId
     * @return
     */
    @Override
    public UUID getReferenceID(int notificationId) {
        return null;
    }

}