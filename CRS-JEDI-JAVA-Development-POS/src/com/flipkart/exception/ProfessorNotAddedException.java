package com.flipkart.exception;
import com.flipkart.constant.ColourConstant;

/**
 * Exception to check if the professor is not added successfully by admin
 * @author JEDI-03
 *
 */
public class ProfessorNotAddedException extends Exception{
	private String professorId;
	
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
