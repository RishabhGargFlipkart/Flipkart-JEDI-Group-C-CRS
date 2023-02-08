package com.flipkart.dao;

public interface ChequeDAO {
    /**
     * @param refId
     * @param chequeNo
     */
    public void addCheque(int refId,String chequeNo);
}
