/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ldf.pfcwebtest.persistence;

import com.ldf.pfcwebtest.model.Classroom;
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
public class GroupNGTest {
    
    public GroupNGTest() {
    }

    @BeforeMethod
    public void setUpMethod() throws Exception {
    }
    
   // @Test
    public void persistGroup() {
        Group group = 
                Group.builder()
                .name("Grupo 1")
                .build();
        EntityManager em = JPASessionUtil.getEntityManager();
        em.getTransaction().begin();
        em.persist(group);
        em.getTransaction().commit();        
        em.close();
        
        em = JPASessionUtil.getEntityManager();
        em.getTransaction().begin();      
        Group result = em.find(Group.class, group.getIdGroup());
        assertNotNull(result);
        assertEquals(result.getName(), group.getName());
        assertEquals(result.getIdGroup(), group.getIdGroup());
        em.remove(result);
        em.getTransaction().commit();
        em.close();
    }
    
    @Test
    public void persistFullGroup() {
        EntityManager em = JPASessionUtil.getEntityManager();
        em.getTransaction().begin();
        
        Student student1 =Student.studentBuilder().dni("11111111A").name("name1").surname1("surname11").surname2("surname21")
                .userName("NSS111").userPassword("123456").build();
//        em.persist(student1);
    
        Student student2 =Student.studentBuilder().dni("22222222B").name("name2").surname1("surname12").surname2("surname22")
                .userName("NSS222").userPassword("123456").build();
//        em.persist(student2);
        
        Student student3 =Student.studentBuilder().dni("33333333C").name("name3").surname1("surname13").surname2("surname23")
                .userName("NSS333").userPassword("123456").build();        
//        em.persist(student3);

        
        LinkedList <Student> studentList = new LinkedList<>(Arrays.asList(student1,student2,student3));
                
        Classroom classroom = Classroom.builder().name("classroom1").build();
        em.persist(classroom);
        Group group = 
                Group.builder()
                .name("Grupo 1").students(studentList)
                .classroom(classroom)
                .build();
        
        student1.setGroups(Arrays.asList(group));
        //student2.setGroups(Arrays.asList(group));
        //student3.setGroups(Arrays.asList(group));
        
        em.persist(group);
        em.getTransaction().commit();        
        em.close();        
   }
    
}
