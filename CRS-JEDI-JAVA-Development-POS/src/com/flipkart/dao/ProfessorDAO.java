package com.flipkart.dao;
import com.flipkart.bean.Course;
import com.flipkart.bean.EnrolledStudent;

import java.util.List;

public interface ProfessorDAO {
    public List<Course> getCourses(String profId);

    public List<EnrolledStudent> getEnrolledStudent(String profId);

    public Boolean addGrade(String studentId,String courseCode,String grade);
    public String getProfessorById(String profId);
}
