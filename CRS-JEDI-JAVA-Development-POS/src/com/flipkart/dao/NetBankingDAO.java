package com.flipkart.dao;

public interface NetBankingDAO {
    /**
     * @param refId
     * @param accountNo
     * @param ifsc
     */
    public void addTransaction(int refId,int accountNo,String ifsc);

}
