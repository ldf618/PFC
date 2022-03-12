/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ldf.pfcwebtest.model;

import static org.testng.Assert.*;
import org.testng.annotations.Test;

/**
 *
 * @author Lo
 */
public class ConsultantNGTest {
    
    public ConsultantNGTest() {
    }

        
    @Test
    public void ConsultantTest(){        
        Consultant consultant =
                Consultant.builder()//consultantBuilder()
                .dni("16232121J")
                .id(1)
                .firstName("Juan").surname1("Diez").surname2("Fuente")
                .userName("JDF123")
                .userPassword("123456")
                .build();
        assertEquals(consultant.getDni(), "16232121J");
        assertEquals(consultant.getId(), 1);
        assertEquals(consultant.getFirstName()+" "+consultant.getSurname1()+" "+consultant.getSurname2(),"Juan Diez Fuente");
        assertEquals(consultant.getUserName(), "JDF123");
        assertEquals(consultant.getUserPassword(), "123456");
    }
}
