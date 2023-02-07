package com.flipkart.dao;

import com.flipkart.bean.Student;
import com.flipkart.exception.StudentNotRegisteredException;
public interface StudentDAO {
    /**
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
     * @param studentId
     * @return
     */
    public boolean isApproved(int studentId);

    /**
     * @param studentId
     * @return
     */
    public boolean checkIsPaid(String studentId);
}