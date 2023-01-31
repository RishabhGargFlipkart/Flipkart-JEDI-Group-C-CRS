package com.flipkart.client;
import com.flipkart.bean.Course;
import com.flipkart.bean.Student;
import com.flipkart.service.AdminServiceOperation;
import com.flipkart.bean.Professor;

import java.sql.SQLOutput;
import java.util.List;
import java.util.Scanner;
public class AdminCRSMenu {

    Scanner scanner =new Scanner(System.in);
    AdminServiceOperation adminService = new AdminServiceOperation();

    public void createMenu(){
        while(CRSApplication.loggedin)
        {
            System.out.println("Welcome to Admin Department");
            System.out.println("Please select an option to perform:");
            System.out.println("1. add course");
            System.out.println("2. delete course");
            System.out.println("3. approve student");
            System.out.println("4. add professor");
            System.out.println("5. view courses");

            int option= scanner.nextInt();


            switch(option) {
                case 1:
                    addCourse();
                case 2:
                    deleteCourse();
                case 3:
                    approveStudent();
                case 4:
                    addProfessor();
                case 5:
                    viewCourses();
            }




        }
    }

    private void addCourse()
    {
        List<Course> courseList = adminService.viewCourses(1);
        System.out.println("Enter course Id: ");
        String courseID=scanner.nextLine();
        System.out.println("Enter course name: ");
        String courseName= scanner.nextLine();
        Course course = new Course(courseID, courseName, null, 10);
        adminService.addCourse(course,courseList);

    }

    private void deleteCourse() {
        List<Course> courseList = adminService.viewCourses(1);
        System.out.println("Enter course Id: ");
        String courseID=scanner.nextLine();
        adminService.deleteCourse(courseID, courseList);

    }

    private void approveStudent() {
        List<Student> studentList= adminService.viewPendingAdmissions();
        System.out.println("Enter Student's ID:");
        int studentUserIdApproval = scanner.nextInt();
        adminService.approveStudent(studentUserIdApproval, studentList);

    }

    private List<Course> viewCourses() {
        List<Course> courseList = adminService.viewCourses(1);
        return courseList;
    }

    private void addProfessor() {

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
        adminService.addProfessor(professor);
    }


}






