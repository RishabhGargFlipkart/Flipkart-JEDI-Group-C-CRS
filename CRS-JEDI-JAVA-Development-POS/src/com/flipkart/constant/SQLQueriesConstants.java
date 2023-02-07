package com.flipkart.constant;

public class SQLQueriesConstants {

    public static final String DELETE_COURSE_QUERY = "delete from Course where courseCode = ?";
    public static final String ADD_COURSE_QUERY = "insert into Course(courseCode, courseName, catalogId) values (?, ?, ?)";
    public static final String VIEW_PENDING_ADMISSION_QUERY = "select userId, name, password, role, gender, address, country, studentId from student natural join user where isApproved = 0";
    public static final String APPROVE_STUDENT_QUERY = "update Student set isApproved = 1 where studentId = ?";
    public static final String ADD_USER_QUERY = "insert into User(userid, name, role, password) values (?, ?, ?, ?)";
    public static final String ADD_PROFESSOR_QUERY = "insert into Professor(userId, department, designation) values (?, ?, ?)";
    public static final String ASSIGN_COURSE_QUERY = "update Course set professorId = ? where courseCode = ?";
    public static final String VIEW_COURSE_QUERY = "select courseCode, courseName, professorId from Course where catalogId = ?";
    public static final String VIEW_PROFESSOR_QUERY = "select userId, name, gender, department, designation, address, country from Professor natural join User";

    public static final String ADD_STUDENT="insert into student (studentId,batch,branchName,loginApproved,gradeCardApproved,regApproved,gender,address) values (?,?,?,?,?,?,?,?)";
    public static final String VERIFY_CREDENTIALS="select password from user where userId = ?";
    public static final String GET_ROLE="select role from user where userId = ? ";
    public static final String IS_APPROVED="select loginApproved from student where studentId = ? ";
    public static final String GET_STUDENT_ID="select studentId from student where studentId = ? ";
    public static final String UPDATE_PASSWORD="update user set password=? where userId = ? ";
    public static final String GET_PROF_NAME = "select name from user where userId = ?";

    }
