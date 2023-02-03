package com.flipkart.data;

import java.util.*;
import com.flipkart.bean.Professor;
public class Professors {
    public static List<Professor> professors = new ArrayList<Professor>();
    public Professors(){
        com.flipkart.bean.Professor professor1 = new com.flipkart.bean.Professor();
        professor1.setDepartment("ABC");
        professor1.setName("ABC");
        professor1.setRole("Professor");
        professor1.setUserId("P1");
        professor1.setPassword("ABC");

        com.flipkart.bean.Professor professor2 = new com.flipkart.bean.Professor();
        professor2.setDepartment("ABCD");
        professor2.setName("ABCD");
        professor2.setRole("Professor");
        professor2.setUserId("P2");
        professor2.setPassword("ABCD");

        com.flipkart.bean.Professor professor3 = new com.flipkart.bean.Professor();
        professor3.setDepartment("ABCDE");
        professor3.setName("ABCDE");
        professor3.setRole("Professor");
        professor3.setUserId("P3");
        professor3.setPassword("ABCDE");

        professors.add(professor1);
        professors.add(professor2);
        professors.add(professor3);
    }
}
