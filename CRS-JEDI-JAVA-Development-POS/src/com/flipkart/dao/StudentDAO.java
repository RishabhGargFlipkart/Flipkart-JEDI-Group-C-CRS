package com.flipkart.dao;

import com.flipkart.bean.Student;
public interface StudentDAO {
    public void addStudent(Student student);
    public int getStudentId(String userId);
    public boolean isApproved(int studentId);
    public boolean checkIsPaid(String studentId);
}