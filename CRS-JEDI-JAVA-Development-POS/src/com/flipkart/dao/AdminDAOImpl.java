package com.flipkart.dao;

import com.flipkart.bean.Course;
import com.flipkart.bean.Professor;
import com.flipkart.bean.Student;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.flipkart.exception.CourseNotFoundException;
import com.flipkart.utils.DBUtils;
import com.flipkart.constant.SQLQueriesAdmin;
import java.sql.SQLException;

 import com.flipkart.exception.CourseFoundException;
 import com.flipkart.exception.CourseNotDeletedException;
import com.flipkart.exception.ProfessorNotAddedException;
 import com.flipkart.exception.StudentNotFoundForApprovalException;
 import com.flipkart.exception.UserIdAlreadyInUseException;

import java.util.ArrayList;
import java.util.List;

public class AdminDAOImpl implements AdminDAO{

    private static volatile AdminDAOImpl instance = null;
    private PreparedStatement statement = null;
    Connection connection = DBUtils.getConnection();

    private AdminDAOImpl(){}

    public static AdminDAOImpl getInstance()
	{
		if(instance == null)
		{
			synchronized(AdminDAOImpl.class){
				instance = new AdminDAOImpl();
			}
		}
		return instance;
	}

    @Override
    public void deleteCourse(String courseCode) throws CourseNotFoundException, CourseNotDeletedException{
        statement = null;
        try {
            String sql = SQLQueriesAdmin.DELETE_COURSE_QUERY;
            statement = connection.prepareStatement(sql);

            statement.setString(1,courseCode);
            int row = statement.executeUpdate();

            System.out.println(row + " entries deleted.");
            if(row == 0) {
                System.out.println(courseCode + " not in catalog!");
                throw new CourseNotFoundException(courseCode);
            }

            System.out.println("Course with courseCode: " + courseCode + " deleted.");

        }catch(SQLException se) {

            System.out.println(se.getMessage());
            throw new CourseNotDeletedException(courseCode);
        }
    }

    @Override
    public void addCourse(Course course) throws CourseFoundException{
        statement = null;
        try{
            String sql = SQLQueriesAdmin.ADD_COURSE_QUERY;
            statement = connection.prepareStatement(sql);
            course.setSeats(10);

            statement.setString(1, course.getCourseCode());
            statement.setString(2, course.getCourseName());
            statement.setString(3, course.getInstructorId());
            statement.setInt(4, course.getSeats());
            statement.setDouble(5, course.getFee());

            int row = statement.executeUpdate();
            System.out.println("Course added to catalog!");
            if(row == 0){
                System.out.println("Course with courseCode: " + course.getCourseCode() + "not added to catalog.");
                throw new CourseFoundException(course.getCourseCode());
            }
        }catch(SQLException se) {
			System.out.println(se.getMessage());
			throw new CourseFoundException(course.getCourseCode());
		}
    }

    @Override
    public void approveGradeCard(String studentID) throws StudentNotFoundForApprovalException {
        statement = null;
		try {
			String sql = SQLQueriesAdmin.APPROVE_GRADECARD_QUERY;
			statement = connection.prepareStatement(sql);

			statement.setString(1,studentID);
			int row = statement.executeUpdate();

			System.out.println(row + " student approved.");
			if(row == 0) {
				System.out.println("Student with studentId: " + studentID + " not found.");
				throw new StudentNotFoundForApprovalException(studentID);
			}
			System.out.println("Report card generated for Student with studentId: " + studentID);

		}catch(SQLException se) {
			System.out.println(se.getMessage());
		}
    }

    @Override
    public void approveRegistration(String studentID) throws StudentNotFoundForApprovalException {
        statement = null;
		try {
			String sql = SQLQueriesAdmin.APPROVE_REGISTRATION_QUERY;
			statement = connection.prepareStatement(sql);

			statement.setString(1,studentID);
			int row = statement.executeUpdate();

			System.out.println(row + " student approved.");
			if(row == 0) {
				System.out.println("Student with studentId: " + studentID + " not found.");
				throw new StudentNotFoundForApprovalException(studentID);
			}
			System.out.println("Student with studentId: " + studentID + " approved for coursse registration by admin.");

		}catch(SQLException se) {
			System.out.println(se.getMessage());
		}
    }

    @Override
    public void approveStudent(String studentID) throws StudentNotFoundForApprovalException {
        statement = null;
		try {
			String sql = SQLQueriesAdmin.APPROVE_STUDENT_QUERY;
			statement = connection.prepareStatement(sql);

			statement.setString(1,studentID);
			int row = statement.executeUpdate();

			System.out.println(row + " student approved.");
			if(row == 0) {
				System.out.println("Student with studentId: " + studentID + " not found.");
				throw new StudentNotFoundForApprovalException(studentID);
			}
			System.out.println("Student with studentId: " + studentID + " approved by admin.");

		}catch(SQLException se) {
			System.out.println(se.getMessage());
		}
    }

