/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/EmptyTestNGTest.java to edit this template
 */
package com.ldf.pfcwebtest.model;

import static org.testng.Assert.*;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 *
 * @author ldiez
 */
public class UserNGTest {
    
    public UserNGTest() {
    }

    @BeforeClass
    public void setUpClass() throws Exception {
    }

    @AfterClass
    public void tearDownClass() throws Exception {
    }

    @BeforeMethod
    public void setUpMethod() throws Exception {
    }

    @AfterMethod
    public void tearDownMethod() throws Exception {
    }

    @Test
    public void UserTest(){
        User user=
                User.builder()
                .dni("16232121J")
                .id(1)
                .name("Juan").surname1("Diez").surname2("Fuente")
                .userName("JDF123")
                .userPassword("123456")
                .build();
        assertEquals(user.getDni(), "16232121J");
        assertEquals(user.getId(), 1);
        assertEquals(user.getName()+" "+user.getSurname1()+" "+user.getSurname2(),"Juan Diez Fuente");
        assertEquals(user.getUserName(), "JDF123");
        assertEquals(user.getUserPassword(), "123456");
    }
    
}
