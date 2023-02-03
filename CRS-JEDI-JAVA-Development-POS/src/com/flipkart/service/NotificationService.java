package com.flipkart.service;
import java.util.UUID;
import com.flipkart.constant.ModeOfPayment;
public interface NotificationService {
    public int sendNotification(String notificationType,String studentID,ModeOfPayment modeOfPayment,double amount);
    public UUID getReferenceID(int notificationID);
}
