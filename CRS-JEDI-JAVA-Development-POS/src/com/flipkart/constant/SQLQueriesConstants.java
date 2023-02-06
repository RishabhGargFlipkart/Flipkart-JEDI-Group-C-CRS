package com.flipkart.constant;

public class SQLQueriesConstants {


    // Student Queries

    public static final String ADD_GRADE="update semregistration set grade=? where courseCode=? and studentId=?";
    public static final String ASSIGN_COURSE="update course set profId=? where courseCode=?";
    public static final String GET_PROFS="select * from professor INNER JOIN user ON professor.profId=user.userId where professor.profId=? and user.password=? " ;
    public static final String GET_COURSES="select * from course where profId=?";
    public static final String GET_ENROLLED_STUDENTS="select course.courseCode,course.courseName,semregistration.studentId from course inner join semregistration on course.courseCode = semregistration.courseCode where course.profId = ? and course.courseCode =? order by course.courseCode";

}
