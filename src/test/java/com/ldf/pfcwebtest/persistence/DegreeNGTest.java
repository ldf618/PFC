/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ldf.pfcwebtest.persistence;

import com.ldf.pfcwebtest.model.Classroom;
import com.ldf.pfcwebtest.model.Course;
import com.ldf.pfcwebtest.model.Degree;
import com.ldf.pfcwebtest.model.Group;
import com.ldf.pfcwebtest.model.Student;
import com.ldf.pfcwebtest.persistence.util.JPASessionUtil;
import java.util.Arrays;
import java.util.LinkedList;
import javax.persistence.EntityManager;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 *
 * @author Lo
 */
public class DegreeNGTest {

    public DegreeNGTest() {
    }

    @BeforeMethod
    public void setUpMethod() throws Exception {
    }

    // @Test
    public void persistDegree() {
        Degree degree
                = Degree.builder()
                        .name("Computer Science")
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

    @Test
    public void persistFullDegree() {
        EntityManager em = JPASessionUtil.getEntityManager();
        em.getTransaction().begin();

        Degree degree
                = Degree.builder()
                        .name("Computer Science")
                        .build();
        Course course1
                =Course.builder()
                        .name("Mathematics 1")
                        .credits(6d)
                        .build();
        Course course2
                =Course.builder()
                        .name("Network 1")
                        .credits(6d)
                        .build();
        Course course3
                =Course.builder()
                        .name("Physics 1")
                        .credits(3d)
                        .build();
        Course course4
                =Course.builder()
                        .name("Programming 1")
                        .credits(12d)
                        .build();
        
        
        course1.setDegree(degree);
        course2.setDegree(degree);
        course3.setDegree(degree);
        course4.setDegree(degree);

        LinkedList<Course> courseList = new LinkedList<>(Arrays.asList(course1, course2, course3, course4));
        degree.setCourses(courseList);

        em.persist(degree);
        em.getTransaction().commit();
        em.close();
    }

}
