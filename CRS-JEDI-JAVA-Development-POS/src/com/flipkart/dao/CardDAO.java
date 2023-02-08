package com.flipkart.dao;

import java.util.Date;

public interface CardDAO {
    /**
     * @param refId
     * @param cardNo
     * @param type
     * @param cvv
     */
    public void addCard(int refId, String cardNo, String type, int cvv, Date date);

}
