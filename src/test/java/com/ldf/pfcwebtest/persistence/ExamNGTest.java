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

        });
    }

    @BeforeMethod
    public void populateData() throws Exception {
        doWithEntityManager((em) -> {
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

            em.persist(exam);
        });
    }

    // @Test
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
    public void persistFullExam() {
        doWithEntityManager((var em) -> {
            Exam exam
                    = em.createQuery("from Exam e where e.name = :name", Exam.class)
                            .setParameter("name", "Exam 1")
                            .getSingleResult();
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
        });
    }

    //@Test
    public void deleteExam() {
        deleteExamByName("Exam 1");

    }

    /*    @Test
    public void queryDegree() {
        doWithEntityManager((var em) -> {
            Degree degree
                    = em.createQuery("from Degree d where d.name = :name", Degree.class)
                            .setParameter("name", "Computer Science")
                            .getSingleResult();
            System.out.println("Degree: " + degree.getName());
            degree.getCourses().stream().forEach((c) -> System.out.println(c.getName().concat("--")));                       
           /* degree.getCourses().stream().forEach((c) -> {
                                                         c.setDegree(null);
                                                         em.persist(c);
            });
            degree.getCourses().clear();
            //degree.getCourses().remove(1);
        });
        
        doWithEntityManager((var em) -> {
            Degree degree
                    = em.createQuery("from Degree d where d.name = :name", Degree.class)
                            .setParameter("name", "Computer Science")
                            .getSingleResult();
            System.out.println("Degree: " + degree.getName());
            degree.getCourses().stream().forEach((c) -> System.out.println(c.getName().concat("--")));
            degree.setName("Cloud Computing");
             System.out.println("Degree: " + degree.getName());
        });
        
    }*/
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
