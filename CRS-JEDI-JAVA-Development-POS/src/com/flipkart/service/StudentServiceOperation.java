package com.flipkart.service;

import com.flipkart.bean.Student;
import com.flipkart.dao.StudentDAO;
import com.flipkart.dao.StudentDAOImpl;

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

    @Override
    public void register(String name, String userId, String password, String gender, int batch, String branch, String address) {
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

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public int getStudentId(String userId) {
        return studentDaoInterface.getStudentId(userId);
    }

    @Override
    public boolean isApproved(int studentId) {
        return studentDaoInterface.isApproved(studentId);
    }
}
