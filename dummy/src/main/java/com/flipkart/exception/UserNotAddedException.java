package com.flipkart.exception;
import com.flipkart.constant.ColourConstant;
/**
 * @author JEDI-Group-C Praneet, Rishabh, Akhil, Manan, Nidhi, Shivanshu, Divyansh
 * Exception to check if user cannot be added
 *
 */
public class UserNotAddedException extends Exception{
	private String userId;

	/**
	 * Constructor to set userId
	 * @param userId
	 */
	public UserNotAddedException(String userId) {
		this.userId = userId;
	}
	
	/**
	 * Getter function for UserId
	 * @return
	 */
	public String getUserId() {
		return this.userId;
	}
	

	/**
	 * Message returned when exception is thrown
	 */
	@Override
	public String getMessage() {
		return ColourConstant.ANSI_YELLOW + "UserId: " + userId + " is already in use!" + ColourConstant.ANSI_RESET;
	}
}
