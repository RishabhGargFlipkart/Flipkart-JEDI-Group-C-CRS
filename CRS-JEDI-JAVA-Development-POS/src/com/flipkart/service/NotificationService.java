package com.flipkart.service;
import java.util.UUID;

public interface NotificationService {
    public int sendNotification(String notificationType,int studentID,String modeOfPayment,double amount);
    public UUID getReferenceID(int notificationID);
}
