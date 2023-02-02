package com.flipkart.client;

import com.flipkart.bean.Course;
import com.flipkart.bean.EnrolledStudent;
import com.flipkart.bean.Professor;
import com.flipkart.service.ProfessorService;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import com.flipkart.service.ProfessorServiceOperation;

public class ProfessorCRSMenu {
    ProfessorService professorInterface=new ProfessorServiceOperation();

    List<Professor> profDB=new ArrayList<Professor>();


    public static void main(String[] args) {
        Scanner sc= new Scanner(System.in);
        ProfessorCRSMenu obj = new ProfessorCRSMenu();
        System.out.println("Enter Professor ID");
        String profID=sc.next();
        obj.createMenu(profID);
    }

    public void createMenu(String profId) {
        Scanner sc = new Scanner(System.in);
        int userInput;
        System.out.println("---------Professor Menu---------");
        System.out.println("1. View Your Courses");
        System.out.println("2. View Enrolled Students");
        System.out.println("3. Add grade");
        System.out.println("4. Assign a course");
        System.out.println("5. Logout");
        System.out.println("---------------------------------");
        userInput=sc.nextInt();
        while (userInput != 5) {
            switch (userInput) {
                case 1:
                    //view all the courses taught by the professor
                    getCourses(profId);
                    break;
                case 2:
                    //view all the enrolled students for the course
                    System.out.println("Enter Course Code:");
                    String courseCode=sc.next();
                    viewEnrolledStudents(profId,courseCode);
                    break;

                case 3:
                    //add grade for a student
                    addGrade(profId);
                    break;
                case 4:
                    assignCourse(profId);
                    break;
                case 5:
                    //logout from the system
                    //CRSApplication.loggedin = false;
                    return;
                default:
                    System.out.println("Invalid Input");
            }
            userInput=sc.nextInt();
        }

    }

//    public void generateDB()
//    {
//        Professor p1=new Professor();
//        p1.setUserId("P1");
//        p1.setName("Praneet");
//        p1.setDepartment("CS");
//        p1.setRole("Professor");
//        p1.setPassword("Praneet");
//
//        Professor p2=new Professor();
//        p1.setUserId("P2");
//        p1.setName("P2");
//        p1.setDepartment("Mech");
//        p1.setRole("Professor");
//        p1.setPassword("Praneet");
//
//        Professor p3=new Professor();
//        p1.setUserId("P3");
//        p1.setName("P3");
//        p1.setDepartment("ECE");
//        p1.setRole("Professor");
//        p1.setPassword("Praneet");
//    }


    public void getCourses(String profId) {
        List<Course> coursesEnrolled= professorInterface.getCourses(profId);
        System.out.println(String.format("%20s %20s %20s","COURSE CODE","COURSE NAME","No. of Students  enrolled" ));
        for(Course obj: coursesEnrolled)
        {
            System.out.println(String.format("%20s %20s %20s",obj.getCourseCode(), obj.getCourseName(),10- obj.getSeats()));
        }
    }

    public void viewEnrolledStudents(String profId,String courseCode)
    {
        List<Course> coursesEnrolled=professorInterface.getCourses(profId);


        List<EnrolledStudent> enrolledStudents=new ArrayList<EnrolledStudent>();
        enrolledStudents=professorInterface.viewEnrolledStudents(profId,courseCode);
        if(enrolledStudents.size()==0)
            return;
        System.out.println(String.format("%20s %20s %20s","COURSE CODE","COURSE CODE","Students  enrolled" ));

        for(EnrolledStudent obj: enrolledStudents)
        {
            System.out.println(String.format("%20s %20s %20s",obj.getCourseCode(), obj.getCourseName(),obj.getStudentId()));
        }

    }

    public void addGrade(String profId) {
        Scanner sc = new Scanner(System.in);

        String studentId;
        String courseCode, grade;
        System.out.println("Enter student ID:");
        studentId=sc.next();
        System.out.println("Enter Course Code:");
        courseCode=sc.next();
        System.out.println("Enter grade");
        grade=sc.next();

        professorInterface.addGrade(profId,studentId,courseCode,grade);

    }
    public void assignCourse(String profId)
    {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the course code you would like to teach:");
        String cc=sc.next();
        boolean check=professorInterface.assignCourse(profId,cc);
        if(check)
        {
            System.out.println("Course assigned successfully");
        }
        else
            System.out.println("The course is either taken or invalid course code.");

    }
}