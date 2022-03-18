/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ldf.pfcwebtest.persistence;

import com.ldf.pfcwebtest.model.Exam;
import com.ldf.pfcwebtest.model.ExamQuestion;
import com.ldf.pfcwebtest.model.ModelCreation;
import java.util.List;
import static org.testng.Assert.*;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 *
 * @author Lo
 */
public class ExamDAONGTest {
    
    public ExamDAONGTest() {
    }

    @BeforeMethod
    public void setUpMethod() throws Exception {
        ModelCreation model = new ModelCreation();
        Exam exam = model.createExam(model.createCourse(0, model.createDegree(0)), model.createConsultant(0));
        ExamDAO dao = new ExamDAO();
        dao.create(exam);        
    }
    
    //@Test(enabled = false)
    public void testFindByExam(){
        ExamDAO dao = new ExamDAO();
        System.out.println(dao.findAll());
    }
    
    //@Test(enabled = false)
    public void testFindByQuestionTypeAndCourse(){
        ExamDAO dao = new ExamDAO();
        System.out.println(dao.findByQuestionTypeAndCourse(1,ExamQuestion.QuestionType.OPTIONS,ExamQuestion.QuestionType.OPTIONS));
    }
    
    //@Test(enabled = false)
    public void testFindByQuestionTypeAndCourseCriteria(){
        ExamDAO dao = new ExamDAO();
        List<Exam> list = dao.findByQuestionTypeAndCourseCriteria(1,ExamQuestion.QuestionType.OPTIONS,ExamQuestion.QuestionType.OPTIONS);
        System.out.println("testFindByQuestionTypeAndCourseCriteria------------------");
        list.forEach(System.out::println);
    }
    
    //@Test(enabled = false)
    public void testFindExam(){
        ExamDAO dao = new ExamDAO();
        Exam exam = dao.findExam(1);
        System.out.println(exam);
    }
    
    @Test(enabled = true)
    public void testFindExamQuestions(){
        ExamDAO dao = new ExamDAO();
        List<ExamQuestion> examQuestions=dao.findExamQuestions(1);
        System.out.println(examQuestions.size());
        examQuestions.forEach(System.out::println);
    }    
    
}
