package com.flipkart.service;
import java.util.UUID;
import com.flipkart.constant.ModeOfPayment;
import com.flipkart.constant.NotificationType;

public interface NotificationService {
    public void sendNotification( int refId,int notifId);
    public UUID getReferenceID(int notificationID);
}
