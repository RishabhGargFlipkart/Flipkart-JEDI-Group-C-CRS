package com.flipkart.service;

public interface StudentService {
    public void register(String name, String userId, String password, String gender, int batch, String branch, String address);
    public int getStudentId(String userId);
    public boolean isApproved(int studentId);
}