    @Override
    public void addProfessor(Professor professor) throws UserIdAlreadyInUseException, ProfessorNotAddedException{
        statement = null;
		try {
			String sql = SQLQueriesAdmin.ADD_PROFESSOR_IN_ADMIN_QUERY;
			statement = connection.prepareStatement(sql);

            professor.setRole("professor");
			statement.setString(1, professor.getUserId());
			statement.setString(2, professor.getName());
            statement.setString(3, professor.getRole());
            statement.setString(4, professor.getPassword());
			int row = statement.executeUpdate();
			System.out.println("User added.");

            sql = SQLQueriesAdmin.ADD_PROFESSOR_IN_PROFESSOR_QUERY;
			statement = connection.prepareStatement(sql);

            statement.setString(1, professor.getUserId());
            statement.setString(2, professor.getDepartment());
            row = statement.executeUpdate();
            System.out.println("Professor added.");
            if(row == 0) {
				System.out.println("Professor with professorId: " + professor.getUserId() + " not added.");
				throw new ProfessorNotAddedException(professor.getUserId());
			}
		}catch(SQLException se) {
			System.out.println(se.getMessage());
			throw new UserIdAlreadyInUseException(professor.getUserId());
		}
    }

    @Override
    public List<Course> viewCourses() {
        statement = null;
        List<Course> courseList = new ArrayList<>();
        try {

            String sql = SQLQueriesAdmin.VIEW_COURSE_QUERY;
            statement = connection.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();

            while(resultSet.next()) {

                Course course = new Course();
                course.setCourseCode(resultSet.getString(1));
                course.setCourseName(resultSet.getString(2));
                course.setInstructorId(resultSet.getString(3));
                course.setSeats(resultSet.getInt(4));
                course.setFee(resultSet.getDouble(5));
                courseList.add(course);
            }

        }catch(SQLException se) {

            System.out.println(se.getMessage());

        }

        return courseList;
    }

    @Override
    public List<Professor> viewProfessors() {
        statement = null;
        List<Professor> professorList = new ArrayList<>();
        try {

            String sql = SQLQueriesAdmin.VIEW_PROFESSOR_QUERY;
            statement = connection.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();

            while(resultSet.next()) {

                Professor professor = new Professor();
                professor.setUserId(resultSet.getString(1));
                professor.setName(resultSet.getString(2));
                professor.setDepartment(resultSet.getString(3));
                professor.setRole(resultSet.getString(4));
                professor.setPassword("*********");
                professorList.add(professor);

            }
            System.out.println("List of Professors: ");
            for(Professor p:professorList)
            {
                System.out.println("Professor ID: "+p.getUserId()+"  Professor Name: "+p.getName());
            }

        }catch(SQLException se) {

            System.out.println(se.getMessage());

        }
        return professorList;
    }

    @Override
    public List<Student> viewPendingAdmission() {
        statement = null;
        List<Student> userList = new ArrayList<Student>();
        try {

            String sql = SQLQueriesAdmin.VIEW_PENDING_ADMISSION_QUERY;
            statement = connection.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();

            while(resultSet.next()) {

                Student user = new Student();
                user.setUserId(resultSet.getString(1));
                user.setName(resultSet.getString(2));
                user.setPassword(resultSet.getString(3));
                user.setRole(resultSet.getString(4));
                user.setGender(resultSet.getString(5));
                user.setAddress(resultSet.getString(6));
                userList.add(user);
            }

            System.out.println(userList.size() + " students have pending-approval");

        }catch(SQLException se) {

            System.out.println(se.getMessage());

        }

        return userList;
    }

    @Override
    public List<Student> viewCompletedAdmission() {
        statement = null;
        List<Student> userList = new ArrayList<Student>();
        try {

            String sql = SQLQueriesAdmin.VIEW_COMPLETED_ADMISSION_QUERY;
            statement = connection.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();

            while(resultSet.next()) {

                Student user = new Student();
                user.setUserId(resultSet.getString(1));
                user.setName(resultSet.getString(2));
                user.setPassword(resultSet.getString(3));
                user.setRole(resultSet.getString(4));
                user.setGender(resultSet.getString(5));
                user.setAddress(resultSet.getString(6));
                userList.add(user);
            }

            System.out.println(userList.size() + " students have pending-approval");

        }catch(SQLException se) {

            System.out.println(se.getMessage());

        }

        return userList;
    }

    @Override
    public List<Student> viewPendingRegistration() {
        statement = null;
        List<Student> userList = new ArrayList<Student>();
        try {

            String sql = SQLQueriesAdmin.VIEW_PENDING_REGISTRATION_QUERY;
            statement = connection.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();

            while(resultSet.next()) {

                Student user = new Student();
                user.setUserId(resultSet.getString(1));
                user.setName(resultSet.getString(2));
                user.setPassword(resultSet.getString(3));
                user.setRole(resultSet.getString(4));
                user.setGender(resultSet.getString(5));
                user.setAddress(resultSet.getString(6));
                userList.add(user);
            }

            System.out.println(userList.size() + " students have pending course package approval.");

        }catch(SQLException se) {

            System.out.println(se.getMessage());

        }

        return userList;
    }

    @Override
    public List<Student> viewPendingGradeCard() {
        statement = null;
        List<Student> userList = new ArrayList<Student>();
        try {

            String sql = SQLQueriesAdmin.VIEW_PENDING_GRADECARD_QUERY;
            statement = connection.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();

            while(resultSet.next()) {

                Student user = new Student();
                user.setUserId(resultSet.getString(1));
                user.setName(resultSet.getString(2));
                user.setPassword(resultSet.getString(3));
                user.setRole(resultSet.getString(4));
                user.setGender(resultSet.getString(5));
                user.setAddress(resultSet.getString(6));
                userList.add(user);
            }

            System.out.println(userList.size() + " students have pending grade cards.");

        }catch(SQLException se) {
            System.out.println(se.getMessage());
        }

        return userList;
    }

}
