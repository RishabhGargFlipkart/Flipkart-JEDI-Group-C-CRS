package com.flipkart.service;
import java.util.UUID;
import com.flipkart.constant.ModeOfPayment;
public class NotificationServiceOperation implements NotificationService {

    public int sendNotification(String notificationType,String studentID,ModeOfPayment modeOfPayment,double amount){
        return 0;
    }

    public UUID getReferenceID(int notificationID)
    {
        return null;
    }
}
