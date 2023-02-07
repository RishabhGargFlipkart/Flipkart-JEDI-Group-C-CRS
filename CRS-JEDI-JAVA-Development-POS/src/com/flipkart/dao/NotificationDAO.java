package com.flipkart.dao;

import java.sql.SQLException;
import java.util.UUID;

import com.flipkart.constant.ModeOfPayment;
import com.flipkart.constant.NotificationType;

public interface NotificationDAO {
    /**
     * @param refId
     * @param notifId
     * @throws SQLException
     */
    public void sendNotification(int refId,int notifId) throws SQLException;
}