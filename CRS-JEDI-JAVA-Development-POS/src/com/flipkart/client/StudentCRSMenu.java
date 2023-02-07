package com.flipkart.client;
import java.util.Random;
import com.flipkart.bean.Course;
import com.flipkart.dao.CardDAO;
import com.flipkart.dao.CardDAOImpl;
import com.flipkart.dao.PaymentDAO;
import com.flipkart.dao.PaymentDAOImpl;
import com.flipkart.service.ProfessorService;
import com.flipkart.service.ProfessorServiceOperation;
import com.flipkart.service.RegistrationService;
import com.flipkart.service.RegistrationServiceOperation;
import com.flipkart.constant.ModeOfPayment;
import com.flipkart.service.NotificationService;
import com.flipkart.service.NotificationServiceOperation;
import com.flipkart.constant.NotificationType;
import java.util.Scanner;
import java.util.List;
import com.flipkart.bean.StudentGrade;
public class StudentCRSMenu {
    Random rand=new Random();
    Scanner sc = new Scanner(System.in);
    RegistrationService registrationInterface = RegistrationServiceOperation.getInstance();
    ProfessorService professorInterface = new ProfessorServiceOperation();
    NotificationService notificationInterface=NotificationServiceOperation.getInstance();
    private boolean is_registered,is_loggedin;
    public void createMenu(String studentId)
    {

        int choice;
        is_loggedin = getLoginStatus(studentId);
        is_registered=getRegistrationStatus(studentId);
        if(!is_loggedin)
        {
            System.out.println("Registration is not approved. Wait for Admin approval.");
            return;
        }
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
            choice = sc.nextInt();
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
            }
        }

        System.out.println("Registration Successful");
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


        if(course_available.isEmpty())
        {
            System.out.println("NO COURSE AVAILABLE");
            return null;
        }


        System.out.println(String.format("%-20s %-20s %-20s %-20s","COURSE CODE", "COURSE NAME", "INSTRUCTOR", "SEATS"));
        for(Course obj : course_available)
        {
            System.out.println(String.format("%-20s %-20s %-20s %-20s",obj.getCourseCode(), obj.getCourseName(),obj.getInstructorId(), obj.getSeats()));
        }

        return course_available;
    }
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

    private void viewGradeCard(String studentId)
    {
        List<StudentGrade> grade_card=null;
        try
        {
            grade_card = registrationInterface.viewGradeCard(studentId);
        }
        catch (Exception e)
        {

            System.out.println(e.getMessage());
        }

        System.out.println(String.format("%-20s %-20s %-20s","COURSE CODE", "COURSE NAME", "GRADE"));

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

        if(fee == 0.0)
        {
            System.out.println("You have not  registered for any courses yet");
        }
        else
        {

            System.out.println("Your total fee  = " + fee);
            System.out.println("Want to continue Fee Payment(y/n)");
            String ch = sc.next();
            int refId=rand.nextInt(Integer.SIZE - 1);
            if(ch.equals("y"))
            {
                System.out.println("Select Mode of Payment:");

                int index = 1;
                for(ModeOfPayment mode : ModeOfPayment.values())
                {
                    System.out.println(index + " " + mode);
                    index = index + 1;
                }
                int c=sc.nextInt();
                ModeOfPayment mode = ModeOfPayment.getModeofPayment(c);


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
                    int cardno=sc.nextInt();
                    System.out.println("Enter cvv");
                    int cvv=sc.nextInt();
                    System.out.println("Enter name of the bank");
                    String bank=sc.next();

                    PaymentDAO paymentDAO= PaymentDAOImpl.getInstance();
                    paymentDAO.addPayment(refId,studentId,fee,"CARD",bank);
                    CardDAO cardDAO= CardDAOImpl.getInstance();
                    cardDAO.addCard(refId,cardno,type,cvv);
                    int notifId=rand.nextInt(Integer.SIZE-1);
                    notificationInterface.sendNotification( refId,notifId);
                    System.out.println("Payment done");



                }

//                if(mode == null)
//                    System.out.println("Invalid Input");
//                else
//                {
//                    try
//                    {
//
//                    }
//                    catch (Exception e)
//                    {
//
//                        System.out.println(e.getMessage());
//                    }
//                }

            }

        }
    }
}
