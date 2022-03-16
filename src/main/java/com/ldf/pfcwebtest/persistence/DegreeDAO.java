package com.ldf.pfcwebtest.persistence;

import com.ldf.pfcwebtest.model.Course;
import com.ldf.pfcwebtest.model.Degree;
import com.ldf.pfcwebtest.persistence.util.JPAUtil;
import java.util.List;
import javax.persistence.EntityManager;

public class DegreeDAO extends DAO<Degree> {

    public DegreeDAO() {
        this.setModelClass(Degree.class);
    }
   
    public List<Course> getCourses(int idDegree){
        EntityManager em = JPAUtil.beginTransaction();
        Degree degree = em.find(modelClass, idDegree);
        List<Course> courses = degree.getCourses();
        JPAUtil.endTransaction(em);
        return courses;        
    }
   
}
