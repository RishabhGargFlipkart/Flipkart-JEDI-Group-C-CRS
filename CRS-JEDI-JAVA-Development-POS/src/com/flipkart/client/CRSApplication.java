package com.flipkart.client;


import com.flipkart.service.AdminServiceOperation;
import com.flipkart.bean.Student;
import java.util.Scanner;

import static com.flipkart.service.AdminServiceOperation.addStudentToList;

public class CRSApplication {
    static boolean loggedin = false;

//    AdminService adminService = new AdminServiceOperation();
    AdminCRSMenu admin=new AdminCRSMenu();
    ProfessorCRSMenu prof=new ProfessorCRSMenu();
    StudentCRSMenu student=new StudentCRSMenu();

    public static void main(String[]args){
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

    public void loginUser(){
        Scanner sc = new Scanner(System.in);
        String userID,password,role;
        System.out.println(" ");
        System.out.println("------------Login------------");
        System.out.print("Enter User ID: ");
        userID = sc.next();
        System.out.print("Enter Password: ");
        password=sc.next();
        System.out.println("Enter Role: Student,Professor,Admin ");
        role=sc.next();

        if(role.equalsIgnoreCase("Student")){
            student.createMenu(userID);
        }
        else if(role.equalsIgnoreCase("Professor")){

            prof.createMenu(userID,password);
        }
        else if(role.equalsIgnoreCase("Admin")){

            admin.createMenu(userID,password);
        }
        else
            System.out.println("Wrong password");
        

    }

    public void registerStudent(){
        Scanner sc = new Scanner(System.in);

        String userId,name,password,address,country,branchName;
        int gender,batch;

        System.out.println("---------------Student Registration-------------");
        System.out.print("Name:");
        name=sc.nextLine();
        System.out.print("ID:");
        userId=sc.next();
        System.out.print("Password:");
        password=sc.next();
        System.out.print("Gender: \t 1: Male \t 2.Female\t 3.Other");
        gender=sc.nextInt();
        sc.nextLine();
        System.out.print("Branch:");
        branchName=sc.nextLine();
        System.out.print("Batch:");
        batch=sc.nextInt();
        sc.nextLine();
        System.out.print("Address:");
        address=sc.nextLine();
        System.out.print("Country");
        country=sc.next();
        System.out.println("Wait for admin approval to login!");

        Student s = new Student();
        s.setUserId(userId);
        s.setBatch(batch);
        s.setName(name);
        s.setPassword(password);
        s.setBranchName(branchName);
        s.setRole("Student");
        s.setApproved(false);
        addStudentToList(s);

    }
    public void updatePassword(){
        Scanner sc = new Scanner(System.in);
        String userId,newPassword;
        System.out.println("------------------Update Password--------------------");
        System.out.print("Email");
        userId=sc.next();
        System.out.print("New Password:");
        newPassword=sc.next();

    }

}
