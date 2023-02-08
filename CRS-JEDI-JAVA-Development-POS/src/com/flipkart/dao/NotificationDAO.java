package com.flipkart.dao;

import java.sql.SQLException;

public interface NotificationDAO {
    /**
     * @param refId
     * @param notifId
     * @throws SQLException
     */
    public void sendNotification(int refId,int notifId) throws SQLException;
}