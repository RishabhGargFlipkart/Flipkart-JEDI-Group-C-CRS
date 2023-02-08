package com.flipkart.restController;

import java.util.ArrayList;
import java.util.List;

import javax.validation.ValidationException;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.flipkart.exception.CourseNotFoundException;
import com.flipkart.exception.NoEnrolledStudentsException;
import com.flipkart.exception.ProfessorAssignedException;
import com.flipkart.service.ProfessorService;
import com.flipkart.service.ProfessorServiceOperation;
import org.hibernate.validator.constraints.Email;

import com.flipkart.bean.Course;
import com.flipkart.bean.EnrolledStudent;
import com.flipkart.validator.ProfessorValidator;

//System.out.println("\033[0;1m---------Professor Menu---------");
//        System.out.println("1. View Your Courses");
//        System.out.println("2. View Enrolled Students");
//        System.out.println("3. Add grade");
//        System.out.println("4. Assign a course");
//        System.out.println("5. Logout");
//        System.out.println("---------------------------------");

@Path("/professor")
public class ProfessorRestAPI {
    ProfessorService professorInterface= new ProfessorServiceOperation();

    @GET
    @Path("/getEnrolledStudents")
    @Produces(MediaType.APPLICATION_JSON)
    public List<EnrolledStudent> viewEnrolledStudents(
            @NotNull
            @QueryParam("profId") String profId, @QueryParam("courseCode") String courseCode) throws ValidationException {

        List<EnrolledStudent> students = new ArrayList<EnrolledStudent>();
        try {
            students = professorInterface.viewEnrolledStudents(profId, courseCode);
        } catch (NoEnrolledStudentsException | ClassNotFoundException e) {
            return null;
        }
        return students;
    }

    @GET
    @Path("/getCourses")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Course> getCourses(
            @NotNull
            @QueryParam("profId") String profId) throws ValidationException	{

        List<Course> courses=new ArrayList<Course>();
        try
        {
            courses=professorInterface.getCourses(profId);
        }
        catch(Exception ex)
        {
            return null;
        }
        return courses;

    }

    @POST
    @Path("/addGrade")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addGrade(
            @NotNull
            @QueryParam("studentId") String studentId,

            @NotNull
            @Size(max = 10 , message = "Course Code length should be between less than 10 character")
            @QueryParam("courseCode") String courseCode,

            @NotNull
            @QueryParam("profId") String profId,

            @QueryParam("grade") String grade) throws ValidationException  	{

        try
        {
            List<EnrolledStudent> enrolledStudents=new ArrayList<EnrolledStudent>();
            enrolledStudents=professorInterface.viewEnrolledStudents(profId, courseCode);
            List<Course> coursesEnrolled=new ArrayList<Course>();
            coursesEnrolled	=professorInterface.getCourses(profId);
            if(ProfessorValidator.isValidStudent(enrolledStudents, studentId) && ProfessorValidator.isValidCourse(coursesEnrolled, courseCode))
            {
                professorInterface.addGrade(profId, studentId, courseCode, grade);
            }
            else
            {
                //error code
                return Response.status(500).entity( "Something went wrong, Please Try Again ! ").build();
            }
        }
        catch(Exception ex)
        {
            return Response.status(500).entity( "Something went wrong, Please Try Again ! ").build();
        }
        return Response.status(200).entity( "Grade updated for student: "+studentId).build();

    }

    @POST
    @Path("/assignCourse")

    public Response assignCourse(
            @Size(max = 10 , message = "Course Code length should be between less than 10 character")
        @NotNull
        @QueryParam("courseCode") String courseCode,

        @NotNull
        @QueryParam("professorId") String profId) throws ValidationException {

            try {

                professorInterface.assignCourse(profId, courseCode);
                return Response.status(201).entity("courseCode: " + courseCode + " assigned to professor: " + profId).build();

            } catch (CourseNotFoundException | ProfessorAssignedException | ClassNotFoundException e) {

                return Response.status(409).entity(e.getMessage()).build();

            }
    }


}