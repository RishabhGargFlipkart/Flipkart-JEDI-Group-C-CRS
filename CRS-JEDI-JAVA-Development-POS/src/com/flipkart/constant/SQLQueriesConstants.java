package com.flipkart.constant;

/**
 * Class containing queries relating to student
 */
public class SQLQueriesConstants {
    public static final String ADD_USER_QUERY = "insert into User(userid, name, role, password) values (?, ?, ?, ?)";
    public static final String ADD_STUDENT="insert into student (studentId,batch,branchName,loginApproved,gradeCardApproved,regApproved,gender,address,isPaid) values (?,?,?,?,?,?,?,?,?)";
    public static final String IS_APPROVED="select loginApproved from student where studentId = ? ";
    public static final String GET_STUDENT_ID="select studentId from student where studentId = ? ";
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
    public static final String INSERT_CARD="insert into card (refId,cardNo,cardType,cvv,date) values (?,?,?,?,?)";
    public static final String INSERT_PAYMENT = "insert into payment (refId,studentId,amount,typeOfPayment,bank) values (?,?,?,?,?)";
    public static final String INSERT_NET_BANKING="insert into netbanking (refId,accountNo,IFSC) values (?,?,?)";
    public static final String INSERT_CHEQUE = "insert into cheque (refId,chequeNo) values (?,?)";
    public static String INSERT_UPI = "insert into upi (refId,upiId,serviceProvider) values (? ,? ,?)";

    public static final String UPDATE_ISPAID="update student set isPaid=true where studentId=?";

    public static final String GET_STUDENT="select * from student where studentId=?";
}


  
