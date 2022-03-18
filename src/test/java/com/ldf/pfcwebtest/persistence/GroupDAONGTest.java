/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ldf.pfcwebtest.persistence;

import com.ldf.pfcwebtest.model.Classroom;
import com.ldf.pfcwebtest.model.Group;
import com.ldf.pfcwebtest.model.ModelCreation;
import com.ldf.pfcwebtest.model.Student;
import java.util.List;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 *
 * @author Lo
 */
public class GroupDAONGTest {
    
    public GroupDAONGTest() {
    }

    @BeforeMethod
    public void setUpMethod() throws Exception {
    }
    
    @Test
    public void findByClassroomTest(){
        ModelCreation model = new ModelCreation();
        GroupDAO dao = new GroupDAO();
        Classroom classroom = model.createClassroom(0, model.createConsultant(0), 
                model.createCourse(0,model.createDegree(0)));
        Group group = model.createGroup(0,classroom);
        Student student1 = model.createStudent(1);
        Student student2 = model.createStudent(2);
        Student student3 = model.createStudent(3);
        List<Student> students= List.of(student1, student2, student3);
        group.setStudents(students);
        dao.create(group);
        
        System.out.println(dao.findByClassroom(classroom.getId()));
        System.out.println(dao.findByStudentAndClassroom(student1.getId(),classroom.getId()));
    }
    
}
