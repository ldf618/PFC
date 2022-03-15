package com.ldf.pfcwebtest.persistence;

import com.ldf.pfcwebtest.model.ExamAnswer;

public class ExamAnswerDAO extends DAO<ExamAnswer> {

    public ExamAnswerDAO() {
        this.setModelClass(ExamAnswer.class);
    }
   
}
