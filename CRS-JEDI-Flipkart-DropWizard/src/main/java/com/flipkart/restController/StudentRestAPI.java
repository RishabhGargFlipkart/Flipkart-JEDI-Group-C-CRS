package com.flipkart.restController;

import java.sql.SQLException;
import java.util.*;
import java.util.Random;
import javax.validation.ValidationException;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import com.flipkart.dao.StudentDAO;
import com.flipkart.dao.StudentDAOImpl;
import com.flipkart.service.*;
import org.apache.log4j.Logger;

import com.flipkart.bean.Course;
import com.flipkart.bean.Notification;
import com.flipkart.bean.StudentGrade;
import com.flipkart.constant.ModeOfPaymentConstant;
import com.flipkart.exception.CourseAlreadyRegisteredException;
import com.flipkart.exception.CourseLimitExceedException;
import com.flipkart.exception.CourseNotFoundException;
import com.flipkart.exception.SeatNotAvailableException;
import com.flipkart.validator.StudentValidator;


//System.out.println("*****************************");
//        System.out.println("**********Student Menu*********");
//        System.out.println("*****************************");
//        System.out.println("1. Course Registration");
//        System.out.println("2. Add Course");
//        System.out.println("3. Drop Course");
//        System.out.println("4. View Course");
//        System.out.println("5. View Registered Courses");
//        System.out.println("6. View grade card");
//        System.out.println("7. Make Payment");
//        System.out.println("8. Logout");
//        System.out.println("*****************************");

@Path("/student")
public class StudentRestAPI {

    RegistrationService registrationInterface = RegistrationServiceOperation.getInstance();
    ProfessorService professorInterface = new ProfessorServiceOperation();

    Random rand=new Random();

    private static Logger logger = Logger.getLogger(StudentRestAPI.class);

    /**
     * Method to handle API request for course registration
     * @param studentId
     * @throws ValidationException
     * @return
     */

    @POST
    @Path("/registerCourses")
    @Consumes("application/json")
    @Produces(MediaType.APPLICATION_JSON)
    public Response registerCourses(

                                    @NotNull
                                    @QueryParam("studentId") String studentId,
                                    @NotNull
                                    @QueryParam("courseCode") String courseCode)	throws ValidationException{
            boolean is_registered= getRegistrationStatus(studentId);
            if(is_registered)
                return Response.status(200).entity("Student "+ studentId+" is already registered.").build();
            int count = 0;
            while(count < 6)
            {
                try
                {
                    List<Course> courseList=viewCourse(studentId);

                    if(courseList==null)
                        return Response.status(200).entity("No courses to register").build();

//                    System.out.println("Enter Course Code : " + (count+1));
//                    String courseCode = sc.next();

                    if(registrationInterface.addCourse(courseCode,studentId,courseList))
                    {
                        logger.info("Course "+courseCode+" registered successfully");
                        count++;
                    }
                    else
                    {
                        logger.info(" You have already registered for Course : " + courseCode);
                    }
                }
                catch(Exception e)
                {
                    return Response.status(200).entity(e.getMessage()).build();
                }
            }
            logger.info("6 courses have been successfully registered");
            logger.info("No more courses can be registered");
            is_registered = true;

            try
            {
                registrationInterface.setRegistrationStatus(studentId);
            }
            catch (Exception e)
            {
                System.out.println(e.getMessage());
            }

        return Response.status(201).entity( "Registration Successful").build();

    }

    /**
     * Handles api request to add a course
     * @param courseCode
     * @param studentId
     * @return
     * @throws ValidationException
     */
    @PUT
    @Path("/addCourse")
    @Produces(MediaType.APPLICATION_JSON)
    public Response addCourse(
            @NotNull
            @Size(max = 10 , message = "Course Code length should be between less than 10 character")
            @QueryParam("courseCode") String courseCode,
            @NotNull
            @QueryParam("studentId") String studentId) throws ValidationException {

                boolean is_registered= getRegistrationStatus(studentId);
                if (is_registered) {
                List<Course> availableCourseList = viewCourse(studentId);

                if (availableCourseList == null)
                    return Response.status(200).entity("No courses to register").build();

                try {
//                    System.out.println("Enter Course Code : ");
//                    String courseCode = sc.next();
                    if (registrationInterface.addCourse(courseCode, studentId, availableCourseList)) {
                        return Response.status(200).entity(" You have successfully registered for Course : " + courseCode).build();
                    } else {
                        return Response.status(200).entity(" You have already registered for Course : " + courseCode).build();
                    }
                } catch (Exception e) {
                    return Response.status(200).entity(e.getMessage()).build();
                }
            } else {
                    return Response.status(200).entity("Please complete registration").build();
            }
    }

    @GET
    @Path("/getRegistrationStatus")
    public boolean getRegistrationStatus(String studentId) {
        try
        {
            return registrationInterface.getRegistrationStatus(studentId);
        }
        catch (Exception e)
        {
           logger.info(e.getMessage());
        }
        return false;
    }


