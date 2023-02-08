package com.flipkart.exception;
import com.flipkart.constant.ColourConstant;
/**
 * Exception to check if user cannot be added
 * @author JEDI-03
 *
 */
public class UserNotAddedException extends Exception{
	private String userId;
	
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
