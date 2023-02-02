package com.flipkart.service;

import java.util.*;

import com.flipkart.bean.*;

public class AdminServiceOperation implements AdminService {

    HashMap<String,List<Course>> profCourseMap=new HashMap<String,List<Course>>();
    List<EnrolledStudent> enrolledStudents=new ArrayList<EnrolledStudent>();

    public static List<Student> students=new ArrayList<>();
    List<StudentGrade> studentGrades = new ArrayList<StudentGrade>();

    List<Professor> professorList=new ArrayList<>();
    List<Course>  course = new ArrayList<Course>();
    public AdminServiceOperation()
    {
        Professor p1=new Professor();
        p1.setUserId("P1");
        p1.setName("Praneet");
        p1.setDepartment("CS");
        p1.setRole("Professor");
        p1.setPassword("Praneet");

        Professor p2=new Professor();
        p2.setUserId("P2");
        p2.setName("P2");
        p2.setDepartment("Mech");
        p2.setRole("Professor");
        p2.setPassword("Praneet");

        Professor p3=new Professor();
        p3.setUserId("P3");
        p3.setName("P3");
        p3.setDepartment("ECE");
        p3.setRole("Professor");
        p3.setPassword("Praneet");

        professorList.add(p1);
        professorList.add(p2);
        professorList.add(p3);

        Course course1 = new Course();
        course1.setCourseCode("1");
        course1.setCourseName("DAA");
        course1.setInstructorId("P1");
        course1.setSeats(9);

        Course course2 = new Course();
        course2.setCourseCode("2");
        course2.setCourseName("DSA");
        course2.setInstructorId("P2");
        course2.setSeats(9);

        Course course3 = new Course();
        course3.setCourseCode("3");
        course3.setCourseName("OS");
        course3.setInstructorId("P3");
        course3.setSeats(9);

        course.add(course1);
        course.add(course2);
        course.add(course3);

        Student student3 = new Student();
        student3.setApproved(false);
        student3.setStudentId("123");
        student3.setBatch(2019);
        student3.setBranchName("ABC");

        Student student1 = new Student();
        student1.setApproved(true);
        student1.setStudentId("234");
        student1.setBatch(2019);
        student1.setBranchName("ABC");

        Student student2 = new Student();
        student2.setApproved(true);
        student2.setStudentId("345");
        student2.setBatch(2019);
        student2.setBranchName("ABC");

        students.add(student1);
        students.add(student2);
        students.add(student3);


        EnrolledStudent enrolledStudent1 = new EnrolledStudent();
        enrolledStudent1.setCourseCode("1");
        enrolledStudent1.setCourseName("DAA");
        enrolledStudent1.setStudentId("123");

        EnrolledStudent enrolledStudent2 = new EnrolledStudent();
        enrolledStudent2.setCourseCode("2");
        enrolledStudent2.setCourseName("DSA");
        enrolledStudent2.setStudentId("234");

        EnrolledStudent enrolledStudent3 = new EnrolledStudent();
        enrolledStudent3.setCourseCode("3");
        enrolledStudent3.setCourseName("OS");
        enrolledStudent3.setStudentId("345");

        enrolledStudents.add(enrolledStudent1);
        enrolledStudents.add(enrolledStudent2);
        enrolledStudents.add(enrolledStudent3);

        for(Course crs:course)
        {
            if(profCourseMap.containsKey(crs.getInstructorId())) {
                profCourseMap.get(crs.getInstructorId()).add(crs);
            }
            else {
                ArrayList<Course> temp=new ArrayList<>();
                temp.add(crs);
                profCourseMap.put(crs.getInstructorId(),temp);
            }
        }


    }
    public void deleteCourse(String courseID, List<Course> courseList){
        Iterator<Course> courseIterator = courseList.iterator();
        while (courseIterator.hasNext()) {
            Course course = courseIterator.next();
            if((course.getCourseCode().equals(courseID))) {
                courseIterator.remove();
                System.out.println("Course "+course.getCourseName()+" deleted.");
                return;
            }
        }
        System.out.println("Course not found");
    }
    public void addCourse(Course course, List<Course> courseList){
        if(!courseList.contains(course)){
            courseList.add(course);
            System.out.println("Added Course");
        } else {
            System.out.println("Course already added");
        }
    }

    public void approveStudent(String studentID, List<Student> studentList){
        for(Student student: studentList){
            if(student.getStudentId().equals(studentID)){
                student.setApproved(true);
                System.out.println("Student with student Id: " + studentID+ " is approved");
                return;
            }
        }
        System.out.println("Invalid Student ID");
    }

    public void addProfessor(Professor professor){
        if(professorList.contains(professor))
        {
            System.out.println("Professor already exists.");
        }
        else {
            professorList.add(professor);
            System.out.println("Professor added");
        }

    }

    public List<Course> viewCourses(int catalogID) {
        for(Course c:course)
        {
            c.display();
        }
        return course;
    }

    public List<Professor> viewProfessors() {
        for(Professor professor:professorList) {
            professor.display();
        }
        return professorList;
    }

    public List<Student> viewPendingAdmission() {
        List<Student> pendingStudents = new ArrayList<Student>();
        for(Student student:students)
        {
            if(!student.isApproved())
            {
                pendingStudents.add(student);
            }
        }
        return pendingStudents;
    }

    public static void addStudentToList(Student s){
        students.add(s);
    }
}
