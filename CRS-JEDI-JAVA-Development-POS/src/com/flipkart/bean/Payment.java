package com.flipkart.bean;

import com.flipkart.constant.PaymentMethodConstant;
import com.flipkart.constant.BankConstant;
public class Payment {
    private String referenceId;
    private String studentId;
    private double amount;
    private PaymentMethodConstant paymentType;

    public Payment(String referenceId, String studentId, double amount, PaymentMethodConstant paymentType, BankConstant bankConstant) {
        this.referenceId = referenceId;
        this.studentId = studentId;
        this.amount = amount;
        this.paymentType = paymentType;
        this.bankConstant = bankConstant;
    }

    private BankConstant bankConstant;

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

    public PaymentMethodConstant getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(PaymentMethodConstant paymentType) {
        this.paymentType = paymentType;
    }

    public BankConstant getBank() {
        return bankConstant;
    }

    public void setBank(BankConstant bankConstant) {
        this.bankConstant = bankConstant;
    }

    public Payment() {
    }

    
    
}