    /**
     * Handles API request to drop a course
     * @param courseCode
     * @param studentId
     * @return
     * @throws ValidationException
     */
    @DELETE
    @Path("/dropCourse")
    @Produces(MediaType.APPLICATION_JSON)
    public Response dropCourse(
            @NotNull
            @Size(max = 10 , message = "Course Code length should be between less than 10 character")
            @QueryParam("courseCode") String courseCode,
            @NotNull
            @QueryParam("studentId") String studentId) throws ValidationException{
        boolean is_registered= getRegistrationStatus(studentId);
        if(is_registered)
        {
            List<Course> registeredCourseList=viewRegisteredCourse(studentId);

            if(registeredCourseList==null)
                return Response.status(200).entity("No courses registered").build();

//            System.out.println("Enter the Course Code : ");
//            String courseCode = sc.next();

            try
            {
                registrationInterface.dropCourse(courseCode, studentId,registeredCourseList);
                return Response.status(200).entity("You have successfully dropped Course : " + courseCode).build();

            }
            catch (Exception e)
            {
                return Response.status(200).entity(e.getMessage()).build();
            }
        }
        else
        {
            return Response.status(200).entity("Please complete registration").build();
        }

    }


    /**
     * Method handles API request to view the list of available courses for a student
     * @param studentId
     * @return
     * @throws ValidationException
     */
    @GET
    @Path("/viewCourse")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Course> viewCourse(
            @NotNull
            @QueryParam("studentId") String studentId) throws ValidationException{

        List<Course> course_available=null;
        try
        {
            course_available = registrationInterface.viewCourses(studentId);
        }
        catch (Exception e)
        {
            logger.info(e.getMessage());
            return null;
        }
//
//        try {
//            if (course_available.isEmpty()) {
//                logger.info("NO COURSE AVAILABLE");
//                return null;
//            }
//        } catch (NullPointerException e) {
//            logger.info("NO COURSE AVAILABLE");
//        }
//
//
////        logger.info(ColourConstant.ANSI_BLUE+String.format("%-20s %-20s %-20s %-20s","COURSE CODE", "COURSE NAME", "INSTRUCTOR", "SEATS")+ColourConstant.ANSI_RESET);
//        try {
//            for (Course obj : course_available) {
//                System.out.println(String.format("%-20s %-20s %-20s %-20s", obj.getCourseCode(), obj.getCourseName(), obj.getInstructorId(), obj.getSeats()));
//            }
//        } catch (NullPointerException e) {
//            System.out.println("NO COURSE AVAILABLE");
//        }

        return course_available;
    }

    /**
     * Method handles API request to view the list of registered courses for a student
     * @param studentId
     * @return
     * @throws ValidationException
     */
    @GET
    @Path("/viewRegisteredCourses")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Course> viewRegisteredCourse(
            @NotNull
            @QueryParam("studentId") String studentId) throws ValidationException{

        try
        {
            return registrationInterface.viewRegisteredCourses(studentId);
        } catch (SQLException e) {
            logger.info(e.getMessage());
        }

        return null;
    }



    /**
     * Method handles request to display the grade card for student
     * @param studentId
     * @return
     * @throws ValidationException
     */

    @GET
    @Path("/viewGradeCard")
    @Produces(MediaType.APPLICATION_JSON)
    public List<StudentGrade> viewGradeCard(
            @NotNull
            @QueryParam("studentId") String studentId) throws ValidationException{

        StudentDAO studentDAO=StudentDAOImpl.getInstance();
        Boolean isGradeCardApproved=studentDAO.checkIsGradeCard(studentId);
        if(!isGradeCardApproved)
        {
            logger.info("Grade Card is not approved. Please contact the Admin.");
            return null;
        }
        List<StudentGrade> grade_card = new ArrayList<StudentGrade>();
        try {
            grade_card = registrationInterface.viewGradeCard(studentId);
            return grade_card;
        }
        catch (SQLException e) {
            return grade_card;
        }

    }

