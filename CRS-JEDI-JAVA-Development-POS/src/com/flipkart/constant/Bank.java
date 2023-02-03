package com.flipkart.constant;

public enum Bank {

    HDFC("HDFC Bank"),
    SBI("SBI"),
    ICICI("ICICI Bank"),
    Axis("Axis Bank");
    

    final private String value;
    private Bank(String value)
    {
        this.value=value;
    }
    public String hasValue()
    {
        return this.value;
    }
}
