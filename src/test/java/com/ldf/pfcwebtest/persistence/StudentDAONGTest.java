/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ldf.pfcwebtest.persistence;

import com.ldf.pfcwebtest.model.Student;
import com.ldf.pfcwebtest.model.ModelCreation;
import java.util.Map;
import static org.testng.Assert.*;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 *
 * @author Lo
 */
public class StudentDAONGTest {
    
    public StudentDAONGTest() {
    }

    @BeforeMethod
    public void setUpMethod() throws Exception {
    }
    
    @Test
    public void testStudentDAO(){
        ModelCreation model = new ModelCreation();
        StudentDAO dao = new StudentDAO();
        
        Student student1 = model.createStudent(1);
        Student student2 = model.createStudent(2);
        
        assertNotEquals (student1,student2);
        
        dao.create(student1);
        dao.create(student2);

        for (int i = 3; i < 10; i++) {
            dao.create(model.createStudent(i));
        }          
                      
        assertEquals(dao.count(),9);
       
        student1.setFirstName("Estudiante 1");
        dao.update(student1);
        
        Student found = dao.find(student1.getId());
        assertEquals(found.getFirstName(),"Estudiante 1");
        
        dao.delete(student1);
        assertEquals(dao.count(),8);
        
        assertTrue(dao.findAll().size()==8);
        
        System.out.println(dao.findAll("firstName", Boolean.TRUE));
        assertTrue (dao.findAll("firstName", Boolean.TRUE).indexOf(student2)==0);
        
        assertTrue (dao.findRange(new int[]{0,3,4}).size()==3);
        
        Map<String, String> test1 = Map.of(
          "firstName", "student 2"           
        );
        assertTrue (dao.countCustom(test1)==1);
        
    }
    
}
