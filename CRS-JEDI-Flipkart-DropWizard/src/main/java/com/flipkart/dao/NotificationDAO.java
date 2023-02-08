package com.flipkart.dao;

import com.flipkart.bean.Notification;

import java.sql.SQLException;

/**
 * @author JEDI-Group-C Praneet, Rishabh, Akhil, Manan, Nidhi, Shivanshu, Divyansh
 * Interface for Notification Dao Operations
 *
 */
public interface NotificationDAO {
    /**
     * This method adds the notification during payment to database
     * @param refId
     * @param notifId
     * @throws SQLException
     */
    public Notification sendNotification(int refId, int notifId) throws SQLException;
}