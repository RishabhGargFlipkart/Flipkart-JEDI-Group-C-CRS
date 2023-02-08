package com.flipkart.constant;

/**
 * Class containing queries relating to student
 */
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

    public static final String ADD_STUDENT="insert into student (studentId,batch,branchName,loginApproved,gradeCardApproved,regApproved,gender,address,isPaid) values (?,?,?,?,?,?,?,?,?)";
    public static final String VERIFY_CREDENTIALS="select password from user where userId = ?";
    public static final String GET_ROLE="select role from user where userId = ? ";
    public static final String IS_APPROVED="select loginApproved from student where studentId = ? ";
    public static final String GET_STUDENT_ID="select studentId from student where studentId = ? ";
    public static final String UPDATE_PASSWORD="update user set password=? where userId = ? ";
    public static final String GET_PROF_NAME = "select name from user where userId = ?";

    //Registration Queries
    public static final String ADD_COURSE="insert into semregistration (studentId,courseCode, grade, semester) values ( ? , ?, ?, ? )";
    public static final String DECREMENT_COURSE_SEATS="update course set seats = seats-1 where courseCode = ? ";
    public static final String NUMBER_OF_REGISTERED_COURSES=" select studentId from semregistration where studentId = ? ";
    public static final String GET_SEATS = "select seats from course where courseCode = ?;";
    public static final String IS_REGISTERED=" select courseCode from semregistration where courseCode=? and studentId=? ";
    public static final String DROP_COURSE_QUERY = "delete from semregistration where courseCode = ? AND studentId = ?;";
    public static final String INCREMENT_SEAT_QUERY  = "update course set seats = seats + 1 where  courseCode = ?;";
    public static final String CALCULATE_FEES  = "select sum(fee) from course where courseCode in (select courseCode from semregistration where studentId = ?);";
    public static final String VIEW_GRADE = "select course.courseCode,course.courseName,semregistration.grade from course inner join semregistration on course.courseCode = semregistration.courseCode where semregistration.studentId = ?;";
    public static final String VIEW_AVAILABLE_COURSES="select * from course";
    public static final String VIEW_REGISTERED_COURSES=" select course.courseCode,course.courseName,course.profId,course.seats,course.fee from course,semregistration where semregistration.studentId=? and semregistration.courseCode=course.courseCode";
    public static final String GET_REGISTRATION_STATUS=" select regApproved from student where studentId = ? ";
    public static final String SET_REGISTRATION_STATUS="update student set regApproved = true  where studentId=?";
    public static final String INSERT_NOTIFICATION = "insert into notification (notificationId,message,refId) values (?,?,?)";
    public static final String INSERT_CARD="insert into card (refId,cardNo,cardType,cvv) values (?,?,?,?)";
    public static final String INSERT_PAYMENT = "insert into payment (refId,studentId,amount,typeOfPayment,bank) values (?,?,?,?,?)";
    public static final String INSERT_NET_BANKING="insert into netbanking (refId,accountNo,IFSC) values (?,?,?)";
    public static final String INSERT_CHEQUE = "insert into cheque (refId,chequeNo) values (?,?)";
    public static String INSERT_UPI = "insert into upi (refId,upiId,serviceProvider) values (? ,? ,?)";

    public static final String UPDATE_ISPAID="update student set isPaid=true where studentId=?";

    public static final String GET_STUDENT="select * from student where studentId=?";
}


  
