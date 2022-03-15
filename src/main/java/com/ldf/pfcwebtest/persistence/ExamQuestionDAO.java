package com.ldf.pfcwebtest.persistence;

import com.ldf.pfcwebtest.model.ExamQuestion;

public class ExamQuestionDAO extends DAO<ExamQuestion> {

    public ExamQuestionDAO() {
        this.setModelClass(ExamQuestion.class);
    }
   
}
