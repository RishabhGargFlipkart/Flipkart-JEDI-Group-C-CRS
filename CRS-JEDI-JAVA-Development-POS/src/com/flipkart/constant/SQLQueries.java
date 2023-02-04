package com.flipkart.constant;

public class SQLQueries {
    //AdminDao Queries
	public static final String DELETE_COURSE_QUERY = "delete from Course where courseCode = ?";
	public static final String ADD_COURSE_QUERY = "insert into Course(courseCode, courseName, instructorId, seats) values (?, ?, ?, ?)";
	public static final String VIEW_PENDING_ADMISSION_QUERY = "select userId, name, password, role, gender, address from student natural join user where loginApproved = 0";
	public static final String VIEW_PENDING_REGISTRATION_QUERY = "select userId, name, password, role, gender, address from student natural join user where regApproved = 0";
	public static final String VIEW_PENDING_GRADECARD_QUERY = "select userId, name, password, role, gender, address from student natural join user where gradeCardApproved = 0";
	public static final String APPROVE_STUDENT_QUERY = "update Student set loginApproved = 1 where studentId = ?";
	public static final String APPROVE_REGISTRATION_QUERY = "update Student set regApproved = 1 where studentId = ?";
	public static final String APPROVE_GRADECARD_QUERY = "update Student set gradeCardApproved = 1 where studentId = ?";

	public static final String ADD_USER_QUERY = "insert into User(userId, name, password, role, gender, address, country) values (?, ?, ?, ?, ?, ?, ?)";
	public static final String ADD_PROFESSOR_IN_ADMIN_QUERY = "insert into User(userId, name, role, password) values (?, ?, ?, ?)";
	public static final String ADD_PROFESSOR_IN_PROFESSOR_QUERY = "insert into Professor(profId, department) values (?, ?)";


	public static final String ASSIGN_COURSE_QUERY = "update Course set professorId = ? where courseCode = ?";
	public static final String VIEW_COURSE_QUERY = "select * from Course";
	public static final String VIEW_PROFESSOR_QUERY = "select profId, name, department, role from Professor natural join User";

	public static final String ADD_STUDENT="insert into student (userId,branchName,batch,isApproved) values (?,?,?,?)";
	public static final String VERIFY_CREDENTIALS="select password from user where userId = ?";
	public static final String GET_ROLE="select role from user where userId = ? ";
	public static final String IS_APPROVED="select isApproved from student where studentId = ? ";
	public static final String GET_STUDENT_ID="select studentId from student where userId = ? ";
	public static final String UPDATE_PASSWORD="update user set password=? where userId = ? ";
	public static final String GET_PROF_NAME = "select name from user where userId = ?";
}
