/**
 * 
 */
package com.flipkart.exception;
import com.flipkart.constant.ColourConstant;

/**
 * Exception to check if course is already present in catalog
 * @author JEDI-03
 *
 */
public class CourseFoundException extends Exception{
	private String courseCode;
	
	/***
	 * Constructor
	 * @param courseCode
	 */
	public CourseFoundException(String courseCode) {
		this.courseCode = courseCode;
	}
	

	/**
	 * Getter method
	 * @return course code
	 */
	public String getCourseCode() {
		return courseCode;
	}

	/**
	 * Message returned when exception is thrown
	 */
	@Override
	public String getMessage() {
		return ColourConstant.ANSI_YELLOW + "Course with courseCode: " + courseCode + " already present in catalog." + ColourConstant.ANSI_RESET;
	}
}
