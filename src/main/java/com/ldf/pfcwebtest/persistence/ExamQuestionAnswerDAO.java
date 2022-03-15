package com.ldf.pfcwebtest.persistence;

import com.ldf.pfcwebtest.model.ExamQuestionAnswer;

public class ExamQuestionAnswerDAO extends DAO<ExamQuestionAnswer> {

    public ExamQuestionAnswerDAO() {
        this.setModelClass(ExamQuestionAnswer.class);
    }
   
}
