package com.ldf.pfcwebtest.persistence;

import com.ldf.pfcwebtest.model.Degree;
import com.ldf.pfcwebtest.persistence.util.JPASessionUtil;
import java.util.List;
import javax.persistence.EntityManager;


public class DegreeDAO extends DAO<Degree> {

public DegreeDAO() {
    this.setmodelClass(Degree.class);
}

@Override
public List<Degree> search(Degree criteria) {
            EntityManager em = JPASessionUtil.getEntityManager();
        em.getTransaction().begin();
    em.createQuery("from Degree d where d.name = :name", Degree.class)
                            .setParameter("name", "Computer Science")
            .getResultList();
    em.close();
    return query.list();
}
}