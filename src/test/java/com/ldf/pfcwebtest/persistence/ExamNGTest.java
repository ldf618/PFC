/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ldf.pfcwebtest.persistence;

import com.ldf.pfcwebtest.model.Consultant;
import com.ldf.pfcwebtest.model.Course;
import com.ldf.pfcwebtest.model.Exam;
import com.ldf.pfcwebtest.model.Exam.ExamType;
import com.ldf.pfcwebtest.model.ExamQuestionOptionNGTest;
import com.ldf.pfcwebtest.persistence.util.JPASessionUtil;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.time.Period;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.function.Consumer;
import javax.persistence.EntityManager;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 *
 * @author Lo
 */
public class ExamNGTest {

    public ExamNGTest() {
    }

    @AfterMethod
    public void cleanup() {
        doWithEntityManager((em) -> {
            System.out.println("rows:" + em.createQuery("delete from ExamQuestionOption").executeUpdate());
            System.out.println("rows:" + em.createQuery("delete from ExamQuestion").executeUpdate());
            System.out.println("rows:" + em.createQuery("delete from Exam").executeUpdate());
        });
    }

    @BeforeMethod
    public void populateData() throws Exception {
        doWithEntityManager((em) -> {
            ExamQuestionOptionNGTest examTest = new ExamQuestionOptionNGTest();
            Exam exam =examTest.createExam();
            em.persist(exam);
        });
    }

    @Test (enabled = false)
    public void persistExam() {
        Exam exam
                = Exam.builder()
                        .name("Exam 1")
                        .instructions("question not answered will be 0. Incorrect question will substract 0,5...")
                        .creationDate(LocalDate.now())
                        .changeDate(LocalDateTime.now())
                        .publicationDate(ZonedDateTime.now().plusDays(3))
                        .deadline(LocalDate.of(2022, Month.MARCH, 31))
                        .type(ExamType.INDIVIDUAL)
                        .build();
        EntityManager em = JPASessionUtil.getEntityManager();
        em.getTransaction().begin();
        em.persist(exam);
        em.getTransaction().commit();
        em.close();
        em = JPASessionUtil.getEntityManager();
        em.getTransaction().begin();
        Exam result = em.find(Exam.class, exam.getId());
        assertNotNull(result);
        assertEquals(result.getName(), exam.getName());
        assertEquals(result.getId(), exam.getId());
        em.remove(result);
        em.getTransaction().commit();
        em.close();
    }

    @Test
    public void readExam() {
        doWithEntityManager((var em) -> {
            Exam exam
                    = em.createQuery("from Exam e where e.name = :name", Exam.class)
                            .setParameter("name", "Exam 1")
                            .getSingleResult();
            System.out.println(exam);
            assertEquals(exam.getName(), "Exam 1");
            assertEquals(exam.getDeadline(), LocalDate.of(2022, Month.MARCH, 31));
            assertEquals(exam.getType(),Exam.ExamType.INDIVIDUAL);
            assertEquals(exam.getExamQuestions().size(),5);
            assertEquals(exam.getExamQuestions().get(0).getExamQuestionOptions().size(),3);
            
        });
    }

    @Test
    public void deleteExam() {
        deleteExamByName("Exam 1");

    }

    public void deleteExamByName(String name) {
        doWithEntityManager((var em) -> {
            Exam exam
                    = em.createQuery("from Exam e where e.name = :name", Exam.class)
                            .setParameter("name", name)
                            .getSingleResult();
            em.remove(exam);
        });
    }

    private void doWithEntityManager(Consumer<EntityManager> command) {
        EntityManager em = JPASessionUtil.getEntityManager();
        em.getTransaction().begin();
        command.accept(em);
        if (em.getTransaction().isActive()
                && !em.getTransaction().getRollbackOnly()) {
            em.getTransaction().commit();
        } else {
            em.getTransaction().rollback();
        }
        em.close();
    }
}
