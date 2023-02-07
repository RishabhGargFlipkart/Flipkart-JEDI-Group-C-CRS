package com.flipkart.constant;


public enum ModeOfPayment {

    CARD,CHEQUE,UPI,NET_BANKING,CASH;

    public static ModeOfPayment getModeofPayment(int value)
    {
        switch(value)
        {
            case 1:
                return ModeOfPayment.CARD;
            case 4:
                return ModeOfPayment.NET_BANKING;
            case 3:
                return ModeOfPayment.UPI;
            case 5:
                return ModeOfPayment.CASH;
            case 2:
                return ModeOfPayment.CHEQUE;
            default:
                return null;

        }

    }

}
