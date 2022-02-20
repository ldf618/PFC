/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testmodel;

import com.ldf.pfcwebtest.model.User;
import com.ldf.pfcwebtest.model.Student;
import static org.testng.Assert.*;
import org.testng.annotations.Test;

/**
 *
 * @author Lo
 */
public class TestModel {
    
    public TestModel() {
    }

    @Test
    public void UserTest(){
        User user=
                User.builder()
                .dni("16232121J")
                .idUser(1)
                .name("Juan").surname1("Diez").surname2("Fuente")
                .userName("JDF123")
                .userPassword("123456")
                .build();
        assertEquals(user.getDni(), "16232121J");
        assertEquals(user.getIdUser(), 1);
        assertEquals(user.getName()+" "+user.getSurname1()+" "+user.getSurname2(),"Juan Diez Fuente");
        assertEquals(user.getUserName(), "JDF123");
        assertEquals(user.getUserPassword(), "123456");
    }
    
    @Test
    public void StudentTest(){        
        Student student =
                Student.studentBuilder()
                .dni("16232121J")
                .idUser(1)
                .name("Juan").surname1("Diez").surname2("Fuente")
                .userName("JDF123")
                .userPassword("123456")
                .build();
        assertEquals(student.getDni(), "16232121J");
        assertEquals(student.getIdUser(), 1);
        assertEquals(student.getName()+" "+student.getSurname1()+" "+student.getSurname2(),"Juan Diez Fuente");
        assertEquals(student.getUserName(), "JDF123");
        assertEquals(student.getUserPassword(), "123456");
    }
}
