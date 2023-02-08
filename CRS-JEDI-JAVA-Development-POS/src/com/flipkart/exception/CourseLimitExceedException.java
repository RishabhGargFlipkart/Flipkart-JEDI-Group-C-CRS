/**
 * 
 */
package com.flipkart.exception;
import com.flipkart.constant.ColourConstant;

/**
 * Exception to check if the maximum number of registered courses is exceeded
 * @author JEDI-03
 *
 */
public class CourseLimitExceedException extends Exception{
	
	private int num;

	/**
	 * Constructor
	 * @param num number of courses
 	 */
	public CourseLimitExceedException(int num )
	{	
		this.num = num;
	}


	/**
	 * Message returned when exception is thrown
	 */
	@Override
	public String getMessage() 
	{
		return ColourConstant.ANSI_YELLOW + "You have already registered for " + num + " courses" + ColourConstant.ANSI_RESET;
	}


}
