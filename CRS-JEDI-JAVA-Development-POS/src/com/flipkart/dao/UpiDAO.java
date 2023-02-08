package com.flipkart.dao;

public interface UpiDAO {
    /**
     * @param refId
     * @param upiId
     * @param serviceProvider
     */
    public void addUPI(int refId,String upiId,String serviceProvider);
}