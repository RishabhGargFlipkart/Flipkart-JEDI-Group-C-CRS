package com.flipkart.service;

import com.flipkart.bean.Course;
import com.flipkart.bean.StudentGrade;

import java.util.ArrayList;
import java.util.List;
import com.flipkart.data.CourseList;
import com.flipkart.data.GradeCard;
import com.flipkart.data.StudentBucket;
import com.flipkart.data.IsRegistered;
import com.flipkart.dao.RegistrationDAO;
import com.flipkart.dao.RegistrationDAOImpl;
public class RegistrationServiceOperation implements RegistrationService{
    private static volatile RegistrationServiceOperation instance = null;

    private RegistrationServiceOperation() {

    }
    public static RegistrationServiceOperation getInstance() {
        if (instance == null) {
            synchronized (RegistrationServiceOperation.class) {
                instance = new RegistrationServiceOperation();
            }
        }
        return instance;
    }

    RegistrationDAO registrationDaoInterface = RegistrationDAOImpl.getInstance();
    @Override
    public boolean addCourse(String courseCode, String studentId,List<Course> availableCourseList)
    {
        if (registrationDaoInterface.numOfRegisteredCourses(studentId) >= 6)
        {
//            throw new CourseLimitExceedException(6);
        }
        else if (registrationDaoInterface.isRegistered(courseCode, studentId))
        {
            return false;
        }
//        else if (!registrationDaoInterface.seatAvailable(courseCode))
//        {
//            throw new SeatNotAvailableException(courseCode);
//        }
//        else if(!StudentValidator.isValidCourseCode(courseCode, availableCourseList))
//        {
//            throw new CourseNotFoundException(courseCode);
//        }



        return registrationDaoInterface.addCourse(courseCode, studentId);

    }

    @Override
    public boolean dropCourse(String courseCode, String studentId,List<Course> registeredCourseList)  {
//        if(!StudentValidator.isRegistered(courseCode, studentId, registeredCourseList))
//        {
//            throw new CourseNotFoundException(courseCode);
//        }

        return registrationDaoInterface.dropCourse(courseCode, studentId);

    }

    @Override
    public List<StudentGrade> viewGradeCard(String studentId) {
        return registrationDaoInterface.viewGradeCard(studentId);
    }
    public List<Course> viewCourses(String studentId){
        return CourseList.courseList;
    }

    @Override
    public List<Course> viewRegisteredCourses(String studentId) {
        return registrationDaoInterface.viewRegisteredCourses(studentId);
    }


    @Override
    public double calculateFee(String studentId) {
        return registrationDaoInterface.calculateFee(studentId);
    }
    @Override
    public boolean getRegistrationStatus(String studentId)  {
        return registrationDaoInterface.getRegistrationStatus(studentId);
    }
    @Override
    public void setRegistrationStatus(String studentId) {
        registrationDaoInterface.setRegistrationStatus(studentId);

    }
}