/**
 * 
 */
package com.flipkart.bean;

/**
 * @author rishabh.garg
 *
 */
public class Professor extends User {

	private String department;
	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public void display()
	{
		System.out.println(this.getUserId()+" "+this.getName()+" "+this.department);
	}


}
