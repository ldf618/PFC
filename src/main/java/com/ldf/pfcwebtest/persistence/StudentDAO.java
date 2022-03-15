package com.ldf.pfcwebtest.persistence;

import com.ldf.pfcwebtest.model.Student;

public class StudentDAO extends DAO<Student> {

    public StudentDAO() {
        this.setModelClass(Student.class);
    }
   
}
