package com.ldf.pfcwebtest.persistence;

import com.ldf.pfcwebtest.model.ExamQuestionOptionAnswer;

public class ExamQuestionOptionAnswerDAO extends DAO<ExamQuestionOptionAnswer> {

    public ExamQuestionOptionAnswerDAO() {
        this.setModelClass(ExamQuestionOptionAnswer.class);
    }
   
}
