package com.flipkart.constant;


/**
 * Class containing queries related to Professor
 */
    //Professor Queries
public class SQLQueriesProfessor {

    // Student Queries
    public static final String ADD_GRADE="update semregistration set grade=? where courseCode=? and studentId=?";

    public static final String GET_ASSIGNED_GRADE="select * from semregistration where courseCode=? and studentId=?";
    public static final String ASSIGN_COURSE="update course set profId=? where courseCode=?";
    public static final String GET_PROFS="select * from professor INNER JOIN user ON professor.profId=user.userId where professor.profId=? and user.password=? " ;
    public static final String GET_COURSES="select * from course where profId=?";
    public static final String GET_ASSIGNED_PROF="select * from course where courseCode=?";
    public static final String GET_ENROLLED_STUDENTS="select course.courseCode,course.courseName,semregistration.studentId from course inner join semregistration on course.courseCode = semregistration.courseCode where course.profId = ? and course.courseCode =? order by course.courseCode";

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
    public static final String INSERT_NOTIFICATION = "insert into notification(message,refId) values (?,?)";
    public static final String INSERT_CARD="insert into card (refId,cardNo,cardType,cvv) values (?,?,?,?)";
    public static final String INSERT_PAYMENT = "insert into payment (studentId,amount,typeOfPayment,bank) values (?,?,?,?)";
}
