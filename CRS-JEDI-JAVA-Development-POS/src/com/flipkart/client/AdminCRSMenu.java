package com.flipkart.client;
import com.flipkart.bean.Course;
import com.flipkart.bean.Student;
import com.flipkart.service.AdminServiceOperation;
import com.flipkart.bean.Professor;

import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static com.flipkart.service.AdminServiceOperation.students;

public class AdminCRSMenu {

    Scanner scanner =new Scanner(System.in);
    AdminServiceOperation adminService = new AdminServiceOperation();

    public void createMenu(String userId){
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
                    return;
            }
        }
    }

    private void addCourse()
    {
        System.out.println("List of already existing courses:");
        List<Course> courseList = adminService.viewCourses(1);
        System.out.println("Enter new course details");
        System.out.println("Enter course Id: ");
        String courseID=scanner.next();
        System.out.println("Enter course name: ");
        String courseName= scanner.next();
        Course course = new Course(courseID, courseName, null, 10);
        adminService.addCourse(course,courseList);

    }

    private void deleteCourse() {
        List<Course> courseList = adminService.viewCourses(1);
        System.out.println("Enter course Id: ");
        String courseID=scanner.next();
        adminService.deleteCourse(courseID, courseList);

    }

    private void approveStudent() {
        List<Student> studentList= adminService.viewPendingAdmission();
        System.out.println("Enter Student's ID:");
        String studentUserIdApproval = scanner.next();
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

    public void viewProfessors(){
        List<Professor> professorList = new ArrayList<Professor>();
        professorList = adminService.viewProfessors();
    }

    public void approveGradeCard(){
        System.out.println("Students whose grade card has to be approved");
        List<Student> studentList = AdminServiceOperation.students;
        System.out.println("Student Id             Name");
        for(Student student: studentList){
            if(!student.isGradeCardApproved()) {
                System.out.println(""+ student.getStudentId()+"              "+ student.getName());
            }
        }
        System.out.println("Enter studentId whose grade card you want to approve");
        String studentId = scanner.next();
        adminService.approveGradeCard(studentId);
    }

}






