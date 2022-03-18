/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ldf.pfcwebtest.persistence;

import com.ldf.pfcwebtest.model.Exam;
import com.ldf.pfcwebtest.model.ModelCreation;
import static org.testng.Assert.*;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 *
 * @author Lo
 */
public class ExamQuestionDAONGTest {
    
    public ExamQuestionDAONGTest() {
    }

    @BeforeMethod
    public void setUpMethod() throws Exception {
    }
    
    @Test
    public void testFindByExam(){
        ModelCreation model = new ModelCreation();
        Exam exam = model.createExam(model.createCourse(0, model.createDegree(0)), model.createConsultant(0));
        ExamDAO dao = new ExamDAO();
        dao.create(exam);
        
        ExamQuestionDAO daoEQ = new ExamQuestionDAO();
        daoEQ.findByExam(exam.getId()).forEach(System.out::println);
    }
    
}
