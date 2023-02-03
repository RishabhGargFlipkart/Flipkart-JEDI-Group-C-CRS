package com.flipkart.constant;


public enum ModeOfPayment {

    CREDIT_CARD,NET_BANKING,DEBIT_CARD,CASH;

    public static ModeOfPayment getModeofPayment(int value)
    {
        switch(value)
        {
            case 1:
                return ModeOfPayment.CREDIT_CARD;
            case 2:
                return ModeOfPayment.NET_BANKING;
            case 3:
                return ModeOfPayment.DEBIT_CARD;
            case 4:
                return ModeOfPayment.CASH;
            default:
                return null;

        }

    }

}
