package com.flipkart.client;

import java.util.Scanner;
import java.util.List;
public class StudentCRSMenu {
    Scanner sc=new Scanner(System.in);
    public void create_menu(String studentId)
    {
        int choice;
        System.out.println("*****************************");
        System.out.println("**********Student Menu*********");
        System.out.println("*****************************");
        System.out.println("1. Course Registration");
        System.out.println("2. Add Course");
        System.out.println("3. Drop Course");
        System.out.println("4. View Course");
        System.out.println("5. View Registered Courses");
        System.out.println("6. View grade card");
        System.out.println("7. Make Payment");
        System.out.println("8. Logout");
        System.out.println("*****************************");
        choice=sc.nextInt();
        switch(choice){
            case 1:
                registerCourses(studentId);
                break;
            case 2:
                addCourse(studentId);
                break;
            case 3:
                dropCourse(studentId);
                break;
            case 4:
                viewCourse(studentId);
                break;
            case 5:
                viewRegisteredCourse(studentId);
                break;

            case 6:
                viewGradeCard(studentId);
                break;

            case 7:
                make_payment(studentId);
                break;

            case 8:
                return;

            default:
                System.out.println("***** Wrong Choice *****");
        }


    }
    private void registerCourses(String studentId)
    {

    }
    private void addCourse(String studentId)
    {

    }
    private boolean getRegistrationStatus(String studentId)
    {
        return false;
    }
    private void dropCourse(String studentId)
    {

    }

    private List<String> viewCourse(String studentId)
    {
        return null;
    }
    private List<String> viewRegisteredCourse(String studentId)
    {
        return null;
    }

    private void viewGradeCard(String studentId)
    {

    }
    private void make_payment(String studentId)
    {

    }
}
