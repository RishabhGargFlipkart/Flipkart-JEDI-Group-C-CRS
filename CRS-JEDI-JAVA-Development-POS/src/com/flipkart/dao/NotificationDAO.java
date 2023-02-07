package com.flipkart.dao;

import java.sql.SQLException;
import java.util.UUID;

import com.flipkart.constant.ModeOfPayment;
import com.flipkart.constant.NotificationType;

public interface NotificationDAO {
    public int sendNotification(NotificationType type,String studentId,ModeOfPayment modeOfPayment,double amount);
}