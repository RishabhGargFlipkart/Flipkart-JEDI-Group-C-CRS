package com.flipkart.dao;

import com.flipkart.constant.NotificationType;
import com.flipkart.constant.ModeOfPayment;
import java.sql.SQLException;
public interface NotificationDAO {
    public int sendNotification(NotificationType type,int studentId,ModeOfPayment modeOfPayment,double amount) throws SQLException;
}
