package com.flipkart.client;
import com.flipkart.bean.Course;
import com.flipkart.bean.Student;
import com.flipkart.exception.*;
import com.flipkart.service.AdminService;
import com.flipkart.service.AdminServiceOperation;
import com.flipkart.bean.Professor;

import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static com.flipkart.service.AdminServiceOperation.students;

public class AdminCRSMenu {

    Scanner scanner =new Scanner(System.in);
    AdminService adminOperation = AdminServiceOperation.getInstance();


    public void createMenu() throws StudentNotFoundForApprovalException, UserIdAlreadyInUseException, ProfessorNotAddedException, CourseNotDeletedException, CourseNotFoundException, CourseFoundException {
        while(true)
        {
            System.out.println("Welcome to Admin Department");
            System.out.println("Please select an option to perform:");
            System.out.println("1. add course");
            System.out.println("2. delete course");
            System.out.println("3. approve student");
            System.out.println("4. add professor");
            System.out.println("5. view courses");
            System.out.println("6. view professors");
            System.out.println("7. approve grade card");
            System.out.println("8. Exit");

            int option= scanner.nextInt();


            switch(option) {
                case 1:
                    addCourse();
                    break;
                case 2:
                    deleteCourse();
                    break;
                case 3:
                    approveStudent();
                    break;
                case 4:
                    addProfessor();
                    break;
                case 5:
                    viewCourses();
                    break;
                case 6:
                    viewProfessors();
                    break;
                case 7:
                    approveGradeCard();
                    break;
                case 8:
                    approveRegistration();
                    break;
                case 9:
                    return;
            }
        }
    }

    private void addCourse() throws CourseFoundException {
        List<Course> courseList = viewCourses();

        scanner.nextLine();
        System.out.println("Enter Course Code:");
        String courseCode = scanner.nextLine();

        System.out.println("Enter Course Name:");
        String courseName = scanner.next();

        Course course = new Course();
        course.setCourseCode(courseCode);
        course.setCourseName(courseName);
        course.setInstructorId("NA");
        course.setSeats(10);

        try {
            adminOperation.addCourse(course, courseList);
        } catch (CourseFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    private void deleteCourse() throws CourseNotDeletedException, CourseNotFoundException {
        List<Course> courseList = adminOperation.viewCourses();
        System.out.println("Enter course Id: ");
        String courseID=scanner.next();
        adminOperation.deleteCourse(courseID, courseList);

    }

    private void approveStudent() throws StudentNotFoundForApprovalException {
        List<Student> studentList= adminOperation.viewPendingAdmission();
        System.out.println("Student IDs to be approved:");
        for (Student s: studentList) {
            System.out.println(""+ s.getUserId()+"              "+ s.getName());
        }
        System.out.println("Enter Student's ID to be approved:");
        String studentUserIdApproval = scanner.next();
        adminOperation.approveStudent(studentUserIdApproval, studentList);
    }

    private List<Course> viewCourses() {
        List<Course> courseList = adminOperation.viewCourses();
        if(courseList.size() == 0) {
            System.out.println("No course in the catalogue!");
            return courseList;
        }
        System.out.println("COURSE CODE      COURSE NAME       INSTRUCTOR");
        for(Course course : courseList) {
            System.out.println(course.getCourseCode()+"      "+course.getCourseName()+"       "+course.getInstructorId());
        }
        return courseList;
    }

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

    public List<Professor> viewProfessors(){
        return adminOperation.viewProfessors();
    }

    public void approveGradeCard() throws StudentNotFoundForApprovalException {
        List<Student> studentList= adminOperation.viewPendingGradeCard();
        System.out.println("Students wanting grade card approval:");
        for (Student s: studentList) {
            System.out.println(""+ s.getUserId()+"              "+ s.getName());
        }
        System.out.println("Enter Student's ID to be generated:");
        String studentUserIdApproval = scanner.next();
        adminOperation.approveStudent(studentUserIdApproval, studentList);

    }
    public void approveRegistration() throws StudentNotFoundForApprovalException {
        List<Student> studentList= adminOperation.viewPendingRegistration();
        System.out.println("Following student's registration is pending");
        for (Student student : studentList) {
            System.out.println(""+ student.getUserId()+"              "+ student.getName());
        }
        System.out.println("Enter Student's ID to be approved:");
        String studentUserIdApproval = scanner.next();
        adminOperation.approveRegistration(studentUserIdApproval,studentList);
    }
}






