package com.flipkart.exception;
import com.flipkart.constant.ColourConstant;

/**
 * @author JEDI-Group-C Praneet, Rishabh, Akhil, Manan, Nidhi, Shivanshu, Divyansh
 * Exception to check if student is not registered
 *
 */
public class StudentNotRegisteredException extends Exception{
	 private String studentName;

	/**
	 * Constructor to set studentName
	 * @param studentName
	 */
	 public StudentNotRegisteredException(String studentName)
	 {
		 this.studentName=studentName;
	 }
	 
	 /**
	  * getter function for student Name
	  * @return student name
	  */
	 public String getStudentName()
	 {
		 return studentName;
	 }
	 
}
