package com.flipkart.client;

import com.flipkart.bean.Course;
import com.flipkart.bean.EnrolledStudent;
import com.flipkart.bean.Professor;
import com.flipkart.constant.ColourConstant;
import com.flipkart.dao.ProfessorDAO;
import com.flipkart.dao.ProfessorDAOImpl;
import com.flipkart.exception.*;
import com.flipkart.service.ProfessorService;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import com.flipkart.service.ProfessorServiceOperation;
import java.util.*;

/**
 * Class for Professor CRS Menu
 */
public class ProfessorCRSMenu {
    ProfessorService professorInterface=new ProfessorServiceOperation();
    ProfessorDAO professorDAO=new ProfessorDAOImpl();
    List<Professor> profDB=new ArrayList<Professor>();
    public ProfessorCRSMenu() {
        /*Professor p1 = new Professor();
        p1.setUserId("P1");
        p1.setName("Praneet");
        p1.setDepartment("CS");
        p1.setRole("Professor");
        p1.setPassword("Praneet");

        Professor p2 = new Professor();
        p2.setUserId("P2");
        p2.setName("P2");
        p2.setDepartment("Mech");
        p2.setRole("Professor");
        p2.setPassword("Praneet");

        Professor p3 = new Professor();
        p3.setUserId("P3");
        p3.setName("P3");
        p3.setDepartment("ECE");
        p3.setRole("Professor");
        p3.setPassword("Praneet");
        profDB.add(p1);
        profDB.add(p2);
        profDB.add(p3);
        */




    }

//    public static void main(String[] args) {
//        Scanner sc= new Scanner(System.in);
//        ProfessorCRSMenu obj = new ProfessorCRSMenu();
//        System.out.println("Enter Professor ID");
//        String profID=sc.next();
//        obj.createMenu(profID);
//    }

    /**
     * Method to create professor menu
     * @param profId
     * @param pwd
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    public void createMenu(String profId,String pwd) throws SQLException, ClassNotFoundException {
        boolean check = false;
        check = professorDAO.getProfessors(profId, pwd);
        if (check == false) {
            System.out.println("Invalid credentials");
            return;
        }

        Scanner sc = new Scanner(System.in);

        int userInput;
        while (true) {
            System.out.println("\033[0;1m---------Professor Menu---------");
            System.out.println("1. View Your Courses");
            System.out.println("2. View Enrolled Students");
            System.out.println("3. Add grade");
            System.out.println("4. Assign a course");
            System.out.println("5. Logout");
            System.out.println("---------------------------------");

            userInput = sc.nextInt();

            switch (userInput) {
                case 1:
                    //view all the courses taught by the professor
                    getCourses(profId);
                    System.out.println("\n");
                    System.out.println("\n");
                    System.out.println("---------------------------------------------------------------------------------------------------");
                    System.out.println("---------------------------------------------------------------------------------------------------");
                    break;
                case 2:
                    //view all the enrolled students for the course
                    System.out.println("Enter Course Code:");
                    String courseCode = sc.next();
                    viewEnrolledStudents(profId, courseCode);
                    System.out.println("---------------------------------------------------------------------------------------------------");
                    System.out.println("---------------------------------------------------------------------------------------------------");
                    System.out.println("\n");
                    break;

                case 3:
                    //add grade for a student
                    addGrade(profId);
                    System.out.println("---------------------------------------------------------------------------------------------------");
                    System.out.println("---------------------------------------------------------------------------------------------------");
                    break;
                case 4:
                    assignCourse(profId);
                    System.out.println("---------------------------------------------------------------------------------------------------");
                    System.out.println("---------------------------------------------------------------------------------------------------");
                    break;
                case 5:
                    //logout from the system
                    //CRSApplication.loggedin = false;
                    return;
                default:
                    System.out.println("Invalid Input");
                    System.out.println("---------------------------------------------------------------------------------------------------");
                    System.out.println("---------------------------------------------------------------------------------------------------");
            }
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


    /**
     * Method to get list courses
     * @param profId
     */
    public void getCourses(String profId) {
        List<Course> coursesEnrolled=new ArrayList<>();
        try {
            coursesEnrolled = professorInterface.getCourses(profId);
        }
        catch (ClassNotFoundException| NoAssignedCourseException e)
        {
            System.out.println(e.getMessage());
        }
        Formatter fmt=new Formatter();
        System.out.println(ColourConstant.ANSI_BLUE+"\033[1mCOURSE CODE\t\t\tCOURSE NAME\t\t\tNo. of students enrolled\033[0m"+ColourConstant.ANSI_RESET);
        //System.out.println(String.format("%20s %20s %20s","COURSE CODE","COURSE NAME","No. of Students  enrolled" ));
        for(Course obj: coursesEnrolled)
        {
            fmt.format("%14s %14s %17s\n", obj.getCourseCode(), obj.getCourseName(),(10- obj.getSeats()));
        }
        System.out.println(fmt);
    }

    /**
     * Method to view enrolled students
     * @param profId
     * @param courseCode
     */
    public void viewEnrolledStudents(String profId,String courseCode) {
        List<EnrolledStudent> enrolledStudents=new ArrayList<EnrolledStudent>();
        try {
            enrolledStudents = professorInterface.viewEnrolledStudents(profId, courseCode);
        } catch (ClassNotFoundException | NoEnrolledStudentsException e)
        {
            System.out.println(e.getMessage());
        }
        if(enrolledStudents.size()==0)
            return;
        Formatter fmt=new Formatter();
        //System.out.println(String.format("%20s %20s %20s","COURSE CODE","COURSE CODE","Students  enrolled" ));
        System.out.println(ColourConstant.ANSI_BLUE+"\033[1mCOURSE CODE\t\t\tCOURSE NAME\t\t\tStudents enrolled\033[0m"+ColourConstant.ANSI_RESET);
        for(EnrolledStudent obj: enrolledStudents)
        {

            fmt.format("%14s %14s %17s\n", obj.getCourseCode(), obj.getCourseName(),obj.getStudentId());
        }
        System.out.println(fmt);
    }

    /**
     * Method to add grade for a student
     * @param profId
     */
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

        try {
            professorInterface.addGrade(profId, studentId, courseCode, grade);
        }
        catch (ClassNotFoundException| StudentNotRegistered| GradeAssignedException e)
        {
            System.out.println(e.getMessage());
        }

    }

    /**
     * Method to assign a course
     * @param profId
     */
    public void assignCourse(String profId) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the course code you would like to teach:");
        String cc=sc.next();
        boolean check =false;
        try {
            check = professorInterface.assignCourse(profId, cc);
        }
        catch (ClassNotFoundException | CourseNotFoundException | ProfessorAssignedException e)
        {
            System.out.println(e.getMessage());
        }
        if(check)
        {
            System.out.println("Course assigned successfully");
        }

    }
}