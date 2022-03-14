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
        Degree degree = dao.create(model.createDegree(1));
        dao.create(model.createDegree(2));
        dao.create(model.createDegree(3));
        System.out.println("Original:"+degree);
        degree.setName("Change Name");
        System.out.println("Changed:"+degree);
        dao.update(degree);
        System.out.println("loaded:"+dao.find(degree.getId()));
        dao.delete(degree);
        System.out.println("List:"+dao.findAll());
        System.out.println("Ordered List:"+dao.findAll("name", Boolean.FALSE));
        System.out.println("List count:"+dao.count());
        
    }
    
}
