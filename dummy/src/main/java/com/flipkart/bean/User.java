package com.flipkart.bean;
/**
 * @author JEDI-Group-C Praneet, Rishabh, Akhil, Manan, Nidhi, Shivanshu, Divyansh
 * Class for User
 *
 */
public class User {
    private String userId;
    private String name;
    private String role;
    private String password;

    /**
     * Get Id of User
     * @return userId
     */
    public String getUserId() {
        return userId;
    }

    /**
     * Set Id of User
     * @param userId
     */
    public void setUserId(String userId) {
        this.userId = userId;
    }

    /**
     * Get name
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * Set name
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Method to get role
     * @return role
     */
    public String getRole() {
        return role;
    }

    /**
     * Method to set role
     * @param role
     */
    public void setRole(String role) {
        this.role = role;
    }

    /**
     * Method to get password
     * @return password
     */
    public String getPassword() {
        return password;
    }

    /**
     * Method to set password
     * @param password
     */
    public void setPassword(String password) {
        this.password = password;
    }
}
