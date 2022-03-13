/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/EmptyTestNGTest.java to edit this template
 */
package com.ldf.pfcwebtest.persistence;

import com.ldf.pfcwebtest.model.Consultant;
import com.ldf.pfcwebtest.model.Group;
import com.ldf.pfcwebtest.model.Student;
import com.ldf.pfcwebtest.model.User;
import com.ldf.pfcwebtest.persistence.util.JPASessionUtil;
import java.util.ArrayList;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import static org.testng.Assert.*;
import org.testng.annotations.Test;

/**
 *
 * @author ldiez
 */
public class StudentNGTest {

    public StudentNGTest() {
    }

    @Test
    public void persistStudent() {
        EntityManager em = JPASessionUtil.getEntityManager();
        em.getTransaction().begin();
        Student student =
                Student.builder()//.studentBuilder()
                .dni("45575745L")
                .firstName("Alberto").surname1("Perez").surname2("Bustos")
                .userName("APB123")
                .userPassword("123456")
               // .groups(new ArrayList<Group>())
                .build();
        em.persist(student);
        em.getTransaction().commit();
       // System.out.println("IdUser:"+consultant.getIdUser());
        em.close();
        em = JPASessionUtil.getEntityManager();
        em.getTransaction().begin();
        
        //no funciona TypedQuery<Student> q = em.createQuery("from User u where u.dni=:dni and type(u)=:type", Student.class);
        //En JPQL y HQL usamos el nombre de las Entitys no de las Tablas de BD
        TypedQuery<User> q = em.createQuery("from User u where u.dni=:dni and type(u)=:type", User.class);
        q.setParameter("dni", "45575745L");
        q.setParameter("type", Student.class);
        Student result = (Student)q.getSingleResult();
        // si funciona Consultant result = em.find(Consultant.class, consultant.getIdUser());
        System.out.println(student);
        System.out.println(result);
        assertNotNull(result);
        assertEquals(result, student);
        em.remove(result);
        em.getTransaction().commit();
        em.close();
    }

}
