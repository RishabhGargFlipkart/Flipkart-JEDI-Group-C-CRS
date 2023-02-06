package com.flipkart.service;
import com.flipkart.bean.*;
import com.flipkart.constant.Grade;
import com.flipkart.dao.ProfessorDAO;
import com.flipkart.dao.ProfessorDAOImpl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ProfessorServiceOperation implements ProfessorService {
    Grade g;
    ProfessorDAO professorDAO=ProfessorDAOImpl.getInstance();
    HashMap<String,List<Course>> profCourseMap=new HashMap<String,List<Course>>();
    List<EnrolledStudent> enrolledStudents=new ArrayList<EnrolledStudent>();

    List<StudentGrade> studentGrades=new ArrayList<StudentGrade>();
    List<Professor> professors = new ArrayList<>();
    List<Course>  course = new ArrayList<Course>();
    public ProfessorServiceOperation()
    {
        Course course1 = new Course();
        course1.setCourseCode("1");
        course1.setCourseName("DAA");
        course1.setInstructorId("NA");
        course1.setSeats(9);

        Course course2 = new Course();
        course2.setCourseCode("2");
        course2.setCourseName("DSA");
        course2.setInstructorId("P2");
        course2.setSeats(9);

        Course course3 = new Course();
        course3.setCourseCode("3");
        course3.setCourseName("OS");
        course3.setInstructorId("P2");
        course3.setSeats(9);

        course.add(course1);
        course.add(course2);
        course.add(course3);

//        StudentGrade studentGrade1 = new StudentGrade();
//        studentGrade1.setGrade("A");
//        studentGrade1.setStudentID("123");
//        studentGrade1.setCourseID("id1");
//        studentGrade1.setCourseName("DAA");
//
//        StudentGrade studentGrade2 = new StudentGrade();
//        studentGrade1.setGrade("B");
//        studentGrade1.setStudentID("234");
//        studentGrade1.setCourseID("id2");
//        studentGrade1.setCourseName("DSA");
//
//        StudentGrade studentGrade3 = new StudentGrade();
//        studentGrade1.setGrade("C");
//        studentGrade1.setStudentID("345");
//        studentGrade1.setCourseID("id3");
//        studentGrade1.setCourseName("OS");
//
//        studentGrades.add(studentGrade1);
//        studentGrades.add(studentGrade2);
//        studentGrades.add(studentGrade3);


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

        //Professor data


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

    public String addGrade(String profId, String studentId, String courseCode,String grade){

        List<Course> teachingCourses=getCourses(profId);
        List<EnrolledStudent> enrolledStudents1=new ArrayList<EnrolledStudent>();
        int check=0;
        for(Course c: teachingCourses)
        {
            if(c.getCourseCode().equalsIgnoreCase(courseCode))
            {
                check=1;
                break;
            }
        }
        if(check==0)
        {
            System.out.println("This course is not taught by you");
            return null;
        }

        enrolledStudents1=viewEnrolledStudents(profId,courseCode);
        check=0;
        for(EnrolledStudent es:enrolledStudents1)
        {
            if(studentId.equalsIgnoreCase(es.getStudentId())){
                check=1;
                break;
            }
        }
        if(check==0)
        {
            System.out.println("This student is not enrolled in the course.");
            return null;
        }
        StudentGrade s=new StudentGrade();
        s.setStudentID(studentId);
        s.setCourseID(courseCode);
        s.setGrade(grade);
        String c="";
        for(Course crs: course)
        {
            if(courseCode.equalsIgnoreCase(crs.getCourseCode())) {
                c = crs.getCourseName();
                break;
            }
        }
        s.setCourseName(c);
        studentGrades.add(s);
        System.out.println("Grade added");
        return null;
    }

    public List<EnrolledStudent> viewEnrolledStudents(String profId, String courseCode){

//        List<Course> teachingCourses=getCourses(profId);
//        List<EnrolledStudent> ans=new ArrayList<EnrolledStudent>();
//        int check=0;
//        for(Course c: teachingCourses)
//        {
//            if(c.getCourseCode().equalsIgnoreCase(courseCode))
//            {
//                check=1;
//                break;
//            }
//        }
//        if(check==0)
//        {
//            System.out.println("This course is not taught by you!");
//            return ans;
//        }
//
//
//        for(EnrolledStudent es:enrolledStudents)
//        {
//            if(es.getCourseCode().equalsIgnoreCase(courseCode)){
//                ans.add(es);
//            }
//
//        }
//        return ans;
        List<EnrolledStudent> enrolledStudents=new ArrayList<EnrolledStudent>();
        try
        {
            enrolledStudents=professorDAO.getEnrolledStudent(profId);
        }
        catch(Exception ex)
        {
            throw ex;
        }
        return enrolledStudents;
    }

    public List<Course> getCourses(String profId){
        List<Course> courses=new ArrayList<Course>();
        try{
            courses=professorDAO.getCourses(profId);
        }
        catch(Exception ex)
        {
            throw ex;
        }
        return courses;
    }

    public List<Professor> getProfessors(){
        return professors;
    }

    public boolean assignCourse(String profId,String courseCode)
    {
        int i=0;
        for(Course crs: course)
        {
            if(crs.getCourseCode().equalsIgnoreCase(courseCode))
            {
                if(crs.getInstructorId().equals("NA"))
                {
                    crs.setInstructorId(profId);
//                    System.out.println(crs.getInstructorId());
//                    System.out.println(i);
                    course.set(i,crs);
//                    System.out.println(course.get(0).getInstructorId());
                    List<Course> newList=profCourseMap.get(profId);
                    newList.add(crs);
                    profCourseMap.put(profId, newList);
                    return true;
                }
            }
            i++;
        }
        return false;
    }

}
