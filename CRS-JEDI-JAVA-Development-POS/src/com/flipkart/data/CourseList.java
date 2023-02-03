package com.flipkart.data;

import com.flipkart.bean.Course;

import java.util.ArrayList;
import java.util.List;

public class CourseList {
    public static List<Course> courseList;
    public CourseList()
    {
        courseList = new ArrayList<Course>();
        Course course1 = new Course();
        course1.setCourseCode("1");
        course1.setCourseName("DAA");
        course1.setInstructorId("P3");
        course1.setSeats(9);

        Course course2 = new Course();
        course2.setCourseCode("2");
        course2.setCourseName("DSA");
        course2.setInstructorId("P2");
        course2.setSeats(9);

        Course course3 = new Course();
        course3.setCourseCode("3");
        course3.setCourseName("OS");
        course3.setInstructorId("P2");
        course3.setSeats(9);

        Course course4 = new Course();
        course4.setCourseCode("4");
        course4.setCourseName("DBMS");
        course4.setInstructorId("P1");
        course4.setSeats(9);


        courseList.add(course1);
        courseList.add(course2);
        courseList.add(course3);
        courseList.add(course4);
    }
    public List<Course> getCourseList()
    {
        return this.courseList;
    }
}