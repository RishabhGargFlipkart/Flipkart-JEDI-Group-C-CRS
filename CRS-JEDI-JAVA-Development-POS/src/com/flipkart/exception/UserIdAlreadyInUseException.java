/**
 * 
 */
package com.flipkart.exception;
import com.flipkart.constant.ColourConstant;

/**
 * @author Rag_Patel
 *
 */
public class UserIdAlreadyInUseException extends Exception{
	private String userId;
	
	
	public UserIdAlreadyInUseException(String userId) {
		this.userId = userId;
	}
	
	public String getUserId() {
		return userId;
	}

	public void setProfessorId(String userId) {
		this.userId = userId;
	}
	
	@Override
	public String getMessage() {
		return ColourConstant.ANSI_YELLOW + "userId: " + userId + " is already in use." + ColourConstant.ANSI_RESET;
	}

}
