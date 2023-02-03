package com.flipkart.bean;

import com.flipkart.constant.PaymentMethod;
import com.flipkart.constant.Bank;
public class Payment {
    private String referenceId;
    private String studentId;
    private double amount;
    private PaymentMethod paymentType;

    public Payment(String referenceId, String studentId, double amount, PaymentMethod paymentType, Bank bank) {
        this.referenceId = referenceId;
        this.studentId = studentId;
        this.amount = amount;
        this.paymentType = paymentType;
        this.bank = bank;
    }

    private Bank bank;

    public String getReferenceId() {
        return referenceId;
    }

    public void setReferenceId(String referenceId) {
        this.referenceId = referenceId;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public PaymentMethod getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(PaymentMethod paymentType) {
        this.paymentType = paymentType;
    }

    public Bank getBank() {
        return bank;
    }

    public void setBank(Bank bank) {
        this.bank = bank;
    }

    public Payment() {
    }

    
    
}
