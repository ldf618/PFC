/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ldf.pfcwebtest.persistence;

import com.ldf.pfcwebtest.model.Course;
import com.ldf.pfcwebtest.model.Degree;
import com.ldf.pfcwebtest.persistence.util.JPASessionUtil;
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
public class DegreeNGTest {
    
    public DegreeNGTest() {
    }

    @AfterMethod
    public void cleanup() {
        doWithEntityManager((em) -> {
            System.out.println("rows:" + em.createQuery("delete from Course").executeUpdate());
            System.out.println("rows:" + em.createQuery("delete from Degree").executeUpdate());
        });
    }

    @BeforeMethod
    public void populateData() throws Exception {
        doWithEntityManager((em) -> {
            Degree degree
                    = Degree.builder()
                            .name("Computer Science")
                            .build();
            Course course1
                    = Course.builder()
                            .name("Mathematics 1")
                            .credits(6d)
                            .build();
            Course course2
                    = Course.builder()
                            .name("Computer Network 1")
                            .credits(6d)
                            .build();
            Course course3
                    = Course.builder()
                            .name("Physics 1")
                            .credits(3d)
                            .build();
            Course course4
                    = Course.builder()
                            .name("Programming 1")
                            .credits(12d)
                            .build();
            
            course1.setDegree(degree);
            course2.setDegree(degree);
            course3.setDegree(degree);
            course4.setDegree(degree);
            
            em.persist(course1);
            em.persist(course2);
            em.persist(course3);
            em.persist(course4);
        });
    }

    // @Test
    public void persistDegree() {
        Degree degree
                = Degree.builder()
                        .name("Economics")
                        .build();
        EntityManager em = JPASessionUtil.getEntityManager();
        em.getTransaction().begin();
        em.persist(degree);
        em.getTransaction().commit();
        em.close();
        em = JPASessionUtil.getEntityManager();
        em.getTransaction().begin();
        Degree result = em.find(Degree.class, degree.getId());
        assertNotNull(result);
        assertEquals(result.getName(), degree.getName());
        assertEquals(result.getId(), degree.getId());
        em.remove(result);
        em.getTransaction().commit();
        em.close();
    }

    /* @Test
    public void persistFullDegree() {
        EntityManager em = JPASessionUtil.getEntityManager();
        em.getTransaction().begin();

        Degree degree
                = Degree.builder()
                        .name("Computer Science")
                        .build();
        Course course1
                = Course.builder()
                        .name("Mathematics 1")
                        .credits(6d)
                        .build();
        Course course2
                = Course.builder()
                        .name("Network 1")
                        .credits(6d)
                        .build();
        Course course3
                = Course.builder()
                        .name("Physics 1")
                        .credits(3d)
                        .build();
        Course course4
                = Course.builder()
                        .name("Programming 1")
                        .credits(12d)
                        .build();

        course1.setDegree(degree);
        course2.setDegree(degree);
        course3.setDegree(degree);
        course4.setDegree(degree);

        //LinkedList<Course> courseList = new LinkedList<>(Arrays.asList(course1, course2, course3, course4));
        //degree.setCourses(courseList);
        //em.persist(degree);
        em.persist(course1);
        em.persist(course2);
        em.persist(course3);
        em.persist(course4);
        
        em.getTransaction().commit();
        em.close();
    }*/
    //@Test
    public void deleteDegree() {
        deleteCourseByName("Mathematics 1");
        deleteCourseByName("Computer Network 1");
        deleteCourseByName("Physics 1");
        deleteCourseByName("Programming 1");
        deleteDegreeByName("Computer Science");
        
    }
    
    @Test
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
            });*/
            //degree.getCourses().clear();
            degree.getCourses().remove(1);
            em.persist(degree);                
        });
        
        doWithEntityManager((var em) -> {
            Degree degree
                    = em.createQuery("from Degree d where d.name = :name", Degree.class)
                            .setParameter("name", "Computer Science")
                            .getSingleResult();
            System.out.println("Degree: " + degree.getName());
            degree.getCourses().stream().forEach((c) -> System.out.println(c.getName().concat("--")));
        });
    }
    
    public void deleteDegreeByName(String name) {
        doWithEntityManager((var em) -> {
            Degree degree
                    = em.createQuery("from Degree d where d.name = :name", Degree.class)
                            .setParameter("name", name)
                            .getSingleResult();
            em.remove(degree);
        });
    }    

    public void deleteCourseByName(String name) {
        doWithEntityManager((var em) -> {
            Course course
                    = em.createQuery("from Course c where c.name = :name", Course.class)
                            .setParameter("name", name)
                            .getSingleResult();
            em.remove(course);
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
