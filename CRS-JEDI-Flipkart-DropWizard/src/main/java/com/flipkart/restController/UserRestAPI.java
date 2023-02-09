/**
 *
 */
package com.flipkart.restController;

import javax.validation.Valid;
import javax.validation.ValidationException;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.hibernate.validator.constraints.Email;

import com.flipkart.bean.Student;
import com.flipkart.exception.UserNotFoundException;
import com.flipkart.service.StudentService;
import com.flipkart.service.StudentServiceOperation;
import com.flipkart.service.UserService;
import com.flipkart.service.UserServiceOperation;

/**
 * @author dilpreetkaur
 *
 */

@Path("/user")
public class UserRestAPI {
    StudentService StudentService=StudentServiceOperation.getInstance();
    UserService UserService =new UserServiceOperation();


    /**
     *
     * @param userId: email address of the user
     * @param newPassword: new password to be stored in db.
     * @return @return 201, if password is updated, else 500 in case of error
     */
    @PUT
    @Path("/updatePassword")
    public Response updatePassword(
            @NotNull
            @QueryParam("userId") String userId,
            @NotNull
            @Size(min = 4 , max = 20 , message = "Password length should be between 4 and 20 characters")
            @QueryParam("newPassword") String newPassword) throws ValidationException {

        if(UserService.changePassword(userId, newPassword))
        {
            return Response.status(201).entity("Password updated successfully! ").build();
        }
        else
        {
            return Response.status(500).entity("Something went wrong, please try again!").build();
        }

    }

    /**
     *
     * @param userId
     * @param password
     * @return
     */

    @POST
    @Path("/login")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response verifyCredentials(
            @NotNull
            @QueryParam("userId") String userId,
            @NotNull
            @Size(min = 4 , max = 20 , message = "Password length should be between 4 and 20 characters")
            @QueryParam("password") String password,
            @NotNull
            @QueryParam("role") String role) throws ValidationException, UserNotFoundException {


        try {
            if (!((role.equalsIgnoreCase("Student") || role.equalsIgnoreCase("Professor") || role.equalsIgnoreCase("Admin")))) {
                return Response.status(500).entity("Enter Valid Role!").build();
            }

            boolean loggedIn = UserService.verifyCredentials(userId, password);
            if (loggedIn) {
                if (role.equalsIgnoreCase("Student")) {
                    int studentId = StudentService.getStudentId(userId);
                    boolean isApproved = StudentService.isApproved(studentId);
                    if (!isApproved) {
                        return Response.status(200).entity("Login unsuccessful! Student " + userId + " has not been approved by the administration!").build();
                    }
                }
                return Response.status(200).entity("Login successful").build();
            } else {
                return Response.status(500).entity("Invalid credentials!").build();
            }
        }catch (UserNotFoundException e){
            return Response.status(500).entity(e.getMessage()).build();
        }

    }


    /**
     *
     * @param student
     * @return 201, if user is created, else 500 in case of error
     */
    @POST
    @Path("/studentRegistration")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response register(@Valid Student student)
    {

        try
        {
            StudentService.register(student.getName(), student.getUserId(), student.getPassword(), student.getGender(), student.getBatch(), student.getBranchName(), student.getAddress());
        }
        catch(Exception ex)
        {
            return Response.status(500).entity("Something went wrong! Please try again.").build();
        }


        return Response.status(201).entity("Registration Successful for "+ student.getUserId()).build();
    }


}