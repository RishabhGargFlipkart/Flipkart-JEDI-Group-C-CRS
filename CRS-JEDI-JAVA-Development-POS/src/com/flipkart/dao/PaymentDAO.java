package com.flipkart.dao;

public interface PaymentDAO {
    public int addPayment(String studentId,double amount,String type,String bank);
}
