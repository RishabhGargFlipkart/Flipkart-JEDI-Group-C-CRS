package com.flipkart.bean;
/**
 * @author JEDI-Group-C Praneet, Rishabh, Akhil, Manan, Nidhi, Shivanshu, Divyansh
 * Class for Professor
 *
 */
public class Professor extends User {

	private String department;

	/**
	 * Method to get department name
	 * @return
	 */
	public String getDepartment() {
		return department;
	}

	/**
	 * Method to set department
	 * @param department
	 */
	public void setDepartment(String department) {
		this.department = department;
	}

	/**
	 *Method to display
	 */
	public void display()
	{
		System.out.println(this.getUserId()+" "+this.getName()+" "+this.department);
	}

	/**
	 * Method to check professor id
	 * @param professor
	 * @return
	 */
	@Override
	public boolean equals(Object professor){
		if(this==professor) return true;
		if(!(professor instanceof Professor)) return false;
		return this.getUserId().equals(((Professor)professor).getUserId());
	}

}
