package com.flipkart.client;


import com.flipkart.dao.StudentDAO;
import com.flipkart.dao.StudentDAOImpl;
import com.flipkart.dao.UserDAO;
import com.flipkart.dao.UserDAOImpl;
import com.flipkart.exception.*;
import com.flipkart.bean.Student;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
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
                    System.out.println("---------------------------------------------------------------------------------------------------");
                    System.out.println("---------------------------------------------------------------------------------------------------");
                    break;
                case 2:
                    crsApplication.registerStudent();
                    System.out.println("---------------------------------------------------------------------------------------------------");
                    System.out.println("---------------------------------------------------------------------------------------------------");
                    break;
                case 3:
                    crsApplication.updatePassword();
                    System.out.println("---------------------------------------------------------------------------------------------------");
                    System.out.println("---------------------------------------------------------------------------------------------------");
                    break;
                case 4:
                    sc.close();
                    return;
                default:
                    System.out.println("Invalid Menu Input");
                    System.out.println("---------------------------------------------------------------------------------------------------");
                    System.out.println("---------------------------------------------------------------------------------------------------");
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
        System.out.println("\033[0;1m-------------Welcome to Course Registration System (CRS) ! Choose from the options given below-------------\033[0m");
        System.out.println("             1. Login");
        System.out.println("             2. Registration of Student");
        System.out.println("             3. Update Password");
        System.out.println("             4. Exit");
        System.out.println(" ");
        System.out.println("\033[0;1m Enter your option(1 to 4)\033[0m");
    }

    public void loginUser() throws CourseNotDeletedException, CourseNotFoundException, CourseFoundException, UserIdAlreadyInUseException, StudentNotFoundForApprovalException, ProfessorNotAddedException, SQLException, ClassNotFoundException {
        Scanner sc = new Scanner(System.in);
        String userId,password,role;
        System.out.println(" ");
        System.out.println("\033[0;1m ------------Login------------\033[0m");
        System.out.print("Enter User ID: ");
        userId = sc.next();
        System.out.print("Enter Password: ");
        password=sc.next();
        System.out.println("Enter Role: Student,Professor,Admin ");
        role=sc.next();
        try{
            loggedin = userInterface.verifyCredentials(userId, password);
            LocalDate localDate = LocalDate.now();
            LocalTime localTime = LocalTime.now();
            System.out.println("Hi, you have successfully logged in at " + localTime+ " on "+ localDate +"\n");
        }catch (UserNotFoundException e){
            System.out.println(e.getMessage());
        }

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
            else{
                System.out.println("Enter a correct role!");
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

        System.out.println("\033[0;1m---------------Student Registration-------------\033[0m");
        System.out.print("ID:");
        userId=sc.next();
        System.out.print("Name:");
        name=sc.next();
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
            System.out.println("\033[0;1m------------------Update Password--------------------\033[0m");
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
