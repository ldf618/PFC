/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/EmptyTestNGTest.java to edit this template
 */
package com.ldf.pfcwebtest.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
import static org.testng.Assert.*;

/**
 *
 * @author ldiez
 */
public class ExamQuestionOptionNGTest {

    public ExamQuestionOptionNGTest() {

        Consultant consultant
                = Consultant.consultantBuilder()
                        .dni("16232121J")
                        .idUser(1)
                        .firstName("Juan").surname1("Diez").surname2("Fuente")
                        .userName("JDF123")
                        .userPassword("123456")
                        .build();
        assertEquals(consultant.getDni(), "16232121J");
        assertEquals(consultant.getId(), 1);
        assertEquals(consultant.getFirstName() + " " + consultant.getSurname1() + " " + consultant.getSurname2(), "Juan Diez Fuente");
        assertEquals(consultant.getUserName(), "JDF123");
        assertEquals(consultant.getUserPassword(), "123456");
    }

    public Exam CreateExam() {
        Exam exam
                = Exam.builder()
                        .name("Exam 1")
                        .instructions("question not answered will be 0. Incorrect question will substract 0,5...")
                        .creationDate(LocalDate.now())
                        .changeDate(LocalDateTime.now())
                        .publicationDate(ZonedDateTime.now().plusDays(3))
                        .deadline(LocalDate.of(2022, Month.MARCH, 31))
                        .type(Exam.ExamType.INDIVIDUAL)
                        .build();
        Consultant consultant
                = Consultant.consultantBuilder()
                        .firstName("Scott")
                        .surname1("Tiger")
                        .surname2("Tiger")
                        .dni("12345678Y")
                        .userName("SCC")
                        .userPassword("SCC12345678")
                        .build();
        Course course
                = Course.builder()
                        .name("Computer Network 1")
                        .credits(6d)
                        .build();
        exam.setCourse(course);
        exam.setConsultant(consultant);

        return exam;
    }
    
    public List<ExamQuestion> addExamQuestions (Exam exam){
         ArrayList <ExamQuestion> list = new ArrayList();
         for (int i = 0; i < 5; i++) {
            
            ExamQuestion question
                   = ExamQuestion.builder()
                           .wording("Question "+i+" statement")
                           .isMultipleSelection(((i % 2)==0))
                           .exam(exam)
                           .type(ExamQuestion.QuestionType.OPTIONS)
                           .position(i)
                           .build();
            list.add(question);
         }
         
         return list;
    }
    
    public List<ExamQuestionOption> addExamQuestionOptions (ExamQuestion question){
         ArrayList <ExamQuestionOption> list = new ArrayList();
         for (int i = 0; i < 3; i++) {
            
            ExamQuestionOption questionOption
                   = ExamQuestionOption.builder()
                           //.wording("Question "+i+" statement")
                          // .isMultipleSelection(((i % 2)==0))
                          // .exam(exam)
                          // .type(ExamQuestion.QuestionType.OPTIONS)
                           .position(i)
                           .build();
            list.add(questionOption);
         }
         
         return list;
    }

}
