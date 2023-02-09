package com.flipkart.service;

import com.flipkart.dao.UserDAO;
import com.flipkart.dao.UserDAOImpl;
import com.flipkart.exception.UserNotFoundException;

public class UserServiceOperation implements UserService{
    UserDAO userDAO = UserDAOImpl.getInstance();
    public String getRole(String userId) {
        return null;
    }

    public boolean changePassword(String userID, String password) {
        return userDAO.updatePassword(userID, password);
    }

    public boolean verifyCredentials(String userID, String password) throws UserNotFoundException {
        return userDAO.verifyCredentials(userID,password);
    }
}
