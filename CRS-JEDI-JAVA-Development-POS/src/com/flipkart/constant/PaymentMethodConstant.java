package com.flipkart.constant;

/**
 * @author JEDI-Group-C Praneet, Rishabh, Akhil, Manan, Nidhi, Shivanshu, Divyansh
 * Enumeration class for Payment methods
 *
 */
public enum PaymentMethodConstant {
    UPI("UPI"),
    Cash("Cash"),
    Cheque("Cheque"),
    NetBanking("Net Banking"),
    Card("Card");

    final private String value;
    private PaymentMethodConstant(String value)
    {
        this.value=value;
    }
    public String hasValue()
    {
        return this.value;
    }



    


}

