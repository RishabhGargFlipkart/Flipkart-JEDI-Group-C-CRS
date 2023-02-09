/**
 * 
 */
package com.flipkart.validator;

import java.util.List;

import com.flipkart.bean.Course;
import com.flipkart.bean.Student;

/**
 *
 * @author JEDI-Group-C Praneet, Rishabh, Akhil, Manan, Nidhi, Shivanshu, Divyansh
 * Class for Admin Validator 
 * 
 */
public class AdminValidator {


	/**
	 * Method to check if it is a valid course
	 * @param newCourse
	 * @param courseList
	 * @return
	 */
	public static boolean isValidNewCourse(Course newCourse, List<Course> courseList) {
		for(Course course : courseList) {
			if(newCourse.getCourseCode().equalsIgnoreCase(course.getCourseCode())) {
				return false; 
			}
		}
		return true;
	}

	/**
	 * Method to check if course is valid
	 * @param dropCourseCode
	 * @param courseList
	 * @return
	 */
	public static boolean isValidDropCourse(String dropCourseCode, List<Course> courseList) {
		for(Course course : courseList) {
			if(dropCourseCode.equalsIgnoreCase(course.getCourseCode())) {
				return true; 
			}
		}
		return false;
	}

	/**
	 * Method to check if student is unapproved
	 * @param studentId
	 * @param studentList
	 * @return
	 */
	public static boolean isValidUnapprovedStudent(String studentId, List<Student> studentList) {
		for(Student student : studentList) {
			if(studentId.equals(student.getUserId())) {
				return true;
			}
		}
		return false;
	}
}