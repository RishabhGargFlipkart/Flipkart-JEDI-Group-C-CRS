package com.flipkart.client;

import com.flipkart.bean.Course;
import com.flipkart.bean.EnrolledStudent;
import com.flipkart.service.ProfessorService;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ProfessorCRSMenu {
    public void createMenu(String profId) {
        Scanner sc = new Scanner(System.in);
        int userInput;
        System.out.println("---------Professor Menu---------");
        System.out.print("1. View Courses");
        System.out.print("2. View Enrolled Students");
        System.out.print("3. Add grade");
        System.out.print("4. Logout");
        System.out.print("---------------------------------");
        userInput=sc.nextInt();
        while (userInput != 4) {
            switch (userInput) {
                case 1:
                    //view all the courses taught by the professor
                    getCourses(profId);
                    break;
                case 2:
                    //view all the enrolled students for the course
                    viewEnrolledStudents(profId);
                    break;

                case 3:
                    //add grade for a student
                    addGrade(profId);
                    break;
                case 4:
                    //logout from the system
                    //CRSApplication.loggedin = false;
                    return;
                default:
                    System.out.println("Invalid Input");
            }
            userInput=sc.nextInt();
        }

    }

    public void getCourses(String profId) {
//        List<Course> coursesEnrolled= ProfessorService.getCourses(Integer.parseInt(profId));
//        System.out.print(String.format("%20s %20s %20s","COURSE CODE","COURSE NAME","No. of Students  enrolled" ));
//        for(Course obj: coursesEnrolled)
//        {
//            System.out.print(String.format("%20s %20s %20s",obj.getCourseCode(), obj.getCourseName(),10- obj.getSeats()));
//        }
    }

    public void viewEnrolledStudents(String profId)
    {
//        List<Course> coursesEnrolled=ProfessorService.getCourses(Integer.parseInt(profId));
//        System.out.print(String.format("%20s %20s %20s","COURSE CODE","COURSE CODE","Students  enrolled" ));
//
//        List<EnrolledStudent> enrolledStudents=new ArrayList<EnrolledStudent>();
//        enrolledStudents=ProfessorService.viewEnrolledStudents(profId);
//        for(EnrolledStudent obj: enrolledStudents)
//        {
//            System.out.print(String.format("%20s %20s %20s",obj.getCourseCode(), obj.getCourseName(),obj.getStudentId()));
//        }

    }

    public void addGrade(String profId) {
        Scanner sc = new Scanner(System.in);

        int studentId;
        String courseCode, grade;
    }
}