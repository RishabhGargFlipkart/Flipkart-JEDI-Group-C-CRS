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
	@Override
	public boolean equals(Object professor){
		if(this==professor) return true;
		if(!(professor instanceof Professor)) return false;
		return this.getUserId().equals(((Professor)professor).getUserId());
	}

}
