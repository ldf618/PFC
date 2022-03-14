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
import org.testng.annotations.Test;

/**
 *
 * @author ldiez
 */
public class PopulateAllModelNGTest {

    public PopulateAllModelNGTest() {
    }
    
    @Test
    public void populate(){
        Student student = createStudent();
        Consultant consultant = createConsultant();
        Degree degree = createDegree();
        Course course = createCourse(1, degree);
        Classroom classroom = createClassroom(consultant, course);
        Exam exam = createExam (course,consultant);
    }

    public Student createStudent() {
        return Student.builder()
                .dni("16232121J")
                .id(1)
                .firstName("Juan").surname1("Diez").surname2("Fuente")
                .userName("JDF123")
                .userPassword("123456")
                .build();
    }

    public Consultant createConsultant() {
        return Consultant.builder()
                .dni("16232121J")
                .id(1)
                .firstName("Juan").surname1("Diez").surname2("Fuente")
                .userName("JDF123")
                .userPassword("123456")
                .build();
    }

    public Degree createDegree() {
        return Degree.builder()
                .name("Computer Science")
                .build();
    }

    public Course createCourse(int i, Degree degree) {
        return Course.builder()
                .name(degree.getName()+" curso "+i)
                .credits(i+3)
                .degree(degree)
                .build();
    }
    
    public Classroom createClassroom(Consultant consultant, Course course) {
        return Classroom.builder()
                .name("Classroom 1")
                .consultant(consultant)
                .course(course)
                .build();
    }

    public Exam createExam(Course course, Consultant consultant) {
        Exam exam
                = Exam.builder()
                        .name("Exam 1")
                        .instructions("question not answered will be 0. Incorrect question will substract 0,5...")
                        .creationDate(LocalDate.now())
                        .changeDate(LocalDateTime.now())
                        .publicationDate(ZonedDateTime.now().plusDays(3))
                        .deadline(LocalDate.of(2022, Month.MARCH, 31))
                        .type(Exam.ExamType.INDIVIDUAL)
                        .course(course)
                        .consultant(consultant)
                        .build();
 
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
