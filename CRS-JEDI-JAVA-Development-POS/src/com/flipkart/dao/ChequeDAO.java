package com.flipkart.dao;

/**
 * @author JEDI-Group-C Praneet, Rishabh, Akhil, Manan, Nidhi, Shivanshu, Divyansh
 * Interface for Cheque Dao Operations
 *
 */
public interface ChequeDAO {
    /**
     * This method adds the cheque payment to database
     * @param refId
     * @param chequeNo
     */
    public void addCheque(int refId,String chequeNo);
}
