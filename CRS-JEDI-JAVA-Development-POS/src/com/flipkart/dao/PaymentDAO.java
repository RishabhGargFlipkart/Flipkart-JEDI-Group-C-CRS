package com.flipkart.dao;

public interface PaymentDAO {
    /**
     * @param refId
     * @param studentId
     * @param amount
     * @param type
     * @param bank
     */
    public void addPayment(int refId,String studentId,double amount,String type,String bank);

    /**
     * @param studentId
     */
    public void isPaid(String studentId);
}
