package com.flipkart.client;


import com.flipkart.dao.UserDAO;
import com.flipkart.dao.UserDAOImpl;
import com.flipkart.exception.*;
import com.flipkart.service.AdminServiceOperation;
import com.flipkart.bean.Student;

import java.sql.SQLException;
import java.util.Scanner;


public class CRSApplication {
    static boolean loggedin = false;
    UserDAO userInterface = UserDAOImpl.getInstance();
//    AdminService adminService = new AdminServiceOperation();



    public static void main(String[]args) throws UserNotFoundException, CourseNotDeletedException, CourseNotFoundException, CourseFoundException, UserIdAlreadyInUseException, StudentNotFoundForApprovalException, ProfessorNotAddedException {

        Scanner sc = new Scanner(System.in);
        CRSApplication crsApplication=new CRSApplication();
        int userInput;
        createMainMenu();
        userInput=sc.nextInt();

        while(userInput!=4)
        {
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
                default:
                    System.out.println("Invalid Input");
            }
            createMainMenu();
            userInput=sc.nextInt();
        }

        sc.close();
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

    public void loginUser() throws CourseNotDeletedException, CourseNotFoundException, CourseFoundException, UserIdAlreadyInUseException, StudentNotFoundForApprovalException, ProfessorNotAddedException, UserNotFoundException {
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
            System.out.println("Wrong password");
        }

    }

    public void registerStudent(){
        Scanner sc = new Scanner(System.in);

        String userId,name,password,address,country,branchName;
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
        gender=sc.next();
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
        //int newStudentId=studentDAO.addStudent(s);

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
