package com.ldf.pfcwebtest.persistence;

import com.ldf.pfcwebtest.model.ExamQuestionOption;

public class ExamQuestionOptionDAO extends DAO<ExamQuestionOption> {

    public ExamQuestionOptionDAO() {
        this.setModelClass(ExamQuestionOption.class);
    }
   
}
