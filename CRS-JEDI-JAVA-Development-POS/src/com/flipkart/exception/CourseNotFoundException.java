package com.flipkart.exception;
import com.flipkart.constant.ColourConstant;


/**
 * Exception to check if course is available in catalog
 * @author JEDI-03
 *
 */
public class CourseNotFoundException extends Exception{
	private String courseCode;
	
	public CourseNotFoundException(String courseCode)
	{	
		this.courseCode = courseCode;
	}

	/**
	 * Getter function for course code
	 * @return
	 */
	public String getCourseCode()
	{
		return courseCode;
	}
	

	/**
	 * Message returned when exception is thrown
	 */
	@Override
	public String getMessage() 
	{
		return ColourConstant.ANSI_YELLOW + "Course with courseCode: " + courseCode + " not found." + ColourConstant.ANSI_RESET;
	}
}