    @GET
    @Path("/getLoginStatus")
    public boolean getLoginStatus(String studentId) {
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
     * Method handles API request to make payment for registered courses
     * @param studentId
     * @param paymentMode
     * @return
     * @throws ValidationException
     */

//    @POST
//    @Path("/make_payment")
//    @Produces(MediaType.APPLICATION_JSON)
//    public Response make_payment(
//            @NotNull
//            @QueryParam("studentId") String studentId ,
//            @NotNull
//            @Min( value = 1)
//            @Max( value = 3)
//            @QueryParam("paymentMode") int paymentMode) throws ValidationException{
//
//        try {
//
//            if (registrationInterface.getRegistrationStatus(studentId) == false)
//                return Response.status(200).entity("Student course registration is pending").build();
//
//            double fee = registrationInterface.calculateFee(studentId);
//
//            fee = registrationInterface.calculateFee(studentId);
//            logger.info("Your total fee  = " + fee);
//            ModeOfPaymentConstant mode = ModeOfPaymentConstant.getModeofPayment(paymentMode);
//
//
////            Notification notify = registrationInterface.calculateFee(studentId);
//        } catch (SQLException e) {
//
//        }
//
//
//        logger.info("Your Payment is successful");
////        logger.info("Your transaction id : " + notify.getReferenceId());
//
//        return Response.status(201).entity("Your total fee  = " + fee+"\n"+"Your Payment is successful\n"+"Your transaction id : " + notify.getReferenceId()).build();
//
//    }
    @POST
    @Path("/make_payment_via_card")
    @Produces(MediaType.APPLICATION_JSON)
    public Response make_payment_via_card(@NotNull @QueryParam("studentId") String studentId, @NotNull @QueryParam("cardNo") String cardNo,
                                          @NotNull @QueryParam("cvv") int cvv, @NotNull @QueryParam("date") Date date) {

        double fee =0.0;
        try
        {
            fee=registrationInterface.calculateFee(studentId);
        }
        catch (Exception e)
        {

            logger.info(e.getMessage());
        }
        StudentDAO studentDAO=StudentDAOImpl.getInstance();
        boolean is_paid=studentDAO.checkIsPaid(studentId);

        if(fee == 0.0)
        {
            return Response.status(200).entity("You have not  registered for any courses yet").build();

        }
        else if(is_paid)
        {
            return Response.status(200).entity("You have already paid the fee").build();
        }
        else {


            int refId = rand.nextInt(Integer.SIZE - 1);
            int notifId = rand.nextInt(Integer.SIZE - 1);

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

    }

    @POST
    @Path("/make_payment_via_upi")
    @Produces(MediaType.APPLICATION_JSON)
    public Response make_payment_via_upi(@NotNull @QueryParam("studentId") String studentId) {
        double fee =0.0;
        try
        {
            fee=registrationInterface.calculateFee(studentId);
        }
        catch (Exception e)
        {

            logger.info(e.getMessage());
        }
        StudentDAO studentDAO=StudentDAOImpl.getInstance();
        boolean is_paid=studentDAO.checkIsPaid(studentId);

        if(fee == 0.0)
        {
            return Response.status(200).entity("You have not  registered for any courses yet").build();

        }
        else if(is_paid)
        {
            return Response.status(200).entity("You have already paid the fee").build();
        }
        else {


            int refId = rand.nextInt(Integer.SIZE - 1);
            int notifId = rand.nextInt(Integer.SIZE - 1);




        }
    }

    @POST
    @Path("/make_payment_via_net_banking")
    @Produces(MediaType.APPLICATION_JSON)
    public Response make_payment_via_net_banking(@NotNull @QueryParam("studentId") String studentId) {
        double fee =0.0;
        try
        {
            fee=registrationInterface.calculateFee(studentId);
        }
        catch (Exception e)
        {

            logger.info(e.getMessage());
        }
        StudentDAO studentDAO=StudentDAOImpl.getInstance();
        boolean is_paid=studentDAO.checkIsPaid(studentId);

        if(fee == 0.0)
        {
            return Response.status(200).entity("You have not  registered for any courses yet").build();

        }
        else if(is_paid)
        {
            return Response.status(200).entity("You have already paid the fee").build();
        }
        else {


            int refId = rand.nextInt(Integer.SIZE - 1);
            int notifId = rand.nextInt(Integer.SIZE - 1);




        }
    }

    @POST
    @Path("/make_payment_via_cheque")
    @Produces(MediaType.APPLICATION_JSON)
    public Response make_payment_via_cheque(@NotNull @QueryParam("studentId") String studentId) {
        double fee =0.0;
        try
        {
            fee=registrationInterface.calculateFee(studentId);
        }
        catch (Exception e)
        {

            logger.info(e.getMessage());
        }
        StudentDAO studentDAO=StudentDAOImpl.getInstance();
        boolean is_paid=studentDAO.checkIsPaid(studentId);

        if(fee == 0.0)
        {
            return Response.status(200).entity("You have not  registered for any courses yet").build();

        }
        else if(is_paid)
        {
            return Response.status(200).entity("You have already paid the fee").build();
        }
        else {


            int refId = rand.nextInt(Integer.SIZE - 1);
            int notifId = rand.nextInt(Integer.SIZE - 1);




        }
    }

    @POST
    @Path("/make_payment_via_cash")
    @Produces(MediaType.APPLICATION_JSON)
    public Response make_payment_via_cash(@NotNull @QueryParam("studentId") String studentId) {
        double fee =0.0;
        try
        {
            fee=registrationInterface.calculateFee(studentId);
        }
        catch (Exception e)
        {

            logger.info(e.getMessage());
        }
        StudentDAO studentDAO=StudentDAOImpl.getInstance();
        boolean is_paid=studentDAO.checkIsPaid(studentId);

        if(fee == 0.0)
        {
            return Response.status(200).entity("You have not  registered for any courses yet").build();

        }
        else if(is_paid)
        {
            return Response.status(200).entity("You have already paid the fee").build();
        }
        else {


            int refId = rand.nextInt(Integer.SIZE - 1);
            int notifId = rand.nextInt(Integer.SIZE - 1);




        }
    }
}