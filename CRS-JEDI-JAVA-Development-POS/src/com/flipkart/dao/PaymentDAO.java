package com.flipkart.dao;

public interface PaymentDAO {
    public void addPayment(int refId,String studentId,double amount,String type,String bank);
}
