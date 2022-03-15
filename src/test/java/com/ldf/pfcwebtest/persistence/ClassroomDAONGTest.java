/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ldf.pfcwebtest.persistence;

import com.ldf.pfcwebtest.model.Classroom;
import com.ldf.pfcwebtest.model.ModelCreation;
import java.util.Map;
import static org.testng.Assert.*;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 *
 * @author Lo
 */
public class ClassroomDAONGTest {
    
    public ClassroomDAONGTest() {
    }

    @BeforeMethod
    public void setUpMethod() throws Exception {
    }
    
    @Test
    public void testClassroomDAO(){
        ModelCreation model = new ModelCreation();
        ClassroomDAO dao = new ClassroomDAO();
        
        Classroom classroom1 = model.createClassroom(1,model.createConsultant(1), model.createCourse(1, model.createDegree(1)));
        Classroom classroom2 = model.createClassroom(2,model.createConsultant(2), model.createCourse(1, model.createDegree(1)));
        
        assertNotEquals (classroom1,classroom2);
        
        dao.create(classroom1);
        dao.create(classroom2);

        for (int i = 3; i < 10; i++) {
            dao.create(model.createClassroom(i,model.createConsultant(i), model.createCourse(1, model.createDegree(1))));
        }          
                      
        assertEquals(dao.count(),9);
       
        classroom1.setName("Clase 1");
        dao.update(classroom1);
        
        Classroom found = dao.find(classroom1.getId());
        assertEquals(found.getName(),"Clase 1");
        
        dao.delete(classroom1);
        assertEquals(dao.count(),8);
        
        assertTrue(dao.findAll().size()==8);
        
        System.out.println(dao.findAll("name", Boolean.TRUE));
        assertTrue (dao.findAll("name", Boolean.TRUE).indexOf(classroom2)==0);
        
        assertTrue (dao.findRange(new int[]{0,3,4}).size()==3);
        
        Map<String, String> test1 = Map.of(
          "name", "Classroom 2"           
        );
        assertTrue (dao.countCustom(test1)==1);
        
    }
    
}
