package com.flipkart.exception;
import com.flipkart.constant.ColourConstant;

/**
 * @author JEDI-Group-C Praneet, Rishabh, Akhil, Manan, Nidhi, Shivanshu, Divyansh
 * Exception to check if the professor is not added successfully by admin
 *
 */
public class ProfessorNotAddedException extends Exception{
	private String professorId;

	/**
	 * Constructor
	 * @param professorId
	 */
	public ProfessorNotAddedException(String professorId) {
		this.professorId = professorId;
	}
	
	/**
	 * Getter function for professorId
	 * @return
	 */
	public String getUserId() {
		return this.professorId;
	}
	

	/**
	 * Message returned when exception is thrown
	 */
	@Override
	public String getMessage() {
		return ColourConstant.ANSI_YELLOW + "professorId: " + professorId + " not added!";
	}
}
