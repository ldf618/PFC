package com.ldf.pfcwebtest.persistence;

import com.ldf.pfcwebtest.model.Classroom;
import java.util.Map;

public class ClassroomDAO extends DAO<Classroom> {

    public ClassroomDAO() {
        this.setModelClass(Classroom.class);
    }

    public Classroom findByConsultantAndCourse(int idConsultant, int idCourse) {    
   
       Map<String,Integer> parameters = Map.of(
               "idConsultant", idConsultant,
               "idCourse", idCourse);
        
       return execJPQLSingle("classroom.findByConsultantAndCourse",parameters);

    }
    
    public Classroom findByStudentAndCourse(int idStudent, int idCourse) {    
   
       Map<String,Integer> parameters = Map.of(
               "idStudent", idStudent,
               "idCourse", idCourse);
        
       return execJPQLSingle("classroom.findByStudentAndCourse",parameters);

    }    
}
