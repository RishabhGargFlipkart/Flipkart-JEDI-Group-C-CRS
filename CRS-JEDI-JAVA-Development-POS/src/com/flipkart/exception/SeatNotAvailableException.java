package com.flipkart.exception;
import com.flipkart.constant.ColourConstant;



/**
 * @author JEDI-Group-C Praneet, Rishabh, Akhil, Manan, Nidhi, Shivanshu, Divyansh
 * Exception to check if seats are available for course registration
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
