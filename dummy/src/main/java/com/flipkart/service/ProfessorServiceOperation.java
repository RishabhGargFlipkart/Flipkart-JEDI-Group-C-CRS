package com.flipkart.service;
import com.flipkart.bean.*;
import com.flipkart.constant.GradeConstant;
import com.flipkart.dao.ProfessorDAO;
import com.flipkart.dao.ProfessorDAOImpl;
import com.flipkart.exception.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ProfessorServiceOperation implements ProfessorService {
    GradeConstant g;
    ProfessorDAO professorDAO = ProfessorDAOImpl.getInstance();
    HashMap<String, List<Course>> profCourseMap = new HashMap<String, List<Course>>();
    List<EnrolledStudent> enrolledStudents = new ArrayList<EnrolledStudent>();
    List<StudentGrade> studentGrades = new ArrayList<StudentGrade>();
    List<Professor> professors = new ArrayList<>();
    List<Course> course = new ArrayList<Course>();

    public ProfessorServiceOperation() {

    }

    public String addGrade(String profId, String studentId, String courseCode, String grade) throws ClassNotFoundException, StudentNotRegistered, GradeAssignedException {
        /*
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
        System.out.println("GradeConstant added");

         */
        try {
            if (professorDAO.addGrade(studentId, courseCode, grade)) {
                System.out.println("Student grade added");
                return null;
            }
            else {
                System.out.println("GradeConstant is not added. Try again with valid details.");
            }
        } catch (StudentNotRegistered | GradeAssignedException | ClassNotFoundException ex) {
            throw ex;
        }
        return null;

    }

    public List<EnrolledStudent> viewEnrolledStudents(String profId, String courseCode) throws ClassNotFoundException, NoEnrolledStudentsException {

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
            enrolledStudents=professorDAO.getEnrolledStudent(profId,courseCode);

        }
        catch(ClassNotFoundException | NoEnrolledStudentsException ex)
        {
            throw ex;
        }
        return enrolledStudents;
    }

    public List<Course> getCourses(String profId) throws NoAssignedCourseException, ClassNotFoundException {
        List<Course> courses=new ArrayList<Course>();
        try{
            courses=professorDAO.getCourses(profId);
        }
        catch(ClassNotFoundException | NoAssignedCourseException ex)
        {
            throw ex;
        }
        return courses;
    }



    public boolean assignCourse(String profId,String courseCode) throws ClassNotFoundException, CourseNotFoundException, ProfessorAssignedException {
        /*
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
        */

         try{
             return professorDAO.assignCourse(profId,courseCode);
         }
         catch (ProfessorAssignedException | CourseNotFoundException ex)
         {
             throw ex;
         }

    }

}
