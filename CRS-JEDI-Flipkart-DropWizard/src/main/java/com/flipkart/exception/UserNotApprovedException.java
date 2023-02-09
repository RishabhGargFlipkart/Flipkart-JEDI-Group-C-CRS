package com.flipkart.exception;

/**
 * @author JEDI-Group-C Praneet, Rishabh, Akhil, Manan, Nidhi, Shivanshu, Divyansh
 * Exception to check if user is approved by administration
 *
 */
public class UserNotApprovedException extends Exception{
	private String userId;
	
	/**
	 * Constructor to set userId
	 * @param userId
	 */
	public UserNotApprovedException(String userId) {
		this.userId = userId;
	}

	/**
	 * Getter for userId
	 * @return userId
	 */
	public String getUserId() {
		return userId;
	}

}
