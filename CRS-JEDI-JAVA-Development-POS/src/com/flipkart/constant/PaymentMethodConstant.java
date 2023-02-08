package com.flipkart.constant;

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

