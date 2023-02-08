/**
 * 
 */
package com.flipkart.exception;
import com.flipkart.constant.ColourConstant;

/**
 * Exception course is deleted from catalog
 * @author JEDI-03
 *
 */
public class CourseNotDeletedException extends Exception{
private String courseCode;
	
	public CourseNotDeletedException(String courseCode)
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
	 * Message thrown by exception
	 */
	@Override
	public String getMessage() 
	{
		return ColourConstant.ANSI_YELLOW + "Course with courseCode: " + courseCode + " can not be deleted." + ColourConstant.ANSI_RESET;
	}
}
