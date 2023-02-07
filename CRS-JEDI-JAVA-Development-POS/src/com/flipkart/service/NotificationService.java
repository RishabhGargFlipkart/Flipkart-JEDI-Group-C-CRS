package com.flipkart.service;
import java.util.UUID;
import com.flipkart.constant.ModeOfPayment;
import com.flipkart.constant.NotificationType;

public interface NotificationService {
    /**
     * @param refId
     * @param notifId
     */
    public void sendNotification( int refId,int notifId);

    /**
     * @param notificationID
     * @return
     */
    public UUID getReferenceID(int notificationID);
}
