package com.flipkart.dao;
import com.flipkart.bean.Course;
import com.flipkart.bean.StudentGrade;
import com.flipkart.constant.SQLQueriesConstants;
import com.flipkart.utils.DBUtils;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
public class RegistrationDAOImpl implements RegistrationDAO{
    private static volatile RegistrationDAOImpl instance=null;
    private PreparedStatement stmt = null;
    private RegistrationDAOImpl()
    {}


    public static RegistrationDAOImpl getInstance()
    {
        if(instance==null)
        {
            synchronized(RegistrationDAOImpl.class){
                instance=new RegistrationDAOImpl();
            }
        }
        return instance;
    }
    @Override
    public boolean addCourse(String courseCode, String studentId) {

        Connection conn = DBUtils.getConnection();
        try
        {
            stmt = conn.prepareStatement(SQLQueriesConstants.ADD_COURSE);
            stmt.setString(1, studentId);
            stmt.setString(2, courseCode);

            stmt.executeUpdate();

            stmt = conn.prepareStatement(SQLQueriesConstants.DECREMENT_COURSE_SEATS);
            stmt.setString(1, courseCode);
            stmt.executeUpdate();
            return true;
        }
        catch (SQLException e)
        {
            System.out.println(e.getMessage());
        }
//        finally
//        {
////            stmt.close();
////            conn.close();
//        }
        return false;
    }
    @Override
    public int numOfRegisteredCourses(String studentId) {

        Connection conn = DBUtils.getConnection();

        int count = 0;
        try {

            stmt = conn.prepareStatement(SQLQueriesConstants.NUMBER_OF_REGISTERED_COURSES);
            stmt.setString(1, studentId);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                count++;
            }
            return count;

        }
        catch (Exception e)
        {

            System.out.println(e.getMessage());
        }
//        finally
//        {
////            stmt.close();
////            conn.close();
//        }

        return count;
    }
    @Override
    public boolean seatAvailable(String courseCode) {

        Connection conn = DBUtils.getConnection();
        try
        {
            stmt = conn.prepareStatement(SQLQueriesConstants.GET_SEATS);
            stmt.setString(1, courseCode);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                return (rs.getInt("seats") > 0);
            }

        }
        catch (SQLException e) {
            e.printStackTrace();
        }
