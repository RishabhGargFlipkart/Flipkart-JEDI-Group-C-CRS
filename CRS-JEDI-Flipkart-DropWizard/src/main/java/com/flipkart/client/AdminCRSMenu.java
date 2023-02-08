package com.flipkart.client;
import com.flipkart.bean.Course;
import com.flipkart.bean.Student;
import com.flipkart.constant.ColourConstant;
import com.flipkart.exception.*;
import com.flipkart.service.AdminService;
import com.flipkart.service.AdminServiceOperation;
import com.flipkart.bean.Professor;

import java.util.List;
import java.util.*;
import java.util.Scanner;

/**
 * @author JEDI-Group-C Praneet, Rishabh, Akhil, Manan, Nidhi, Shivanshu, Divyansh
 * Class for Admin CRS Menu
 */
public class AdminCRSMenu {

    Scanner scanner =new Scanner(System.in);
    AdminService adminOperation = AdminServiceOperation.getInstance();


    /**
     * Method to create admin menu
     * @throws StudentNotFoundForApprovalException
     * @throws UserIdAlreadyInUseException
     * @throws ProfessorNotAddedException
     * @throws CourseNotDeletedException
     * @throws CourseNotFoundException
     * @throws CourseFoundException
     */
    public void createMenu() throws StudentNotFoundForApprovalException, UserIdAlreadyInUseException, ProfessorNotAddedException, CourseNotDeletedException, CourseNotFoundException, CourseFoundException {
        while(true)
        {
            System.out.println("\033[0;1mWelcome to Admin Department\033[0m");
            System.out.println("Please select an option to perform:");
            System.out.println("1. add course");
            System.out.println("2. delete course");
            System.out.println("3. approve student login");
            System.out.println("4. add professor");
            System.out.println("5. view courses");
            System.out.println("6. view professors");
            System.out.println("7. generate grade card");
            System.out.println("8. approve course registration");
            System.out.println("9. Exit");

            int option= scanner.nextInt();


            switch(option) {
                case 1:
                    addCourse();
                    System.out.println("---------------------------------------------------------------------------------------------------");
                    System.out.println("---------------------------------------------------------------------------------------------------");
                    break;
                case 2:
                    deleteCourse();
                    System.out.println("---------------------------------------------------------------------------------------------------");
                    System.out.println("---------------------------------------------------------------------------------------------------");
                    break;
                case 3:
                    approveStudent();
                    System.out.println("---------------------------------------------------------------------------------------------------");
                    System.out.println("---------------------------------------------------------------------------------------------------");
                    break;
                case 4:
                    addProfessor();
                    System.out.println("---------------------------------------------------------------------------------------------------");
                    System.out.println("---------------------------------------------------------------------------------------------------");
                    break;
                case 5:
                    viewCourses();
                    System.out.println("---------------------------------------------------------------------------------------------------");
                    System.out.println("---------------------------------------------------------------------------------------------------");
                    break;
                case 6:
                    viewProfessors();
                    System.out.println("---------------------------------------------------------------------------------------------------");
                    System.out.println("---------------------------------------------------------------------------------------------------");
                    break;
                case 7:
                    approveGradeCard();
                    System.out.println("---------------------------------------------------------------------------------------------------");
                    System.out.println("---------------------------------------------------------------------------------------------------");
                    break;
                case 8:
                    approveRegistration();
                    System.out.println("---------------------------------------------------------------------------------------------------");
                    System.out.println("---------------------------------------------------------------------------------------------------");
                    break;
                case 9:
                    System.out.println("Good Bye!");
                    return;
            }
        }
    }

