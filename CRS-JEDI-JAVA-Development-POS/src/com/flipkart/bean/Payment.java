package com.flipkart.bean;
/**
 * @author JEDI-Group-C Praneet, Rishabh, Akhil, Manan, Nidhi, Shivanshu, Divyansh
 * Class for Payment, for Student after Registration
 *
 */
import com.flipkart.constant.BankConstant;
import com.flipkart.constant.PaymentMethodConstant;

public class Payment {
    private String referenceId;
    private String studentId;
    private double amount;
    private PaymentMethodConstant paymentType;

    /**
     * Parameterized constructor
     * @param referenceId
     * @param studentId
     * @param amount
     * @param paymentType
     * @param bankConstant
     */
    public Payment(String referenceId, String studentId, double amount, PaymentMethodConstant paymentType, BankConstant bankConstant) {
        this.referenceId = referenceId;
        this.studentId = studentId;
        this.amount = amount;
        this.paymentType = paymentType;
        this.bankConstant = bankConstant;
    }

    private BankConstant bankConstant;

    /**
     * Get reference Id
     * @return
     */
    public String getReferenceId() {
        return referenceId;
    }

    /**
     * Set reference Id
     * @param referenceId
     */
    public void setReferenceId(String referenceId) {
        this.referenceId = referenceId;
    }

    /**
     * return Id of student making payment
     * @return studentId
     */
    public String getStudentId() {
        return studentId;
    }

    /**
     * set Id of student making payment
     * @param studentId
     */
    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    /**
     * Get payment amount
     * @return amount
     */
    public double getAmount() {
        return amount;
    }

    /**
     * Set payment amount
     * @param amount
     */
    public void setAmount(double amount) {
        this.amount = amount;
    }

    /**
     * Get Type of payment
     * @return paymentType
     */
    public PaymentMethodConstant getPaymentType() {
        return paymentType;
    }

    /**
     * Set Type of payment
     * @param paymentType
     */
    public void setPaymentType(PaymentMethodConstant paymentType) {
        this.paymentType = paymentType;
    }

    /**
     * Get name of bankConstant
     * @return bankConstant
     */
    public BankConstant getBank() {
        return bankConstant;
    }

    /**
     * Set name of bankConstant
     * @param bankConstant
     */

    public void setBank(BankConstant bankConstant) {
        this.bankConstant = bankConstant;
    }

    /**
     * Default constructor
     */
    public Payment() {}

}
