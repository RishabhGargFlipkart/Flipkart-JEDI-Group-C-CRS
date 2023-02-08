/**
 * 
 */
package com.flipkart.exception;
import com.flipkart.constant.ColourConstant;

/**
 * Exception to check if user exists 
 * @author JEDI-03
 *
 */
public class UserNotFoundException extends Exception {

	private String userId;

	/***
	 * Getter function for UserId
	 * @param userId
	 */
	public UserNotFoundException(String userId) {
		this.userId = userId;
	}

	/**
	 * Message thrown by exception
	 */
	@Override
	public String getMessage() {
		return ColourConstant.ANSI_YELLOW + "User with userId: " + userId + " not found." + ColourConstant.ANSI_RESET;
	}

}
