package com.flipkart.constant;

/**
 * @author JEDI-Group-C Praneet, Rishabh, Akhil, Manan, Nidhi, Shivanshu, Divyansh
 * Enumeration class for BankConstant names
 *
 */
public enum BankConstant {

    HDFC("HDFC BankConstant"),
    SBI("SBI"),
    ICICI("ICICI BankConstant"),
    Axis("Axis BankConstant");
    
    final private String value;
    private BankConstant(String value)
    {
        this.value=value;
    }
    public String hasValue()
    {
        return this.value;
    }
}
