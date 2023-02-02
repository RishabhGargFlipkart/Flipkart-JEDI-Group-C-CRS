package com.flipkart.client;
import com.flipkart.bean.Course;
import com.flipkart.service.ProfessorService;
import com.flipkart.service.ProfessorServiceOperation;
import com.flipkart.service.RegistrationService;
import com.flipkart.service.RegistrationServiceOperation;
import com.flipkart.constant.ModeOfPayment;
import com.flipkart.service.NotificationService;
import com.flipkart.service.NotificationServiceOperation;
import com.flipkart.constant.NotificationType;
import com.flipkart.bean.Professor;
import java.util.Scanner;
import java.util.List;
import com.flipkart.bean.StudentGrade;
import com.flipkart.data.CourseList;
public class StudentCRSMenu {
    Scanner sc=new Scanner(System.in);
    private boolean is_registered;
    RegistrationService registrationService = new RegistrationServiceOperation();
    ProfessorServiceOperation professorService = new ProfessorServiceOperation();
    NotificationService notificationService = new NotificationServiceOperation();


    public void createMenu(String studentId)
    {
        int choice;
        is_registered = getRegistrationStatus(studentId);
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
        int count = 0;
        while(count<6)
        {
            if(is_registered)
            {
                System.out.println("Registration is already completed");
                return;
            }
            List<Course> courseList = viewCourse(studentId);
            if(courseList == null)
            {
                return;
            }
            System.out.println("Enter Course Code :");
            String courseCode = sc.next();
            if(registrationService.addCourse(courseCode,studentId,courseList)) //parameters need to be added in addCourse
            {
                System.out.println("Course " + courseCode + " registered sucessfully.");
            }
            else
            {
                System.out.println(" You have already registered for Course : " + courseCode);
            }
            count++;
        }
        System.out.println("Registration Successful.");
        is_registered = true;
        registrationService.setRegistrationStatus(studentId);
    }
    private void addCourse(String studentId)
    {
        if(is_registered)
        {
            List<Course> availableCourseList=viewCourse(studentId);

            if(availableCourseList==null)
                return;


                System.out.println("Enter Course Code : " );
                String courseCode = sc.next();
                if(registrationService.addCourse(courseCode, studentId,availableCourseList))
                {
                    System.out.println(" You have successfully registered for Course : " + courseCode);
                }
                else
                {
                    System.out.println(" You have already registered for Course : " + courseCode);
                }

        }
        else
        {
            System.out.println("Please complete registration");
        }
    }
    private boolean getRegistrationStatus(String studentId)
    {
        return registrationService.getRegistrationStatus(studentId);
    }
    private void dropCourse(String studentId)
    {
        if(is_registered)
        {
            List<Course> registeredCourseList=viewRegisteredCourse(studentId);

            if(registeredCourseList==null){
                return;
            }


            System.out.println("Enter the Course Code : ");
            String courseCode = sc.next();


                registrationService.dropCourse(courseCode, studentId,registeredCourseList);
                System.out.println("You have successfully dropped Course : " + courseCode);


                System.out.println("You have not registered for course.");

        }
        else
        {
            System.out.println("Please complete registration");
        }
    }

    private List<Course> viewCourse(String studentId)
    {
        List<Course> course_available = null;
        course_available = registrationService.viewCourses();


        if(course_available.isEmpty())
        {
            System.out.println("NO COURSE AVAILABLE");
            return null;
        }
        System.out.println(String.format("%-20s %-20s %-20s %-20s","COURSE CODE", "COURSE NAME", "INSTRUCTOR", "SEATS"));
        if(course_available == null) return null;
        for(Course obj : course_available)
        {
            if(obj == null) continue;
            System.out.println(String.format("%-20s %-20s %-20s %-20s",obj.getCourseCode(), obj.getCourseName(),obj.getInstructorId(), obj.getSeats()));
        }

        return course_available;
    }
    private List<Course> viewRegisteredCourse(String studentId)
    {
        List<Course> course_registered=null;

            course_registered = registrationService.viewRegisteredCourses(studentId);





        if(course_registered.isEmpty())
        {
            System.out.println("You haven't registered for any course");
            return null;
        }

        System.out.println(String.format("%-20s %-20s %-20s","COURSE CODE", "COURSE NAME", "INSTRUCTOR"));

        for(Course obj : course_registered)
        {
            String id = obj.getInstructorId();
            Professor prof = null;
            for(Professor p: professorService.getProfessors())
            {
                if(p.getUserId() == id)
                {
                    prof = p;
                }
            }
            System.out.println(String.format("%-20s %-20s %-20s",obj.getCourseCode(), obj.getCourseName(),prof.getName()));
        }

        return course_registered;
    }

    private void viewGradeCard(String studentId)
    {
        List<StudentGrade> grade_card=null;

            grade_card = registrationService.viewGradeCard(studentId);


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
    private void make_payment(String studentId)
    {
        double fee =0.0;

            fee=registrationService.calculateFee(studentId);



        if(fee == 0.0)
        {
            System.out.println("You have not  registered for any courses yet");
        }
        else
        {

            System.out.println("Your total fee  = " + fee);
            System.out.println("Want to continue Fee Payment(y/n)");
            String ch = sc.next();
            if(ch.equals("y"))
            {
                System.out.println("Select Mode of Payment:");

                int index = 1;

                for(ModeOfPayment mode : ModeOfPayment.values())
                {
                    System.out.println(index + " " + mode);
                    index = index + 1;
                }

                ModeOfPayment mode = ModeOfPayment.getModeofPayment(sc.nextInt());

                if(mode == null)
                    System.out.println("Invalid Input");
                else
                {

                    notificationService.sendNotification(String.valueOf(NotificationType.PAYMENT), studentId, mode, fee);


                }

            }

        }
    }
}
