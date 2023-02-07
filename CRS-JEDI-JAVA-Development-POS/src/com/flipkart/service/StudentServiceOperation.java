package com.flipkart.service;

import com.flipkart.bean.Student;
import com.flipkart.dao.StudentDAO;
import com.flipkart.dao.StudentDAOImpl;

import com.flipkart.exception.StudentNotRegisteredException;

public class StudentServiceOperation implements StudentService {

    private static volatile StudentServiceOperation instance = null;
    StudentDAO studentDaoInterface = StudentDAOImpl.getInstance();

    private StudentServiceOperation() {

    }

    public static StudentServiceOperation getInstance() {
        if (instance == null) {
            // This is a synchronized block, when multiple threads will access this instance
            synchronized (StudentServiceOperation.class) {
                instance = new StudentServiceOperation();
            }
        }
        return instance;
    }

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
    @Override
    public void register(String name, String userId, String password, String gender, int batch, String branch, String address) throws StudentNotRegisteredException {
        try {
            Student newStudent = new Student();
            newStudent.setUserId(userId);
            newStudent.setUserId(userId);
            newStudent.setName(name);
            newStudent.setGradeCardApproved(false);
            newStudent.setRegistrationApproved(false);
            newStudent.setBatch(batch);
            newStudent.setBranchName(branch);
            newStudent.setPassword(password);
            newStudent.setRole("Student");
            newStudent.setGender(gender);
            newStudent.setAddress(address);
            studentDaoInterface.addStudent(newStudent);

        }
        catch(StudentNotRegisteredException ex)
        {
            throw ex;
        }
    }

    /**
     * @param userId
     * @return
     */
    @Override
    public int getStudentId(String userId) {
        return studentDaoInterface.getStudentId(userId);
    }

    /**
     * @param studentId
     * @return
     */
    @Override
    public boolean isApproved(int studentId) {
        return studentDaoInterface.isApproved(studentId);
    }
}
