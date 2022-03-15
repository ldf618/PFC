package com.ldf.pfcwebtest.persistence;

import com.ldf.pfcwebtest.model.Course;

public class CourseDAO extends DAO<Course> {

    public CourseDAO() {
        this.setModelClass(Course.class);
    }
   
}
