package com.flipkart.service;
import java.util.UUID;
import com.flipkart.constant.ModeOfPayment;
import com.flipkart.constant.NotificationType;

public interface NotificationService {
    public int sendNotification(NotificationType type, String studentID, ModeOfPayment modeOfPayment, double amount);
    public UUID getReferenceID(int notificationID);
}
