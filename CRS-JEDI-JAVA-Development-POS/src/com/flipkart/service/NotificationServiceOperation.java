package com.flipkart.service;

import java.sql.SQLException;
import java.util.UUID;

import com.flipkart.dao.NotificationDAOImpl;

import com.flipkart.constant.ModeOfPayment;
import com.flipkart.constant.NotificationType;
import com.flipkart.dao.NotificationDAO;
import com.flipkart.dao.NotificationDAOImpl;

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
    @Override
    public void sendNotification(int refId,int notifId) {
        try
        {
            notificationDaoInterface.sendNotification( refId,notifId);

        }
        catch(Exception ex)
        {
            System.out.println("Error occured "+ex.getMessage());
        }
    }

    @Override
    public UUID getReferenceID(int notificationId) {
        return null;
    }

}