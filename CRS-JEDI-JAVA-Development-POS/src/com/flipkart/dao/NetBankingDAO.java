package com.flipkart.dao;

/**
 * @author JEDI-Group-C Praneet, Rishabh, Akhil, Manan, Nidhi, Shivanshu, Divyansh
 * Interface for Net banking Dao Operations
 *
 */
public interface NetBankingDAO {
    /**
     * This method adds the net banking payment to database
     * @param refId
     * @param accountNo
     * @param ifsc
     */
    public void addTransaction(int refId,int accountNo,String ifsc);

}
