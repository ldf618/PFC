/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/EmptyTestNGTest.java to edit this template
 */
package com.ldf.pfcwebtest.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.ArrayList;
import java.util.List;
import org.testng.annotations.Test;
import static org.testng.Assert.*;

/**
 *
 * @author ldiez
 */
public class ExamQuestionOptionNGTest {


    public ExamQuestionOptionNGTest() {

    }

    @Test
    public void TestExam(){
       Exam exam = createExam();
       System.out.println(exam);
       
       System.out.println(exam.getPublicationDate());
        System.out.println(exam.getPublicationDate().format(DateTimeFormatter.ofLocalizedDate(FormatStyle.FULL)));
        System.out.println(exam.getPublicationDate().format(DateTimeFormatter.ofLocalizedDate(FormatStyle.LONG)));
        System.out.println(exam.getPublicationDate().format(DateTimeFormatter.ofLocalizedDate(FormatStyle.MEDIUM)));
        System.out.println(exam.getPublicationDate().format(DateTimeFormatter.ofLocalizedDate(FormatStyle.SHORT)));
        System.out.println(exam.getPublicationDate().format(DateTimeFormatter.ofPattern("dd 'de' LLLL 'de' yyyy")));
        System.out.println(exam.getPublicationDate().format(DateTimeFormatter.ofPattern("dd'/'MM'/'yyyy")));

    //           System.out.println(exam.getChangeDate().format(DateTimeFormatter.ofLocalizedDateTime(FormatStyle.FULL)));
    //            System.out.println(exam.getChangeDate().format(DateTimeFormatter.ofLocalizedDateTime(FormatStyle.LONG)));
        System.out.println(exam.getChangeDate().format(DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM)));
        System.out.println(exam.getChangeDate().format(DateTimeFormatter.ofLocalizedDateTime(FormatStyle.SHORT)));

        System.out.println(exam.getPublicationDate().format(DateTimeFormatter.ofLocalizedDateTime(FormatStyle.FULL)));
        System.out.println(exam.getPublicationDate().format(DateTimeFormatter.ofLocalizedDateTime(FormatStyle.LONG)));
        System.out.println(exam.getPublicationDate().format(DateTimeFormatter.ofLocalizedDateTime(FormatStyle.SHORT)));
        
        
       assertEquals(exam.getName(), "Exam 1");
       assertEquals(exam.getDeadline(), LocalDate.of(2022, Month.MARCH, 31));
       assertEquals(exam.getType(),Exam.ExamType.INDIVIDUAL);
       assertEquals(exam.getExamQuestions().size(),5);
       assertEquals(exam.getExamQuestions().get(0).getExamQuestionOptions().size(),3);
    }
    
    public Exam createExam() {
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
        exam.setExamQuestions(addExamQuestions(exam));
        
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
            question.setExamQuestionOptions(addExamQuestionOptions(question));
            list.add(question);
         }
         
         return list;
    }
    
    public List<ExamQuestionOption> addExamQuestionOptions (ExamQuestion question){
         ArrayList <ExamQuestionOption> list = new ArrayList();
         for (int i = 0; i < 3; i++) {
            
            ExamQuestionOption questionOption
                   = ExamQuestionOption.builder()
                           .wording("Option "+i)
                           .isRigth(i==1)
                           .examQuestion(question)
                           .position(i)
                           .build();
            list.add(questionOption);
         }
         
         return list;
    }

}
