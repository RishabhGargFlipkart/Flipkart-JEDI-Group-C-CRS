package com.flipkart.dao;

public interface CardDAO {
    /**
     * @param refId
     * @param cardNo
     * @param type
     * @param cvv
     */
    public void addCard(int refId,int cardNo,String type,int cvv);

}
