package com.flipkart.client;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.*;

import com.flipkart.bean.Course;
import com.flipkart.dao.*;
import com.flipkart.service.ProfessorService;
import com.flipkart.service.ProfessorServiceOperation;
import com.flipkart.service.RegistrationService;
import com.flipkart.service.RegistrationServiceOperation;
import com.flipkart.constant.ModeOfPaymentConstant;
import com.flipkart.service.NotificationService;
import com.flipkart.service.NotificationServiceOperation;
import com.flipkart.constant.ColourConstant;

import java.util.Scanner;
import java.util.List;
import com.flipkart.bean.StudentGrade;

/**
 * Class for student CRS Menu
 */
public class StudentCRSMenu {
    Random rand=new Random();
    Scanner sc = new Scanner(System.in);
    RegistrationService registrationInterface = RegistrationServiceOperation.getInstance();
    ProfessorService professorInterface = new ProfessorServiceOperation();
    NotificationService notificationInterface=NotificationServiceOperation.getInstance();
    private boolean is_registered,is_loggedin;

    /**
     * Method to create student menu
     * @param studentId
     */
    public void createMenu(String studentId)
    {

        int choice = 0;
        is_loggedin = getLoginStatus(studentId);
        is_registered=getRegistrationStatus(studentId);
        if(!is_loggedin)
        {
            System.out.println("Registration is not approved. Wait for Admin approval.");
            return;
        }
        LocalDate localDate = LocalDate.now();
        LocalTime localTime = LocalTime.now();
        System.out.println("Hi, you have successfully logged in at " + localTime+ " on "+ localDate +"\n");
        while(true) {
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
            try {
                choice = sc.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Enter integer");
            }
            switch (choice) {
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

    }

    /**
     * Method to register courses
     * @param studentId
     */
    private void registerCourses(String studentId)
    {
        if(is_registered)
        {
            System.out.println(" Registration is already completed");
            return;
        }

        int count = 0;
        while(count < 6)
        {
            try
            {
                List<Course> courseList=viewCourse(studentId);

                if(courseList==null)
                    return;

                System.out.println("Enter Course Code : " + (count+1));
                String courseCode = sc.next();

                if(registrationInterface.addCourse(courseCode,studentId,courseList))
                {
                    System.out.println("Course " + courseCode + " registered sucessfully.");
                    count++;
                }
                else
                {
                    System.out.println(" You have already registered for Course : " + courseCode);
                }
            }
            catch(Exception e)
            {
                System.out.println(e.getMessage());
                return;
            }
        }
        System.out.println("6 courses have been successfully registered");
        System.out.println("No more courses can be registered");
        is_registered = true;

        try
        {
            registrationInterface.setRegistrationStatus(studentId);
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }

    }

    /**
     * Method to add a course to registered courses
     * @param studentId
     */
    private void addCourse(String studentId)
    {
        if(is_registered)
        {
            List<Course> availableCourseList=viewCourse(studentId);

            if(availableCourseList==null)
                return;

            try
            {
                System.out.println("Enter Course Code : " );
                String courseCode = sc.next();
                if(registrationInterface.addCourse(courseCode, studentId,availableCourseList))
                {
                    System.out.println(" You have successfully registered for Course : " + courseCode);
                }
                else
                {
                    System.out.println(" You have already registered for Course : " + courseCode);
                }
            }
            catch(Exception e)
            {
                System.out.println(e.getMessage());
            }
        }
        else
        {
            System.out.println("Please complete registration");
        }
    }

    /**
     * Method to get registration status
     * @param studentId
     * @return boolean
     */
    private boolean getRegistrationStatus(String studentId)
    {
        try
        {
            return registrationInterface.getRegistrationStatus(studentId);
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }
        return false;
    }

    /**
     * Method to drop registered course
     * @param studentId
     */
    private void dropCourse(String studentId)
    {
        if(is_registered)
        {
            List<Course> registeredCourseList=viewRegisteredCourse(studentId);

            if(registeredCourseList==null)
                return;

            System.out.println("Enter the Course Code : ");
            String courseCode = sc.next();

            try
            {
                registrationInterface.dropCourse(courseCode, studentId,registeredCourseList);
                System.out.println("You have successfully dropped Course : " + courseCode);

            }
            catch (Exception e)
            {

                System.out.println(e.getMessage());
            }
        }
        else
        {
            System.out.println("Please complete registration");
        }
    }

    /**
     * Method to view courses
     * @param studentId
     * @return list of courses
     */
    private List<Course> viewCourse(String studentId)
    {
        List<Course> course_available=null;
        try
        {
            course_available = registrationInterface.viewCourses(studentId);
        }
        catch (Exception e)
        {

            System.out.println(e.getMessage());
        }

        try {
            if (course_available.isEmpty()) {
                System.out.println("NO COURSE AVAILABLE");
                return null;
            }
        } catch (NullPointerException e) {
            System.out.println("NO COURSE AVAILABLE");
        }


        System.out.println(ColourConstant.ANSI_BLUE+String.format("%-20s %-20s %-20s %-20s","COURSE CODE", "COURSE NAME", "INSTRUCTOR", "SEATS")+ColourConstant.ANSI_RESET);
        try {
            for (Course obj : course_available) {
                System.out.println(String.format("%-20s %-20s %-20s %-20s", obj.getCourseCode(), obj.getCourseName(), obj.getInstructorId(), obj.getSeats()));
            }
        } catch (NullPointerException e) {
            System.out.println("NO COURSE AVAILABLE");
        }

        return course_available;
    }

    /**
     * Method to view registered courses
     * @param studentId
     * @return list of courses
     */
    private List<Course> viewRegisteredCourse(String studentId)
    {
        List<Course> course_registered=null;
        try
        {
            course_registered = registrationInterface.viewRegisteredCourses(studentId);
        }
        catch (Exception e)
        {

            System.out.println(e.getMessage());
        }

        if(course_registered.isEmpty())
        {
            System.out.println("You haven't registered for any course");
            return null;
        }

        System.out.println(String.format("%-20s %-20s %-20s","COURSE CODE", "COURSE NAME", "INSTRUCTOR"));

        for(Course obj : course_registered)
        {
            System.out.println(String.format("%-20s %-20s %-20s",obj.getCourseCode(), obj.getCourseName(),obj.getInstructorId()));
        }

        return course_registered;
    }

    /**
     * Method to view grade card
     * @param studentId
     */
    private void viewGradeCard(String studentId)
    {
        StudentDAO studentDAO=StudentDAOImpl.getInstance();
        Boolean isGradeCardApproved=studentDAO.checkIsGradeCard(studentId);
        if(!isGradeCardApproved)
        {
            System.out.println("Grade Card is not approved. Please contact the Admin.");
            return;
        }
        List<StudentGrade> grade_card=null;
        try
        {
            grade_card = registrationInterface.viewGradeCard(studentId);
        }
        catch (Exception e)
        {

            System.out.println(e.getMessage());
        }

        System.out.println(ColourConstant.ANSI_BLUE+String.format("%-20s %-20s %-20s","COURSE CODE", "COURSE NAME", "GRADE")+ColourConstant.ANSI_RESET);

        if(grade_card.isEmpty())
        {
            System.out.println("You haven't registered for any course");
            return;
        }

        for(StudentGrade obj : grade_card)
        {
            System.out.println(String.format("%-20s %-20s %-20s",obj.getCourseID(), obj.getCourseName(),obj.getGrade()));
        }
    }

    /**
     * Method to get login status
     * @param studentId
     * @return boolean
     */
    private boolean getLoginStatus(String studentId)
    {
        try
        {
            return registrationInterface.getLoginStatus(studentId);
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }
        return false;
    }

    /**
     * method to make payment
     * @param studentId
     */
    private void make_payment(String studentId) {
        double fee =0.0;
        try
        {
            fee=registrationInterface.calculateFee(studentId);
        }
        catch (Exception e)
        {

            System.out.println(e.getMessage());
        }
        StudentDAO studentDAO=StudentDAOImpl.getInstance();
        boolean is_paid=studentDAO.checkIsPaid(studentId);

        if(fee == 0.0)
        {
            System.out.println("You have not  registered for any courses yet");
            return;
        }
        else if(is_paid)
        {
            System.out.println("You have already paid the fee");
            return;
        }
        else
        {

            System.out.println("Your total fee  = " + fee);
            System.out.println("Want to continue Fee Payment(y/n)");
            String ch = "";
            try {
                ch = sc.next();
            }
            catch (InputMismatchException e) {
                System.out.println("Enter y/n");
            }

            int refId=rand.nextInt(Integer.SIZE - 1);
            int notifId=rand.nextInt(Integer.SIZE-1);

            if(ch.equals("y"))
            {
                System.out.println("Select Mode of Payment:");

                int index = 1;
                for(ModeOfPaymentConstant mode : ModeOfPaymentConstant.values())
                {
                    System.out.println(index + " " + mode);
                    index = index + 1;
                }
                int c=sc.nextInt();
                ModeOfPaymentConstant mode = ModeOfPaymentConstant.getModeofPayment(c);
                PaymentDAO paymentDAO= PaymentDAOImpl.getInstance();
                if(c==1)
                {
                    System.out.println("Enter type of Card(Debit/Credit)");
                    String type=sc.next();
                    System.out.println(type);
                    if((!type.equalsIgnoreCase("Debit")) &&(!type.equalsIgnoreCase("Credit")))
                    {
                        System.out.println("Enter valid card type, either debit or credit");
                        return;
                    }
                    System.out.println("Enter Card number: ");
                    String cardno = sc.next();
                    while(cardno.length()!=16){
                        System.out.println("Incorrect Card Number!");
                        System.out.println("Enter valid card number");
                        cardno = sc.next();
                    }
                    System.out.println("Enter cvv");
                    int cvv = sc.nextInt();
                    while(cvv<99 || cvv>1000) {
                        System.out.println("Incorrect CVV!");
                        System.out.println("Enter valid CVV");
                        cvv = sc.nextInt();
                    }

                    System.out.println("Enter name of the bank");
                    String bank=sc.next();


                    while(true)
                    {

                        try{
                            System.out.println("Enter expiry in format MM/yy");
                            String input = sc.next();
                            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM/yy");
                            simpleDateFormat.setLenient(false);
                            Date expiry = simpleDateFormat.parse(input);
                            boolean expired = expiry.before(new Date());
                            if(expired) {
                                System.out.println("Card is expired");
                                return;
                            }
                            paymentDAO.addPayment(refId,studentId,fee,"CARD",bank);
                            CardDAO cardDAO= CardDAOImpl.getInstance();
                            cardDAO.addCard(refId,cardno,type,cvv,expiry);
                            notificationInterface.sendNotification(refId,notifId);
                            break;
                        }
                        catch(ParseException parseException){
                            System.out.println(ColourConstant.ANSI_YELLOW + parseException.getMessage() + ColourConstant.ANSI_RESET);
                        }
                    }

                }
                else if(c == 2)
                {
                    System.out.println("Enter cheque number:");
                    String chequeNo = sc.next();
                    System.out.println("Enter name of the bank:");
                    String bank=sc.next();
                    paymentDAO.addPayment(refId, studentId,fee,"CHEQUE",bank);
                    ChequeDAO chequeDAO = ChequeDAOImpl.getInstance();
                    chequeDAO.addCheque(refId,chequeNo);
                    notificationInterface.sendNotification(refId,notifId);

                }
                else if(c == 3)
                {
                    System.out.println("Enter UPI ID:");
                    String upiID = sc.next();
                    System.out.println("Enter service provider:");
                    String service = sc.next();
                    System.out.println("Enter name of the bank:");
                    String bank=sc.next();
                    paymentDAO.addPayment(refId,studentId,fee,"UPI",bank);
                    UpiDAOImpl upiDAO = UpiDAOImpl.getInstance();
                    upiDAO.addUPI(refId,upiID,service);
                    notificationInterface.sendNotification(refId,notifId);
                }
                else if(c==4) {
                    System.out.println("Enter Account number: ");
                    int accountno=sc.nextInt();
                    System.out.println("Enter IFSC Code");
                    String ifsc=sc.next();
                    System.out.println("Enter name of the bank");
                    String bank=sc.next();
                    paymentDAO.addPayment(refId,studentId,fee,"NET_BANKING",bank);
                    NetBankingDAO netBankingDAO= NetBankingDAOImpl.getInstance();
                    netBankingDAO.addTransaction(refId,accountno,ifsc);
                    notificationInterface.sendNotification( refId,notifId);

                } else if (c==5) {
                    System.out.println("Please pay in cash");
                    paymentDAO.addPayment(refId,studentId,fee,"CASH","NA");
                    notificationInterface.sendNotification(refId,notifId);
                }
                paymentDAO.isPaid(studentId);
            }

        }
    }
}
