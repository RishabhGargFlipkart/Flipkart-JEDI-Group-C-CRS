package com.flipkart.service;
import com.flipkart.exception.StudentNotRegisteredException;

public interface StudentService {
    public void register(String name, String userId, String password, String gender, int batch, String branch, String address) throws StudentNotRegisteredException;
    public int getStudentId(String userId);
    public boolean isApproved(int studentId);
}