    /**
     * Method to add course
     */
    private void addCourse() {
        List<Course> courseList = viewCourses();

        scanner.nextLine();
        System.out.println("Enter Course Code:");
        String courseCode = scanner.nextLine();

        System.out.println("Enter Course Name:");
        String courseName = scanner.next();

        System.out.println("Enter fee:");
        double fee = scanner.nextDouble();


        Course course = new Course();
        course.setCourseCode(courseCode);
        course.setCourseName(courseName);
        course.setFee(fee);
        //course.setInstructorId("NA");
        course.setSeats(10);

        try {
            adminOperation.addCourse(course, courseList);
        } catch (CourseFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Method to delete course
     */
    private void deleteCourse() {
        List<Course> courseList = adminOperation.viewCourses();
        System.out.println("Enter course Id: ");
        String courseID=scanner.next();
        try{
            adminOperation.deleteCourse(courseID, courseList);
        }catch(CourseNotFoundException | CourseNotDeletedException e){
            System.out.println(e.getMessage());
        }
        

    }

    /**
     * Method to approve student
     */
    private void approveStudent(){
        Formatter fmt1 = new Formatter();

        List<Student> studentApprovedList = adminOperation.viewCompletedAdmission();
        System.out.println("Student IDs already approved:");
        fmt1.format(ColourConstant.ANSI_BLUE+"\033[1m%15s %19s\n", "Student Id", "Name\033[0m"+ ColourConstant.ANSI_RESET);
        for (Student s: studentApprovedList) {
            fmt1.format("%14s %14s\n", s.getUserId(), s.getName());
        }
        System.out.println(fmt1);
        System.out.println("---------------------------------------------------------------------------------------------------");
        System.out.println("---------------------------------------------------------------------------------------------------");

        Formatter fmt = new Formatter();
        List<Student> studentList= adminOperation.viewPendingAdmission();
        System.out.println("Student IDs to be approved:");
        fmt.format(ColourConstant.ANSI_BLUE +"\033[1m%15s %19s\n", "Student Id", "Name\033[0m"+ ColourConstant.ANSI_RESET);
        for (Student s: studentList) {
            fmt.format("%14s %14s\n", s.getUserId(), s.getName());
        }
        System.out.println(fmt);
        System.out.println("---------------------------------------------------------------------------------------------------");
        System.out.println("---------------------------------------------------------------------------------------------------");


        if(studentList.isEmpty())
        {
            System.out.println("No pending admission.\n");
            return;
        }
        System.out.println("Enter Student's ID to be approved:");
        try{
            String studentUserIdApproval = scanner.next();
            adminOperation.approveStudent(studentUserIdApproval, studentList);
        }catch(StudentNotFoundForApprovalException e){
            System.out.println(e.getMessage());
        }

    }

    /**
     * Method to view courses
     * @return List of courses
     */
    private List<Course> viewCourses() {
        List<Course> courseList = adminOperation.viewCourses();
        if(courseList.size() == 0) {
            System.out.println("No course in the catalogue!");
            return courseList;
        }

        Formatter fmt = new Formatter();
        fmt.format(ColourConstant.ANSI_BLUE +"\033[1m%15s %15s %20s\n", "COURSE CODE", "COURSE NAME", "INSTRUCTOR ID\033[0m"+ColourConstant.ANSI_RESET);
        for (Course course: courseList) {
            fmt.format("%14s %14s %17s\n", course.getCourseCode(),course.getCourseName(),course.getInstructorId());
        }
        System.out.println(fmt);
        return courseList;
    }

    /**
     * Method to add professor
     * @throws UserIdAlreadyInUseException
     * @throws ProfessorNotAddedException
     */
    private void addProfessor() throws UserIdAlreadyInUseException, ProfessorNotAddedException {

        Professor professor = new Professor();

        System.out.println("Enter Professor Name:");
        String professorName = scanner.next();
        professor.setName(professorName);

        System.out.println("Enter Department:");
        String department = scanner.next();
        professor.setDepartment(department);

        System.out.println("Enter User Id:");
        String userId = scanner.next();
        professor.setUserId(userId);

        System.out.println("Enter Password:");
        String password = scanner.next();
        professor.setPassword(password);

        professor.setRole("Professor");

        try {
			adminOperation.addProfessor(professor);
		} catch (ProfessorNotAddedException | UserIdAlreadyInUseException e) {
			System.out.println(e.getMessage());
		}
    }

    /**
     * Method to view professors
     * @return list of professors
     */
    public List<Professor> viewProfessors(){
        return adminOperation.viewProfessors();
    }

    public void approveGradeCard(){
        List<Student> studentList= adminOperation.viewPendingGradeCard();
        System.out.println("Students wanting grade card approval:");
        Formatter fmt = new Formatter();
        fmt.format(ColourConstant.ANSI_BLUE +"\033[1m%15s %15s\n", "Student Id", "Name\033[0m"+ColourConstant.ANSI_RESET);
        for (Student s: studentList) {
            fmt.format("%14s %17s\n", s.getUserId(), s.getName());
        }
        System.out.println(fmt);
        if(studentList.isEmpty())
        {
            System.out.println("No pending Gradecard approval.\n");
            return;
        }
        System.out.println("Enter Student's ID to be generated:");
        String studentUserIdApproval = scanner.next();
        try{
            adminOperation.approveGradeCard(studentUserIdApproval, studentList);
        }catch(StudentNotFoundForApprovalException e){
            System.out.println(e.getMessage());
        }
    }

    /**
     * Method to approve registration
     * @throws StudentNotFoundForApprovalException
     */
    public void approveRegistration() throws StudentNotFoundForApprovalException {
        List<Student> studentList= adminOperation.viewPendingRegistration();
        System.out.println("Following student's registration is pending");


        Formatter fmt = new Formatter();
        fmt.format(ColourConstant.ANSI_BLUE +"\033[0;1m%14s %14s\n", "Student Id", "Name\033[0m"+ColourConstant.ANSI_RESET);
        for (Student s: studentList) {
            fmt.format("%14s %14s\n", s.getUserId(), s.getName());
        }
        System.out.println(fmt);
        if(studentList.isEmpty())
        {
            System.out.println("No pending Registrations.\n");
            return;
        }
        System.out.println("Enter Student's ID to be approved:");
        String studentUserIdApproval = scanner.next();
        try{
            adminOperation.approveRegistration(studentUserIdApproval,studentList);
        }catch(StudentNotFoundForApprovalException e){
            System.out.println(e.getMessage());
        }

    }
}






