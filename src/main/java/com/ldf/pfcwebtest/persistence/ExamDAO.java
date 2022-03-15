package com.ldf.pfcwebtest.persistence;

import com.ldf.pfcwebtest.model.Exam;

public class ExamDAO extends DAO<Exam> {

    public ExamDAO() {
        this.setModelClass(Exam.class);
    }
   
}
