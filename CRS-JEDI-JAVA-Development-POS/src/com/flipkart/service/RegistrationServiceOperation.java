package com.flipkart.service;

import com.flipkart.bean.Course;
import com.flipkart.bean.StudentGrade;

import java.util.ArrayList;
import java.util.List;
import com.flipkart.data.CourseList;
public class RegistrationServiceOperation implements RegistrationService{
    CourseList cl = new CourseList();
    List<Course> studentBucket = new ArrayList<Course>();
    public boolean addCourse(String courseCode,String studentId,List<Course> courseList){

        courseList = cl.getCourseList();
        for(Course c:courseList)
        {
            if(c.getCourseCode().equalsIgnoreCase(courseCode))
            {
                studentBucket.add(c);
                System.out.println(studentBucket.size());
                return true;
            }
        }
        return false;
    }

    public boolean dropCourse(String CourseCode,String studentId,List<Course> registeredCourseList){
        return false;
    }

    public List<StudentGrade> viewGradeCard(String studentId){
        return null;
    }
    public List<Course> viewCourses(){
        return cl.getCourseList();
    }

    @Override
    public List<Course> viewRegisteredCourses(String studentId) {
        return studentBucket;
    }


    public double calculateFee(String studentId){
        return 0.0;
    }
    public boolean getRegistrationStatus(String studentId){
        return false;
    }
    public void setRegistrationStatus(String studentId)  {


    }
}