//        finally
//        {
////            stmt.close();
////            conn.close();
//        }

        return true;
    }
    @Override
    public boolean isRegistered(String courseCode, String studentId) {

        Connection conn = DBUtils.getConnection();

        boolean check = false;
        try
        {
            stmt = conn.prepareStatement(SQLQueriesConstants.IS_REGISTERED);
            stmt.setString(1, courseCode);
            stmt.setString(2, studentId);
            ResultSet rs = stmt.executeQuery();

            while(rs.next())
            {
                check = true;
            }
        }
        catch(Exception e)
        {
            System.out.println(e.getClass());
            System.out.println(e.getMessage());
        }
//        finally
//        {
//            stmt.close();
//            conn.close();
//        }

        return check;

    }
    @Override
    public boolean dropCourse(String courseCode, String studentId) {

        Connection conn = DBUtils.getConnection();


        try
        {
            stmt = conn.prepareStatement(SQLQueriesConstants.DROP_COURSE_QUERY);
            stmt.setString(1, courseCode);
            stmt.setString(2, studentId);
            stmt.execute();

            stmt = conn.prepareStatement(SQLQueriesConstants.INCREMENT_SEAT_QUERY);
            stmt.setString(1, courseCode);
            stmt.execute();

            stmt.close();

            return true;
        }
        catch(Exception e)
        {
            System.out.println("Exception found" + e.getMessage());
        }
//        finally
//        {
//
//            stmt.close();
//            conn.close();
//        }


        return false;

    }
    @Override
    public double calculateFee(String studentId)
    {
        Connection conn = DBUtils.getConnection();
        double fee = 0;
        try
        {
            stmt = conn.prepareStatement(SQLQueriesConstants.CALCULATE_FEES);
            stmt.setString(1, studentId);
            ResultSet rs = stmt.executeQuery();
            rs.next();
            fee = rs.getDouble(1);
        }
        catch(SQLException e)
        {
            System.out.println(e.getErrorCode());
            System.out.println(e.getMessage());
        }
        catch(Exception e)
        {
            System.out.println(e.getMessage());
        }
//        finally
//        {
//            stmt.close();
//            conn.close();
//        }

        return fee;
    }
    @Override
    public List<StudentGrade> viewGradeCard(String studentId) {
        Connection conn = DBUtils.getConnection();
        List<StudentGrade> grade_List = new ArrayList<>();
        try
        {
            stmt = conn.prepareStatement(SQLQueriesConstants.VIEW_GRADE);
            stmt.setString(1, studentId);
            ResultSet rs = stmt.executeQuery();

            while(rs.next())
            {
                String courseCode = rs.getString("courseCode");
                String courseName = rs.getString("courseName");
                String grade = rs.getString("grade");
                StudentGrade obj = new StudentGrade();
                obj.setGrade(grade);
                obj.setCourseID(courseCode);
                obj.setCourseName(courseName);
                grade_List.add(obj);
            }
        }
        catch(SQLException e)
        {
            System.out.println(e.getMessage());
        }
        catch(Exception e)
        {
            System.out.println(e.getMessage());
        }
//        finally
//        {
//            stmt.close();
//            conn.close();
//
//        }

        return grade_List;
    }

    @Override
    public List<Course> viewCourses(String studentId) throws SQLException {

        List<Course> availableCourseList = new ArrayList<>();
        Connection conn = DBUtils.getConnection();

        try
        {
            stmt = conn.prepareStatement(SQLQueriesConstants.VIEW_AVAILABLE_COURSES);
            stmt.setString(1, studentId);
            stmt.setBoolean(2, true);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                availableCourseList.add(new Course(rs.getString("courseCode"),
                        rs.getString("courseName"),
                        rs.getString("instructorId"), rs.getInt("seats"),rs.getDouble("fee")));

            }


        }
        catch (SQLException e)
        {
            System.out.println(e.getMessage());
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }
        finally
        {
            stmt.close();
            conn.close();
        }

        return availableCourseList;

    }
    @Override
    public List<Course> viewRegisteredCourses(String studentId) {

        Connection conn = DBUtils.getConnection();
        List<Course> registeredCourseList = new ArrayList<>();
        try
        {
            stmt = conn.prepareStatement(SQLQueriesConstants.VIEW_REGISTERED_COURSES);
            stmt.setString(1, studentId);

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                registeredCourseList.add(new Course(rs.getString("courseCode"), rs.getString("courseName"),
                        rs.getString("professorId"), rs.getInt("seats"),rs.getDouble("fee")));
            }
        }
        catch (SQLException e)
        {
            System.out.println(e.getMessage());

        }
//        finally
//        {
//            stmt.close();
//            conn.close();
//        }

        return registeredCourseList;
    }
    @Override
    public boolean getRegistrationStatus(String studentId)
    {
        Connection conn = DBUtils.getConnection();
        boolean status = false;
        try
        {
            stmt = conn.prepareStatement(SQLQueriesConstants.GET_REGISTRATION_STATUS);
            stmt.setString(1, studentId);
            ResultSet rs = stmt.executeQuery();
            rs.next();
            status = rs.getBoolean(1);

        }
        catch (SQLException e)
        {
            System.out.println(e.getMessage());

        }
//        finally
//        {
//            stmt.close();
//            conn.close();
//        }

        return status;
    }

    @Override
    public void setRegistrationStatus(String studentId)
    {
        Connection conn = DBUtils.getConnection();
        try
        {
            stmt = conn.prepareStatement(SQLQueriesConstants.SET_REGISTRATION_STATUS);
            stmt.setString(1, studentId);
            stmt.executeUpdate();

        }
        catch (SQLException e)
        {
            System.out.println(e.getMessage());

        }
//        finally
//        {
//            stmt.close();
//            conn.close();
//        }

    }

}