package com.flipkart.client;


import com.flipkart.dao.StudentDAO;
import com.flipkart.dao.StudentDAOImpl;
import com.flipkart.dao.UserDAO;
import com.flipkart.dao.UserDAOImpl;
import com.flipkart.exception.*;
import com.flipkart.bean.Student;

import java.sql.SQLException;
import java.util.InputMismatchException;
import java.util.Scanner;


public class CRSApplication {
    static boolean loggedin = false;
    StudentDAO studentDAO=StudentDAOImpl.getInstance();
    UserDAO userInterface = UserDAOImpl.getInstance();

    public static void main(String[]args) throws UserNotFoundException, CourseNotDeletedException, CourseNotFoundException, CourseFoundException, UserIdAlreadyInUseException, StudentNotFoundForApprovalException, ProfessorNotAddedException, SQLException, ClassNotFoundException, NoEnrolledStudentsException, StudentNotRegistered, GradeAssignedException, NoAssignedCourseException, ProfessorAssignedException {

        Scanner sc = new Scanner(System.in);
        CRSApplication crsApplication=new CRSApplication();
        int userInput=0;
        while(true){
            createMainMenu();
            try {

                userInput = sc.nextInt();
            }catch (InputMismatchException ex) {
                System.out.println("Enter correct input");
                sc.nextLine();
            }


    //        while(userInput!=4)
    //        {
            switch(userInput){
                case 1:
                    crsApplication.loginUser();
                    break;
                case 2:
                    crsApplication.registerStudent();
                    break;
                case 3:
                    crsApplication.updatePassword();
                    break;
                case 4:
                    sc.close();
                    return;
                default:
//                    System.out.println("Invalid Input");
            }
//            createMainMenu();
//            try {
//                userInput = sc.nextInt();
//            } catch (InputMismatchException e) {
//                break;
//            }
        }
    }

    public static void createMainMenu(){
        System.out.println("-------------Welcome to Course Registration System! Choose from the options given below-------------");
        System.out.println("             1. Login");
        System.out.println("             2. Registration of Student");
        System.out.println("             3. Update Password");
        System.out.println("             4. Exit");
        System.out.println(" ");
        System.out.println(" Enter your option(1 to 4)");
    }

    public void loginUser() throws CourseNotDeletedException, CourseNotFoundException, CourseFoundException, UserIdAlreadyInUseException, StudentNotFoundForApprovalException, ProfessorNotAddedException, UserNotFoundException, SQLException, ClassNotFoundException, NoEnrolledStudentsException, StudentNotRegistered, GradeAssignedException, NoAssignedCourseException, ProfessorAssignedException {
        Scanner sc = new Scanner(System.in);
        String userId,password,role;
        System.out.println(" ");
        System.out.println("------------Login------------");
        System.out.print("Enter User ID: ");
        userId = sc.next();
        System.out.print("Enter Password: ");
        password=sc.next();
        System.out.println("Enter Role: Student,Professor,Admin ");
        role=sc.next();
        loggedin = userInterface.verifyCredentials(userId, password);

        if(loggedin){
            if(role.equalsIgnoreCase("Student")){

                StudentCRSMenu student = new StudentCRSMenu();
                student.createMenu(userId);
            }
            else if(role.equalsIgnoreCase("Professor")){
                ProfessorCRSMenu prof = new ProfessorCRSMenu();
                prof.createMenu(userId,password);
            }
            else if(role.equalsIgnoreCase("Admin")){
                AdminCRSMenu admin=new AdminCRSMenu();
                admin.createMenu();
            }
        }
        else{
            System.out.println("Wrong user Id or password");
        }

    }

    public void registerStudent(){
        Scanner sc = new Scanner(System.in);

        String userId,name,password,address,branchName;
        String gender;
        int batch;

        System.out.println("---------------Student Registration-------------");
        System.out.print("Name:");
        name=sc.nextLine();
        System.out.print("ID:");
        userId=sc.next();
        System.out.print("Password:");
        password=sc.next();
        System.out.print("Gender: \t 1: Male \t 2.Female\t 3.Other");
        int g=sc.nextInt();
        if(g==1)
            gender="Male";
        else if(g==2)
            gender="Female";
        else if(g==3)
            gender="Other";
        else
        {
            System.out.println("Enter correct option");
            return;
        }
        sc.nextLine();
        System.out.print("Branch:");
        branchName=sc.nextLine();
        System.out.print("Batch:");
        batch=sc.nextInt();
        sc.nextLine();
        System.out.print("Address:");
        address=sc.nextLine();

        System.out.println("Wait for admin approval to login!");

        Student s = new Student();
        s.setUserId(userId);
        s.setBatch(batch);
        s.setName(name);
        s.setPassword(password);
        s.setBranchName(branchName);
        s.setRole("Student");
        s.setGender(gender);
        s.setAddress(address);

        try {
            studentDAO.addStudent(s);
        }
        catch (StudentNotRegisteredException e) {
            System.out.println(e.getMessage());
        }

    }
    public void updatePassword(){
        Scanner sc=new Scanner(System.in);
        String userId,newPassword;
        try
        {
            System.out.println("------------------Update Password--------------------");
            System.out.println("Email");
            userId=sc.next();
            System.out.println("New Password:");
            newPassword=sc.next();
            boolean isUpdated=userInterface.updatePassword(userId, newPassword);
            if(isUpdated)
                System.out.println("Password updated successfully!");

            else
                System.out.println("Something went wrong, please try again!");
        }
        catch(Exception ex)
        {
            System.out.println("Error Occurred "+ex.getMessage());
        }

    }

}
