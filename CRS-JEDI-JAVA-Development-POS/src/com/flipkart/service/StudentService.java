package com.flipkart.service;
import com.flipkart.exception.StudentNotRegisteredException;

public interface StudentService {
    /**
     * @param name
     * @param userId
     * @param password
     * @param gender
     * @param batch
     * @param branch
     * @param address
     * @throws StudentNotRegisteredException
     */
    public void register(String name, String userId, String password, String gender, int batch, String branch, String address) throws StudentNotRegisteredException;

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
}
