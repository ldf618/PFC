/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/EmptyTestNGTest.java to edit this template
 */
package com.ldf.pfcwebtest.persistence;

import com.ldf.pfcwebtest.model.Consultant;
import com.ldf.pfcwebtest.model.User;
import com.ldf.pfcwebtest.persistence.util.JPASessionUtil;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import static org.testng.Assert.*;
import org.testng.annotations.Test;

/**
 *
 * @author ldiez
 */
public class ConsultantNGTest {

    public ConsultantNGTest() {
    }

    @Test
    public void uno() {
        EntityManager em = JPASessionUtil.getEntityManager();
        em.getTransaction().begin();
        Consultant consultant =
                Consultant.consultantBuilder()
                .dni("16232121J")
                .name("Juan").surname1("Diez").surname2("Fuente")
                .userName("JDF123")
                .userPassword("123456")
                .build();
        em.persist(consultant);
        em.getTransaction().commit();
        System.out.println("IdUser:"+consultant.getIdUser());
        em.close();
        em = JPASessionUtil.getEntityManager();
        em.getTransaction().begin();
        
        //no funciona TypedQuery<Consultant> q = em.createQuery("from Users u where u.dni=:dni and type(u)=:type", Consultant.class);
        TypedQuery<User> q = em.createQuery("from Users u where u.dni=:dni and type(u)=:type", User.class);
        q.setParameter("dni", "16232121J");
        q.setParameter("type", Consultant.class);
        Consultant result = (Consultant)q.getSingleResult();
        // si funciona Consultant result = em.find(Consultant.class, consultant.getIdUser());
        assertNotNull(result);
        assertEquals(result, consultant);
        em.remove(result);
        em.getTransaction().commit();
        em.close();
    }

}
