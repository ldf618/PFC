package com.ldf.pfcwebtest.persistence;

import com.ldf.pfcwebtest.model.StudentOptionAnswerScore;
import com.ldf.pfcwebtest.model.StudentOptionAnswerScorePK;
import com.ldf.pfcwebtest.persistence.util.JPAUtil;
import javax.persistence.EntityManager;

public class StudentOptionAnswerScoreDAO extends DAO<StudentOptionAnswerScore> {

    public StudentOptionAnswerScoreDAO() {
        this.setModelClass(StudentOptionAnswerScore.class);
    }

    
    public final StudentOptionAnswerScore find(StudentOptionAnswerScorePK id) {

        EntityManager em = JPAUtil.beginTransaction();
        StudentOptionAnswerScore obj = em.find(modelClass, id);
        JPAUtil.endTransaction(em);
        return obj;
    }
}
