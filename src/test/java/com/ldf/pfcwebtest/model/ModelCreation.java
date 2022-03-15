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
import java.util.Random;
import static org.testng.Assert.*;
import org.testng.annotations.Test;

/**
 *
 * @author ldiez
 */
public class ModelCreation {

    public ModelCreation() {
    }

    @Test
    public void populate() {
        Student student = createStudent(1);
        Consultant consultant = createConsultant(1);
        Degree degree = createDegree(1);
        Course course = createCourse(1, degree);
        Classroom classroom = createClassroom(1, consultant, course);
        Exam exam = createExam(course, consultant);
        Group group = createGroup(1, classroom);
        ExamAnswer examAnswer = createExamAnswer(exam,student);
        System.out.println(examAnswer);
    }

    public Student createStudent(int i) {
        return Student.builder()
                .dni("0000000"+i+"J")
                //.id(1)
                .firstName("student "+i).surname1("Diez").surname2("Fuente")
                .userName("JDF123")
                .userPassword("123456")
                .build();
    }

    public Consultant createConsultant(int i) {
        return Consultant.builder()
                .dni("1111111"+i+"J")
                //.id(1)
                .firstName("Consultant "+i).surname1("Diez").surname2("Fuente")
                .userName("JDF123")
                .userPassword("123456")
                .build();
    }

    public Degree createDegree(int i) {
        return Degree.builder()
                .name("Computer Science " + i)
                .courses(new ArrayList<>())
                .build();
    }

    public Course createCourse(int i, Degree degree) {
        return Course.builder()
                .name(degree.getName() + " curso " + i)
                .credits(i + 3)
                .degree(degree)
                .build();
    }

    public Classroom createClassroom(int i, Consultant consultant, Course course) {
        return Classroom.builder()
                .name("Classroom " + i)
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

    public List<ExamQuestion> addExamQuestions(Exam exam) {
        ArrayList<ExamQuestion> list = new ArrayList();
        for (int i = 0; i < 5; i++) {
            ExamQuestion question
                    = ExamQuestion.builder()
                            .wording("Question " + i + " statement")
                            .isMultipleSelection(((i % 2) == 0))
                            .exam(exam)
                            .type(ExamQuestion.QuestionType.OPTIONS)
                            .position(i)
                            .build();
            question.setExamQuestionOptions(
                    addExamQuestionOptions(question)
            );
            list.add(question);
        }

        return list;
    }

    public List<ExamQuestionOption> addExamQuestionOptions(ExamQuestion question) {
        ArrayList<ExamQuestionOption> list = new ArrayList();
        for (int i = 0; i < 3; i++) {

            ExamQuestionOption questionOption
                    = ExamQuestionOption.builder()
                            .wording("Option " + i)
                            .isRigth(i == 1)
                            .examQuestion(question)
                            .position(i)
                            .build();
            list.add(questionOption);
        }

        return list;
    }

    public Group createGroup(int i, Classroom classroom) {
        return Group.builder()
                .name("Grupo " + i)
                .classroom(classroom)
                .build();
    }

    public ExamAnswer createExamAnswer(Exam exam, Student student) {
        ExamAnswer examAnswer
                = ExamAnswer.builder()
                        .creationDate(LocalDate.now())
                        .changeDate(LocalDateTime.now())
                        .exam(exam)
                        .student(student)
                        .finished(true)
                        .build();

        examAnswer.setExamQuestionAnswers(
                addExamQuestionAnswers(examAnswer, exam.getExamQuestions())
        );

        return examAnswer;
    }

    public List<ExamQuestionAnswer> addExamQuestionAnswers(ExamAnswer examAnswer, List<ExamQuestion> examQuestions) {
        ArrayList<ExamQuestionAnswer> list = new ArrayList();
        examQuestions.forEach(q -> {
            ExamQuestionAnswer question
                    = ExamQuestionAnswer.builder()
                            .answer("Repuesta a la pregunta " + q.getPosition())
                            .examAnswer(examAnswer)
                            .examQuestion(q)
                            .build();
            list.add(question);

            question.setExamQuestionOptionAnswer(
                    addExamQuestionOptionAnswers(question, q.getExamQuestionOptions())
            );
        }
        );

        return list;
    }

        public List<ExamQuestionOptionAnswer> addExamQuestionOptionAnswers(ExamQuestionAnswer question,List<ExamQuestionOption> examQuestionOption) {
        ArrayList<ExamQuestionOptionAnswer> list = new ArrayList();
        examQuestionOption.forEach(q -> {

            ExamQuestionOptionAnswer optionAnswer
                    = ExamQuestionOptionAnswer.builder()
                            .answer("Answer to Option "+q.getPosition())
                            .isSelected(new Random().nextBoolean())
                            .examQuestionAnswer(question)
                            .examQuestionOption(q)
                            .build();
            list.add(optionAnswer);
        });

        return list;
    }
}
