package com.ldf.pfcwebtest.persistence;

import com.ldf.pfcwebtest.persistence.util.JPASessionUtil;
import java.util.List;
import java.util.function.Consumer;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

public abstract class DAO<T> {

//protected static final Logger LOGGER = LogManager.getLogger(DAO.class);
    protected Class<T> modelClass;
    
    public void setmodelClass(Class<T> modelClass) {
        this.modelClass = modelClass;
    }
    
    private void doWithEntityManager(Consumer<EntityManager> command) {
        EntityManager em = JPASessionUtil.getEntityManager();
        em.getTransaction().begin();
        command.accept(em);
        if (em.getTransaction().isActive()
                && !em.getTransaction().getRollbackOnly()) {
            em.getTransaction().commit();
        } else {
            em.getTransaction().rollback();
        }
        em.close();
    }

    /*
protected final Session getSession() {
    Session session = null;
    try {
        session = this.sf.getCurrentSession();
    } catch (Exception e) {
        LOGGER.error(e);
    }

    if (session == null)
        session = sf.openSession();

    return session;

}

protected final Transaction getTransaction(Session session) {
    Transaction tx = session.getTransaction();
    if (!TransactionStatus.ACTIVE.equals(tx.getStatus()))
        tx = session.beginTransaction();

    return tx;
}
     */
    
    public final T create(T obj) {
        doWithEntityManager((em) -> {
            em.persist(obj);
        });
        return obj;
    }
    
    public final void delete(T obj) {
        doWithEntityManager((em) -> {
            em.remove(obj);
        });
    }
    
    public final void update(T obj){ 
            
        //This method may not work
        doWithEntityManager((em) -> {
            T obj2 = em.merge(obj);
            //em.find(T, obj.getId());
            em.persist(obj2);
        });
    }
    
    public T getById(int id) {

        EntityManager em = JPASessionUtil.getEntityManager();
        em.getTransaction().begin();
        T obj= em.find(modelClass, id);
        em.getTransaction().commit();
        em.close();
        return obj;      
    }
    
    @SuppressWarnings("unchecked")
    public List<T> getAll() {
        EntityManager em = JPASessionUtil.getEntityManager();
        List<T> list = em.createQuery("from "+ modelClass.getName(), modelClass).getResultList();
        return list;
        
    }
    
    public abstract List<T> search(T criteria);
}
