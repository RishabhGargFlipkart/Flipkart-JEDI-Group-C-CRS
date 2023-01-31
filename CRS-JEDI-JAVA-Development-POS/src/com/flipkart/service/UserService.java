package com.flipkart.service;

public interface UserService{
    public String getRole(String userId);
    public boolean changePassword(String userID, String password);
    public boolean verifyCredentials(String userID, String password);
}
