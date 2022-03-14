/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ldf.pfcwebtest.persistence;

import com.ldf.pfcwebtest.model.Course;
import com.ldf.pfcwebtest.model.Degree;
import com.ldf.pfcwebtest.model.ModelCreation;
import java.util.ArrayList;
import static org.testng.Assert.*;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 *
 * @author Lo
 */
public class DegreeDAONGTest {
    
    public DegreeDAONGTest() {
    }

    @BeforeMethod
    public void setUpMethod() throws Exception {
    }
    
    @Test
    public void testDregreeDAO(){
        ModelCreation model = new ModelCreation();
        DegreeDAO dao = new DegreeDAO();
        
        Degree mdegree1 = model.createDegree(1);
        Degree mdegree = model.createDegree(1);
        
        assertEquals (mdegree1,mdegree);
        Degree mdegree2 = model.createDegree(2);
        Degree mdegree3 = model.createDegree(3);
        
        dao.create(mdegree1);
        dao.create(mdegree2);
        dao.create(mdegree3);
        for (int i = 4; i < 10; i++) {
            dao.create(model.createDegree(i));
        }          
        
        assertNotEquals(mdegree1,mdegree);
        
        mdegree.setId(1);
        System.out.println(mdegree1);
        System.out.println(mdegree);
        assertEquals (mdegree1,mdegree);
        
        assertEquals(dao.count(),9);
       
        mdegree3.setName("Changed name");
        dao.update(mdegree3);
        
        Degree degree3 = dao.find(mdegree3.getId());
        assertEquals(degree3.getName(),"Changed name");
        assertEquals(degree3,mdegree3);
        
        dao.delete(degree3);
        assertEquals(dao.count(),8);
        
        assertTrue(dao.findAll().size()==8);
        
        System.out.println(dao.findAll("name", Boolean.TRUE));
        assertTrue (dao.findAll("name", Boolean.TRUE).indexOf(mdegree2)==1);
        assertTrue (dao.findAll("name", Boolean.TRUE).indexOf(mdegree1)==0);
        
        assertTrue (dao.findRange(new int[]{0,3,4}).size()==3);
        
    }
    
}
