package com.flipkart.constant;

public enum PaymentMethod {
    UPI("UPI"),
    Cash("Cash"),
    Cheque("Cheque"),
    NetBanking("Net Banking"),
    Card("Card");

    final private String value;
    private PaymentMethod(String value)
    {
        this.value=value;
    }
    public String hasValue()
    {
        return this.value;
    }



    


}

