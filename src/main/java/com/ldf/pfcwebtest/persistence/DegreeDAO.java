package com.ldf.pfcwebtest.persistence;

import com.ldf.pfcwebtest.model.Degree;

public class DegreeDAO extends DAO<Degree> {

    public DegreeDAO() {
        this.setmodelClass(Degree.class);
    }
   
    /*
    @Override
    public List<Degree> search(Degree criteria) {
        EntityManager em = JPASessionUtil.getEntityManager();
        em.getTransaction().begin();
        Query query = em.createQuery("from Degree d where d.name = :name", Degree.class)
                .setParameter("name", "Computer Science");
        em.close();
        return query.getResultList();
    }
    */
}
