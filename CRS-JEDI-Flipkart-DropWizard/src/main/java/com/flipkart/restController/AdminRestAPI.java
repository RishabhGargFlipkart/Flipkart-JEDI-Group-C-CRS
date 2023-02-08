/**
 *
 */
package com.flipkart.restController;

import java.util.List;

import javax.validation.Valid;
import javax.validation.ValidationException;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.flipkart.service.AdminService;
import com.flipkart.service.AdminServiceOperation;
import org.hibernate.validator.constraints.Email;

import com.flipkart.bean.Course;
import com.flipkart.bean.Professor;
import com.flipkart.bean.Student;
import com.flipkart.exception.CourseFoundException;
import com.flipkart.exception.CourseNotDeletedException;
import com.flipkart.exception.CourseNotFoundException;
import com.flipkart.exception.UserIdAlreadyInUseException;
import com.flipkart.exception.ProfessorNotAddedException;
import com.flipkart.exception.StudentNotFoundForApprovalException;
import com.flipkart.exception.UserNotFoundException;


/**
 * @author JEDI-03
 */
@Path("/admin")
public class AdminRestAPI {

    AdminService adminOperation = AdminServiceOperation.getInstance();

    /**
     * /admin/addCourse
     * REST-service for adding a new course in catalog
     * @param course
     * @return
     */
    @POST
    @Path("/addCourse")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response addCourse(@Valid Course course) throws ValidationException{
        List<Course> courseList = adminOperation.viewCourses();

        try {

            adminOperation.addCourse(course, courseList);
            return Response.status(201).entity("Course with courseCode: " + course.getCourseCode() + " added to catalog").build();

        } catch (CourseFoundException e) {

            return Response.status(409).entity(e.getMessage()).build();

        }

    }

    /**
     * /admin/deleteCourse
     * REST-services for dropping a course from catalog
     * @param courseCode
     * @return
     */
    @PUT
    @Path("/deleteCourse")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteCourse(
            @Size(max = 10 , message = "Course Code length should be between less than 10 character")
            @NotNull
            @QueryParam("courseCode") String courseCode) throws ValidationException{
        List<Course> courseList = adminOperation.viewCourses();

        try {

            adminOperation.deleteCourse(courseCode, courseList);
            return Response.status(201).entity("Course with courseCode: " + courseCode + " deleted from catalog").build();

        } catch (CourseNotFoundException | CourseNotDeletedException e) {

            return Response.status(409).entity(e.getMessage()).build();

        }
    }

    /**
     * /admin/addProfessor
     * REST-service for addding a new professor
     * @param professor
     * @return
     */
    @POST
    @Path("/addProfessor")
    @Consumes("application/json")
    @Produces(MediaType.APPLICATION_JSON)
    public Response addProfessor(@Valid Professor professor) throws ValidationException{

        try {
            adminOperation.addProfessor(professor);
            return Response.status(201).entity("Professor with professorId: " + professor.getUserId() + " added").build();
        } catch (ProfessorNotAddedException | UserIdAlreadyInUseException e) {
            return Response.status(409).entity(e.getMessage()).build();
        }
    }

    /**
     *
     * @return list of professors in the system
     */
    @GET
    @Path("/viewProfessors")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Professor> viewProfessors() {

        return adminOperation.viewProfessors();
    }


    /**
     * /admin/viewCourses
     * REST-service for getting courses in the catalog
     * @return
     */
    @GET
    @Path("/viewCourses")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Course> viewCoursesInCatalogue() {

        return adminOperation.viewCourses();

    }

    /**
     * /admin/approveStudent
     * REST-service for approving the student admission
     * @param studentId
     * @return
     */
    @PUT
    @Path("/approveStudent")
    @Produces(MediaType.APPLICATION_JSON)
    public Response approveStudent(
            @NotNull
            @QueryParam("studentId") String studentId) throws ValidationException{
        List<Student> studentList = adminOperation.viewPendingAdmission();

        try {
            adminOperation.approveStudent(studentId, studentList);
            return Response.status(201).entity("Student with studentId: " + studentId + " approved").build();
        } catch (StudentNotFoundForApprovalException e) {
            return Response.status(409).entity(e.getMessage()).build();
        }
    }

    /**
     * /admin/approveCourseRegistration
     * REST-service for approving the student admission
     * @param studentId
     * @return
     */
    @PUT
    @Path("/approveCourseRegistration")
    @Produces(MediaType.APPLICATION_JSON)
    public Response approveCourseRegistration(
            @NotNull
            @QueryParam("studentId") String studentId) throws ValidationException{
        List<Student> studentList = adminOperation.viewPendingRegistration();

        try {
            adminOperation.approveRegistration(studentId, studentList);
            return Response.status(201).entity("Student with studentId: " + studentId + " can now register for courses!").build();
        } catch (StudentNotFoundForApprovalException e) {
            return Response.status(409).entity(e.getMessage()).build();
        }
    }

    /**
     * /admin/generateReportCard
     * REST-service for approving the student admission
     * @param studentId
     * @return
     */
    @PUT
    @Path("/generateReportCard")
    @Produces(MediaType.APPLICATION_JSON)
    public Response generateReportCard(
            @NotNull
            @QueryParam("studentId") String studentId) throws ValidationException{
        List<Student> studentList = adminOperation.viewPendingGradeCard();

        try {
            adminOperation.approveGradeCard(studentId, studentList);
            return Response.status(201).entity("Report card of student with studentId: " + studentId + " has been generated.").build();
        } catch (StudentNotFoundForApprovalException e) {
            return Response.status(409).entity(e.getMessage()).build();
        }
    }


    /**
     * /admin/viewPendingAdmissions
     * REST-service for getting all pending-approval of students
     * @return
     */
    @GET
    @Path("/viewPendingAdmissions")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Student> viewPendingAdmissions() {
        return adminOperation.viewPendingAdmission();
    }

    /**
     * /admin/viewPendingRegistration
     * REST-service for getting all pending-approval of students
     * @return
     */
    @GET
    @Path("/viewPendingRegistration")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Student> viewPendingRegistration() {
        return adminOperation.viewPendingRegistration();
    }

    /**
     * /admin/viewPendingGradeCard
     * REST-service for getting all pending-approval of students
     * @return
     */
    @GET
    @Path("/viewPendingGradeCard")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Student> viewPendingGradeCard() {
        return adminOperation.viewPendingGradeCard();
    }
}
	