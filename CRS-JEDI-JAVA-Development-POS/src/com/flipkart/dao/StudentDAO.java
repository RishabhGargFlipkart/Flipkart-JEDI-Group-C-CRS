package com.flipkart.dao;

import com.flipkart.bean.Student;
import com.flipkart.exception.StudentNotRegisteredException;
public interface StudentDAO {
    public void addStudent(Student student) throws StudentNotRegisteredException;
    public int getStudentId(String userId);
    public boolean isApproved(int studentId);
    public boolean checkIsPaid(String studentId);
}