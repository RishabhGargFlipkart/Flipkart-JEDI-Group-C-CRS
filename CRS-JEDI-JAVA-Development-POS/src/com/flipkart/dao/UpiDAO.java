package com.flipkart.dao;

/**
 * @author JEDI-Group-C Praneet, Rishabh, Akhil, Manan, Nidhi, Shivanshu, Divyansh
 * Interface for UPI Dao Operations
 *
 */
public interface UpiDAO {
    /**
     * This method adds the UPI payment to database
     * @param refId
     * @param upiId
     * @param serviceProvider
     */
    public void addUPI(int refId,String upiId,String serviceProvider);
}