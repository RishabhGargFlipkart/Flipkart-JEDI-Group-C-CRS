package com.flipkart.dao;

/**
 * @author JEDI-Group-C Praneet, Rishabh, Akhil, Manan, Nidhi, Shivanshu, Divyansh
 * Interface for Card Dao Operations
 *
 */
public interface CardDAO {
    /**
     * This method adds the card payment to database
     * @param refId
     * @param cardNo
     * @param type
     * @param cvv
     */
    public void addCard(int refId,int cardNo,String type,int cvv);

}
