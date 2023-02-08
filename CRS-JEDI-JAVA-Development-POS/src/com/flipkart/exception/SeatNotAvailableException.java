package com.flipkart.exception;
import com.flipkart.constant.ColourConstant;

/**
 * Exception to check if seats are available for course registration
 * @author JEDI-03
 *
 */
public class SeatNotAvailableException extends Exception{
	
	private String courseCode;

	/**
	 * Constructor
	 * @param courseCode
	 */
	public SeatNotAvailableException(String courseCode)
	{	
		this.courseCode = courseCode;
	}


	/**
	 * Message returned when exception is thrown
	 */
	@Override
	public String getMessage() {
		return  ColourConstant.ANSI_YELLOW + "Seats are not available in : " + courseCode + ColourConstant.ANSI_RESET;
	}


}
