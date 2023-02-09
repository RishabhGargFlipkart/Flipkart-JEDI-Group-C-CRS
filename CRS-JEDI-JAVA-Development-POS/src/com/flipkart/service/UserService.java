package com.flipkart.service;

import com.flipkart.exception.UserNotFoundException;

/**
 * @author JEDI-Group-C Praneet, Rishabh, Akhil, Manan, Nidhi, Shivanshu, Divyansh
 * Interface for Admin Dao Operations
 *
 */

public interface UserService{

    /**
     * Method to get role of userId
     * @param userId
     * @return role
     */
    public String getRole(String userId);

    /**
     * Method to change password
     * @param userID
     * @param password
     * @return boolean
     */
    public boolean changePassword(String userID, String password);

    /**
     * Method to verify credentials
     * @param userID
     * @param password
     * @return
     */
    public boolean verifyCredentials(String userID, String password) throws UserNotFoundException;
}
