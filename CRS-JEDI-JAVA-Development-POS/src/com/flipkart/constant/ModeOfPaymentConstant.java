package com.flipkart.constant;


public enum ModeOfPaymentConstant {

    CARD,CHEQUE,UPI,NET_BANKING,CASH;

    public static ModeOfPaymentConstant getModeofPayment(int value)
    {
        switch(value)
        {
            case 1:
                return ModeOfPaymentConstant.CARD;
            case 4:
                return ModeOfPaymentConstant.NET_BANKING;
            case 3:
                return ModeOfPaymentConstant.UPI;
            case 5:
                return ModeOfPaymentConstant.CASH;
            case 2:
                return ModeOfPaymentConstant.CHEQUE;
            default:
                return null;

        }

    }

}
