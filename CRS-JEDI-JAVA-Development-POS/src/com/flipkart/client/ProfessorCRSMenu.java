package com.flipkart.client;

import com.flipkart.bean.Course;
import com.flipkart.bean.EnrolledStudent;
import com.flipkart.bean.Professor;
import com.flipkart.dao.ProfessorDAO;
import com.flipkart.dao.ProfessorDAOImpl;
import com.flipkart.service.ProfessorService;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import com.flipkart.service.ProfessorServiceOperation;

public class ProfessorCRSMenu {
    ProfessorService professorInterface=new ProfessorServiceOperation();
    ProfessorDAO professorDAO=new ProfessorDAOImpl();
    List<Professor> profDB=new ArrayList<Professor>();
    public ProfessorCRSMenu() throws SQLException, ClassNotFoundException {
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

    public void createMenu(String profId,String pwd) throws SQLException, ClassNotFoundException {
        boolean check = false;
        check=professorDAO.getProfessors(profId,pwd);
        if(check == false){
            System.out.println("Invalid credentials");
            return;
        }

        Scanner sc = new Scanner(System.in);
        while(true){
            System.out.println("---------Professor Menu---------");
            System.out.println("1. View Your Courses");
            System.out.println("2. View Enrolled Students");
            System.out.println("3. Add grade");
            System.out.println("4. Assign a course");
            System.out.println("5. Logout");
            System.out.println("---------------------------------");
            int userInput = sc.nextInt();

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


    public void getCourses(String profId) throws SQLException, ClassNotFoundException {
        List<Course> coursesEnrolled= professorInterface.getCourses(profId);
        System.out.println(String.format("%20s %20s %20s","COURSE CODE","COURSE NAME","No. of Students  enrolled" ));
        for(Course obj: coursesEnrolled)
        {
            System.out.println(String.format("%20s %20s %20s",obj.getCourseCode(), obj.getCourseName(),10- obj.getSeats()));
        }
    }

    public void viewEnrolledStudents(String profId,String courseCode) throws SQLException, ClassNotFoundException {
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

    public void addGrade(String profId) throws SQLException, ClassNotFoundException {
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
    public void assignCourse(String profId) throws SQLException, ClassNotFoundException {
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