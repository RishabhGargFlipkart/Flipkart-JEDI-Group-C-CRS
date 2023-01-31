package com.flipkart.service;

public interface StudentService {
    public int register(String name, String userId, String password, String branch, int batch);
    public int getStudentId(String userId);
    public boolean isApproved(int studentId);
}
