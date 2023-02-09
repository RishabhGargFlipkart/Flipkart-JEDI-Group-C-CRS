package com.flipkart.restController;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
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

import com.flipkart.dao.*;
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

import static java.lang.Math.min;


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
    NotificationService notificationInterface=NotificationServiceOperation.getInstance();
    PaymentDAO paymentDAO= PaymentDAOImpl.getInstance();
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
    public Response registerCourses(ArrayList<String>courses,
                                    @NotNull
                                    @QueryParam("studentId") String studentId
    )	throws ValidationException{
        boolean is_registered= getRegistrationStatus(studentId);
        if(is_registered)
            return Response.status(200).entity("Student "+ studentId+" is already registered.").build();
        int count = 0;
        while(count < min(6,courses.size()))
        {

            try
            {
                List<Course> courseList=viewCourse(studentId);

                if(courseList==null)
                    return Response.status(200).entity("No courses to register").build();

//                    System.out.println("Enter Course Code : " + (count+1));
//                    String courseCode = sc.next();

                if(registrationInterface.addCourse(courses.get(count),studentId,courseList))
                {
                    logger.info("Course "+courses.get(count)+" registered successfully");
                    count++;
                }
                else
                {
                    return Response.status(200).entity(" You have already registered for Course : " + courses.get(count)+" Please enter a list of courses again.").build();

                }
                int registeredCourses;
                try {
                    RegistrationDAO registrationDaoInterface = RegistrationDAOImpl.getInstance();
                    registeredCourses=registrationDaoInterface.numOfRegisteredCourses(studentId);
                }
                catch(Exception e){
                    return Response.status(200).entity(e.getMessage()).build();
                }
                if(registeredCourses==6) {
                    is_registered = true;


                    try {
                        registrationInterface.setRegistrationStatus(studentId);
                        break;
                    } catch (Exception e) {
                        logger.info(e.getMessage());
                    }
                }
            }
            catch(Exception e)
            {
                return Response.status(200).entity(e.getMessage()).build();
            }
        }
        logger.info("6 courses have been successfully registered");
        logger.info("No more courses can be registered");


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
        List<StudentGrade> grade_card = new ArrayList<StudentGrade>();
        if(!isGradeCardApproved)
        {
            logger.info("Grade Card is not approved. Please contact the Admin.");
            return grade_card;
        }

        try {
            grade_card = registrationInterface.viewGradeCard(studentId);
            for(int i=0;i<grade_card.size();i++) {
                StudentGrade st=grade_card.get(i);
                st.setStudentID(studentId);
                grade_card.set(i,st);
            }
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
    //     * @param paymentMode
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
    public Response make_payment_via_card(@NotNull @QueryParam("studentId") String studentId,@NotNull @QueryParam("type") String type, @NotNull @QueryParam("cardNo") String cardno,
                                          @NotNull @QueryParam("cvv") int cvv, @NotNull @QueryParam("date") String date,@NotNull @QueryParam("bank") String bank) {



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

                return Response.status(200).entity("Enter valid card type, either debit or credit").build();
            }
//            System.out.println("Enter Card number: ");
//            String cardno = sc.next();
            if(cardno.length()!=16){
                return Response.status(200).entity("Card number must contain 16 digits").build();
            }
//            System.out.println("Enter cvv");
//            int cvv = sc.nextInt();
            if(cvv<99 || cvv>1000) {
                return Response.status(200).entity("cvv must contain 3 digits").build();
            }

//            System.out.println("Enter name of the bank");
//            String bank=sc.next();

            try{
//                    System.out.println("Enter expiry in format MM/yy");
//                    String input = sc.next();
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM/yy");
                simpleDateFormat.setLenient(false);
                Date expiry = simpleDateFormat.parse(date);
                boolean expired = expiry.before(new Date());
                if(expired) {
                    return Response.status(200).entity("Card is expired").build();
                }
                paymentDAO.addPayment(refId,studentId,fee,"CARD",bank);
                CardDAO cardDAO= CardDAOImpl.getInstance();
                cardDAO.addCard(refId,cardno,type,cvv,expiry);
                Notification notification=notificationInterface.sendNotification(refId,notifId);
                paymentDAO.isPaid(studentId);
                LocalDate localdate=LocalDate.now();
                LocalTime time=LocalTime.now();
                return Response.status(200).entity("Payment of "+fee+" successfully done(via card) with refId: "+refId+" at "+time+" on "+localdate).build();
            }
            catch(Exception e){
                return Response.status(200).entity(e.getMessage()).build();
            }



        }

    }

    @POST
    @Path("/make_payment_via_upi")
    @Produces(MediaType.APPLICATION_JSON)
    public Response make_payment_via_upi(@NotNull @QueryParam("studentId") String studentId, @NotNull @QueryParam("upi_id")  String upiID, @NotNull @QueryParam("service_provider") String service, @NotNull @QueryParam("bank") String bank) {
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

            try{
                int refId = rand.nextInt(Integer.SIZE - 1);
                int notifId = rand.nextInt(Integer.SIZE - 1);


                paymentDAO.addPayment(refId,studentId,fee,"UPI",bank);
                UpiDAOImpl upiDAO=UpiDAOImpl.getInstance();
                upiDAO.addUPI(refId,upiID,service);
                Notification notification=notificationInterface.sendNotification(refId,notifId);
                paymentDAO.isPaid(studentId);
                LocalDate date=LocalDate.now();
                LocalTime time=LocalTime.now();
                return Response.status(200).entity("Payment of "+fee+" successfully done(via UPI) with refId: "+refId+" at "+time+" on "+date).build();}
            catch(Exception e){
                return Response.status(200).entity(e.getMessage()).build();
            }
        }

    }

    @POST
    @Path("/make_payment_via_net_banking")
    @Produces(MediaType.APPLICATION_JSON)
    public Response make_payment_via_net_banking(@NotNull @QueryParam("studentId") String studentId,@NotNull @QueryParam("AccountNumber") int accountno,
                                                 @NotNull @QueryParam("IFSCCode") String ifsc,@NotNull @QueryParam("bank") String bank) {
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

            try{
                int refId = rand.nextInt(Integer.SIZE - 1);
                int notifId = rand.nextInt(Integer.SIZE - 1);

                paymentDAO.addPayment(refId,studentId,fee,"NET_BANKING","bank");
                NetBankingDAO netBankingDAO=NetBankingDAOImpl.getInstance();
                netBankingDAO.addTransaction(refId,accountno,ifsc);
                Notification notification=notificationInterface.sendNotification(refId,notifId);
                paymentDAO.isPaid(studentId);
                LocalDate date=LocalDate.now();
                LocalTime time=LocalTime.now();
                return Response.status(200).entity("Payment of "+fee+" successfully done(via net banking) with refId: "+refId+" at "+time+" on "+date).build();
            }
            catch(Exception e){
                return Response.status(200).entity(e.getMessage()).build();
            }

        }
    }

    @POST
    @Path("/make_payment_via_cheque")
    @Produces(MediaType.APPLICATION_JSON)
    public Response make_payment_via_cheque(@NotNull @QueryParam("studentId") String studentId, @NotNull @QueryParam("chequeNo") String chequeNo,@NotNull @QueryParam("bank") String bank) {
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

            try {
                int refId = rand.nextInt(Integer.SIZE - 1);
                int notifId = rand.nextInt(Integer.SIZE - 1);
                paymentDAO.addPayment(refId,studentId,fee,"CHEQUE",bank);
                ChequeDAO chequeDAO=ChequeDAOImpl.getInstance();
                chequeDAO.addCheque(refId,chequeNo);
                Notification notification=notificationInterface.sendNotification(refId,notifId);
                paymentDAO.isPaid(studentId);
                LocalDate date=LocalDate.now();
                LocalTime time=LocalTime.now();
                return Response.status(200).entity("Payment of "+fee+" successfully done(via cheque) with refId: "+refId+" at "+time+" on "+date).build();
            }
            catch(Exception e){
                return Response.status(200).entity(e.getMessage()).build();
            }
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

            try {
                int refId = rand.nextInt(Integer.SIZE - 1);
                int notifId = rand.nextInt(Integer.SIZE - 1);
                paymentDAO.addPayment(refId,studentId,fee,"CASH","NA");
                Notification notification=notificationInterface.sendNotification(refId,notifId);
                paymentDAO.isPaid(studentId);
                LocalDate date=LocalDate.now();
                LocalTime time=LocalTime.now();
                return Response.status(200).entity("Payment of "+fee+" successfully done(with cash) with refId: "+refId+" at "+time+" on "+date).build();
            }
            catch(Exception e) {
                return Response.status(200).entity(e.getMessage()).build();
            }
        }




    }
}