package com.ldf.pfcwebtest.persistence;

import com.ldf.pfcwebtest.model.Classroom;

public class ClassroomDAO extends DAO<Classroom> {

    public ClassroomDAO() {
        this.setModelClass(Classroom.class);
    }
   
}
