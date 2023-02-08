package com.flipkart.dao;

import com.flipkart.exception.UserNotFoundException;

public interface UserDAO {
    /**
     * This method verifies if the user with userId exists and logs in the user if he/she enters the correct password.
     * @param userId
     * @param password
     * @return
     * @throws UserNotFoundException
     */
    public boolean verifyCredentials(String userId,String password) throws UserNotFoundException;

    /**
     * This method updates the password corresponding to the user with the given userId.
     * @param userID
     * @param newPassword
     * @return
     */
    public boolean updatePassword(String userID,String newPassword);
    
}
