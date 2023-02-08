/**
 * 
 */
package com.flipkart.exception;
import com.flipkart.constant.ColourConstant;

/**
 * Exception thrown when student is not found for approval
 * @author JEDI-03
 *
 */
public class StudentNotFoundForApprovalException extends Exception {
	private String studentId;
	
	public StudentNotFoundForApprovalException(String studentId) {
		this.studentId = studentId;
	}
	
	/**
	 * Getter function for studentId
	 * @return
	 */
	public String getStudentId() {
		return this.studentId;
	}
	

	/**
	 * Message returned when exception is thrown
	 */
	@Override
	public String getMessage() {
		return ColourConstant.ANSI_YELLOW + "student with Id: " + studentId + " not found for approval!"  + ColourConstant.ANSI_RESET;
	}
}
