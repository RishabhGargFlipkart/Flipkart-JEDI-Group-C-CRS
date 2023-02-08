package com.flipkart.service;
import java.util.UUID;

/**
 * @author JEDI-Group-C Praneet, Rishabh, Akhil, Manan, Nidhi, Shivanshu, Divyansh
 * Interface for Admin Dao Operations
 *
 */
public interface NotificationService {
    /**
     * Method to send notification
     * @param refId
     * @param notifId
     */
    public void sendNotification(int refId, int notifId);

    /**
     * Method to get reference id
     * @param notificationID
     * @return
     */
    public UUID getReferenceID(int notificationID);
}
