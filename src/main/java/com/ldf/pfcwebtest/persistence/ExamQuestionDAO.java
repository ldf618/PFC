package com.ldf.pfcwebtest.persistence;

import com.ldf.pfcwebtest.model.ExamQuestion;
import java.util.List;
import java.util.Map;

public class ExamQuestionDAO extends DAO<ExamQuestion> {

    public ExamQuestionDAO() {
        this.setModelClass(ExamQuestion.class);
    }
   
    public List<ExamQuestion> findByExam(int idExam){
        Map<String, Integer> parameters = Map.of(
                "exam.id", idExam);
        return findCustom(parameters, null, "", true);
    }
}
