package com.flipkart.service;

import com.flipkart.bean.Course;
import com.flipkart.bean.StudentGrade;

import java.util.ArrayList;
import java.util.List;
import com.flipkart.data.CourseList;
import com.flipkart.data.GradeCard;
import com.flipkart.data.StudentBucket;
import com.flipkart.data.IsRegistered;
public class RegistrationServiceOperation implements RegistrationService{
    CourseList cl = new CourseList();
    boolean is_registered = false;
    public boolean addCourse(String courseCode,String studentId,List<Course> courseList){

        courseList = cl.getCourseList();
        for(Course c:StudentBucket.studentBucket)
        {
            if(c.getCourseCode().equalsIgnoreCase(courseCode))
            {
                return false;
            }
        }
        for(Course c:courseList)
        {
            if(c.getCourseCode().equalsIgnoreCase(courseCode))
            {
                StudentBucket.studentBucket.add(c);
                return true;
            }
        }
        return false;
    }

    public boolean dropCourse(String CourseCode,String studentId,List<Course> registeredCourseList){
        for(Course c:StudentBucket.studentBucket)
        {
            if(c.getCourseCode().equalsIgnoreCase(CourseCode))
            {
                StudentBucket.studentBucket.remove(c);
                return true;
            }
        }
        return false;
    }

    public List<StudentGrade> viewGradeCard(String studentId){
        return GradeCard.gradeCard;
    }
    public List<Course> viewCourses(){
        return CourseList.courseList;
    }

    @Override
    public List<Course> viewRegisteredCourses(String studentId) {
        System.out.println(StudentBucket.studentBucket.size());
        return StudentBucket.studentBucket;
    }


    public double calculateFee(String studentId){
        return 0.0;
    }
    public boolean getRegistrationStatus(String studentId){
        return IsRegistered.isRegistered;
    }
    public boolean setRegistrationStatus(String studentId)  {
        IsRegistered.isRegistered = true;
        return IsRegistered.isRegistered;
    }
}