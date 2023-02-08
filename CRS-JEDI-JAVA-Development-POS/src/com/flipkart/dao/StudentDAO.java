package com.flipkart.dao;

import com.flipkart.bean.Student;
import com.flipkart.exception.StudentNotRegisteredException;

/**
 * @author JEDI-Group-C Praneet, Rishabh, Akhil, Manan, Nidhi, Shivanshu, Divyansh
 * Interface for Student Dao Operations
 *
 */
public interface StudentDAO {
    /**
     * Add student for login approval
     * @param student
     * @throws StudentNotRegisteredException
     */
    public void addStudent(Student student) throws StudentNotRegisteredException;

    /**
     * @param userId
     * @return
     */
    public int getStudentId(String userId);

    /**
     * returns the login approval status
     * @param studentId
     * @return
     */
    public boolean isApproved(int studentId);

    /**
     * returns the payment status of student
     * @param studentId
     * @return
     */
    public boolean checkIsPaid(String studentId);

    /**
     * return the grade card approval status
     * @param studentId
     * @return
     */
    public boolean checkIsGradeCard(String studentId);
}