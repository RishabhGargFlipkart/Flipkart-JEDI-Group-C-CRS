/**
 * 
 */
package com.flipkart.exception;



/**
 * @author JEDI-Group-C Praneet, Rishabh, Akhil, Manan, Nidhi, Shivanshu, Divyansh
 * Exception if userID is already in use
 *
 */
public class UserIdAlreadyInUseException extends Exception{
	private String userId;


	/**
	 * Constructor to set user id
	 * @param userId
	 */
	public UserIdAlreadyInUseException(String userId) {
		this.userId = userId;
	}

	/**
	 * Method to get user id
	 * @return
	 */
	public String getUserId() {
		return userId;
	}

	/**
	 * Method to set professor id
	 * @param userId
	 */
	public void setProfessorId(String userId) {
		this.userId = userId;
	}

	/**
	 * Method to get error message
	 * @return string
	 */
	@Override
	public String getMessage() {
		return "userId: " + userId + " is already in use.";
	}

}
