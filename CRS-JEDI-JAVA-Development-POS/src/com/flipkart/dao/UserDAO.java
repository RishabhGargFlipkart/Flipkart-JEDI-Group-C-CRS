package com.flipkart.dao;

import com.flipkart.exception.UserNotFoundException;

public interface UserDAO {
    public boolean verifyCredentials(String userId,String password) throws UserNotFoundException, UserNotFoundException;
    public boolean updatePassword(String userID,String newPassword);
    
}
