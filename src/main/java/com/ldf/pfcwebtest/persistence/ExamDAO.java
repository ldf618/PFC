package com.ldf.pfcwebtest.persistence;

import com.ldf.pfcwebtest.model.Course_;
import com.ldf.pfcwebtest.model.Exam;
import com.ldf.pfcwebtest.model.ExamQuestion;
import com.ldf.pfcwebtest.model.ExamQuestion_;
import com.ldf.pfcwebtest.model.Exam_;
import com.ldf.pfcwebtest.persistence.util.JPAUtil;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.ListJoin;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

public class ExamDAO extends DAO<Exam> {

    public ExamDAO() {
        this.setModelClass(Exam.class);
    }
    
    public Exam findExam(int id){
        return find(id);
    }
    
    
    //better placed on ExamQuetionDAO
    public List<ExamQuestion> findExamQuestions(int id){
        return find(id).getExamQuestions();
        /*
        solved with @orderBy on entity attribute ExamQuestions
        List<ExamQuestion> sorted = exam.getExamQuestions()
                .stream()
                    .sorted(Comparator.comparing(ExamQuestion::getPosition))
                        .collect(Collectors.toList());
        */
    }    

    public List<Exam> findByCourse(int idCourse) {
        Map<String, Integer> parameters = Map.of(
                "course.id", idCourse);
        return findCustom(parameters, null, "", true);
    }

    
    public List<Exam> findByQuestionTypeAndCourse (Integer idCourse, ExamQuestion.QuestionType questionType1, ExamQuestion.QuestionType questionType2){
        Map<String, Object> parameters = Map.of(
                "idCourse", idCourse,
                "questionType1",questionType1,
                "questionType2",questionType2);
        return execJPQLMultiple("exam.findByQuestionTypeAndCourse",parameters);   
    }

    
     public List<Exam> findByQuestionTypeAndCourseCriteria(Integer idCourse, ExamQuestion.QuestionType questionType1, ExamQuestion.QuestionType questionType2){
         
        EntityManager em = JPAUtil.beginTransaction();
        CriteriaBuilder builder = em.getCriteriaBuilder();        
        CriteriaQuery<Exam> cq = builder.createQuery(Exam.class);   
        Root<Exam> root = cq.from(Exam.class);
        cq.select(root);        
        //Join<Exam, ExamQuestion> examQuestion= root.join(Exam_.examQuestions, JoinType.LEFT);
        ListJoin<Exam, ExamQuestion>examQuestion= root.joinList(Exam_.EXAM_QUESTIONS,JoinType.LEFT);
        //cq.multiselect(root, examQuestion);
        cq.select(root);

        List<Predicate> restrictions = new ArrayList<>();
        restrictions.add(builder.isNotNull(root.get(Exam_.PUBLICATION_DATE)));
        restrictions.add(builder.equal(root.get(Exam_.course).get(Course_.id),idCourse));
        restrictions.add(builder.in(examQuestion.get(ExamQuestion_.TYPE)).value(questionType1).value(questionType2));
        cq.where(restrictions.toArray(new Predicate[restrictions.size()]));

        TypedQuery<Exam> query = em.createQuery(cq);
        List<Exam> result =  query.getResultList();
        JPAUtil.endTransaction(em);
        return result;
     }
}

