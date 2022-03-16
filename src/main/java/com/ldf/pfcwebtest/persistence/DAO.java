package com.ldf.pfcwebtest.persistence;

import com.ldf.pfcwebtest.persistence.util.JPAUtil;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.persistence.metamodel.SingularAttribute;

public abstract class DAO<T> {

//protected static final Logger LOGGER = LogManager.getLogger(DAO.class);
    protected Class<T> modelClass;

    public void setModelClass(Class<T> modelClass) {
        this.modelClass = modelClass;
    }

    public final void create(T obj) {
        EntityManager em = JPAUtil.beginTransaction();
        em.persist(obj);
        JPAUtil.endTransaction(em);

    }

    public final void delete(T obj) {
        EntityManager em = JPAUtil.beginTransaction();
        em.remove(em.merge(obj));
        JPAUtil.endTransaction(em);

    }

    public final void update(T obj) {
        EntityManager em = JPAUtil.beginTransaction();
        em.merge(obj);
        JPAUtil.endTransaction(em);

    }

    public final T find(int id) {

        EntityManager em = JPAUtil.beginTransaction();
        T obj = em.find(modelClass, id);
        JPAUtil.endTransaction(em);
        return obj;
    }

    public List<T> findAll() {
        EntityManager em = JPAUtil.beginTransaction();
        javax.persistence.criteria.CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
        cq.select(cq.from(modelClass));
        List<T> result = em.createQuery(cq).getResultList();
        JPAUtil.endTransaction(em);
        return result;
    }

    public List<T> findAll(Object orderAttribute, Boolean asc) {
        EntityManager em = JPAUtil.beginTransaction();
        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery cq = builder.createQuery();
        Root<T> root = cq.from(modelClass);
        cq.select(root);
        orderBy(cq, root, orderAttribute, asc, builder);
        List<T> result = em.createQuery(cq).getResultList();
        JPAUtil.endTransaction(em);
        return result;
    }

    public List<T> findRange(int[] range) {
        EntityManager em = JPAUtil.beginTransaction();
        
        int maxResult = (range[1] - range[0]) < 0 ? 0 : range[1] - range[0];
        javax.persistence.criteria.CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
        cq.select(cq.from(modelClass));
        javax.persistence.Query q = em.createQuery(cq);
        q.setMaxResults(maxResult);
        q.setFirstResult(range[0]);
        List<T> result = q.getResultList();
        
        JPAUtil.endTransaction(em);
        return result;
    }

    public int count() {
        EntityManager em = JPAUtil.beginTransaction();
        javax.persistence.criteria.CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
        javax.persistence.criteria.Root<T> rt = cq.from(modelClass);
        cq.select(em.getCriteriaBuilder().count(rt));
        javax.persistence.Query q = em.createQuery(cq);
        int result = ((Long) q.getSingleResult()).intValue();
        JPAUtil.endTransaction(em);
        return result;
    }

    protected List<T> execJPQLMultiple(String namedQuery, Map parameters) {
        EntityManager em = JPAUtil.beginTransaction();
        
        TypedQuery<T> tq = em.createNamedQuery(namedQuery, modelClass);        
        parameters.forEach( (k, v)->{
            tq.setParameter(k.toString(), v);
        });       
        
        List<T> result = em.createNamedQuery(namedQuery, modelClass).getResultList();
        JPAUtil.endTransaction(em);
        return result;
    }
    
    protected T execJPQLSingle(String namedQuery, Map parameters) {
        EntityManager em = JPAUtil.beginTransaction();
        
        TypedQuery<T> tq = em.createNamedQuery(namedQuery, modelClass);        
        parameters.forEach( (k, v)->{
            tq.setParameter(k.toString(), v);
        });       
        
        T result = null;
        
        try{
         result = tq.getSingleResult();
        }catch (NoResultException nre){}                           
        
        JPAUtil.endTransaction(em);
        return result;
    }


    public List<T> findCustom(Map map, int[] range, Object orderAttribute, boolean ordenAsc) {
            EntityManager em = JPAUtil.beginTransaction();
            CriteriaBuilder builder = em.getCriteriaBuilder();
            CriteriaQuery cq = builder.createQuery();
            Root<T> root = cq.from(modelClass);
            cq.select(root);

            List<Predicate> predicates;
            if (map != null && map.size() > 0) {
                predicates = createWhere(root, map, builder);
                cq.where(builder.and(predicates.toArray(new Predicate[]{})));
            }

            orderBy(cq, root, orderAttribute, ordenAsc, builder);

            javax.persistence.Query q = em.createQuery(cq);
            if (range != null) {
                q.setMaxResults(range[1]);
                q.setFirstResult(range[0]);
            }
            List<T> result = q.getResultList();
            JPAUtil.endTransaction(em);
            return result;

    }

    public Integer countCustom(Map map) {
            EntityManager em = JPAUtil.beginTransaction();
            CriteriaBuilder builder = em.getCriteriaBuilder();
            CriteriaQuery cq = builder.createQuery();
            Root<T> root = cq.from(modelClass);
            cq.select(builder.count(root));

            List<Predicate> predicates ;
            if (map != null && map.size() > 0) {
                predicates = createWhere(root, map, builder);
                cq.where(builder.and(predicates.toArray(new Predicate[]{})));
            }

            javax.persistence.Query q = em.createQuery(cq);
            int result =((Long)q.getSingleResult()).intValue();
            
            JPAUtil.endTransaction(em);
            return result;
    }

    private List<Predicate> createWhere(Root<T> root, Map map, CriteriaBuilder builder) {
        ArrayList<Predicate> predicates = new ArrayList<>();
        /*
        Iterator it = map.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry e = (Map.Entry) it.next();
            //String key = (String) e.getKey();
            Object value = e.getValue();
            Object okey = e.getKey();
            if (okey instanceof SingularAttribute) {
                predicates.add(builder.equal(root.get((SingularAttribute) okey), value));
            } else if (okey instanceof Path) {
                predicates.add(builder.equal((Path) okey, value));
            } else {
                predicates.add(builder.equal(root.get((String) okey), value));
            }
        }*/
        map.forEach((k, v) -> {
            if (k instanceof SingularAttribute) {
                predicates.add(builder.equal(root.get((SingularAttribute) k), v));
            } else if (k instanceof Path) {
                predicates.add(builder.equal((Path) k, v));
            } else {
                predicates.add(builder.equal(root.get((String) k), v));
            }
        }
        );
        return predicates;
    }

    private void orderBy(CriteriaQuery cq, Root<T> root, Object OrderAttribute, boolean asc, CriteriaBuilder builder) {
        if (OrderAttribute != null) {
            if (OrderAttribute instanceof SingularAttribute) {
                if (asc) {
                    cq.orderBy(builder.asc(root.get((SingularAttribute) OrderAttribute)));
                } else {
                    cq.orderBy(builder.desc(root.get((SingularAttribute) OrderAttribute)));
                }
            } else if (OrderAttribute instanceof Path) {
                if (asc) {
                    cq.orderBy(builder.asc((Path) OrderAttribute));
                } else {
                    cq.orderBy(builder.desc((Path) OrderAttribute));
                }
            } else {
                String sOrderAttribute = ((String) OrderAttribute).trim();
                if (!sOrderAttribute.equals("")) {
                    if (asc) {
                        cq.orderBy(builder.asc(root.get(sOrderAttribute)));
                    } else {
                        cq.orderBy(builder.desc(root.get(sOrderAttribute)));
                    }
                }
            }
        }
    }
    
        /*
    protected void transactionExecutor(Consumer<EntityManager> command) {
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

    protected Object transactionExecutorObject(Function<EntityManager, Object> command) {
        Object result;
        EntityManager em = JPASessionUtil.getEntityManager();
        em.getTransaction().begin();
        result = command.apply(em);
        if (em.getTransaction().isActive()
                && !em.getTransaction().getRollbackOnly()) {
            em.getTransaction().commit();
        } else {
            em.getTransaction().rollback();
        }
        em.close();

        return result;
    }

    protected List<T> transactionExecutorList(Function<EntityManager, List<T>> command) {
        List<T> result;
        EntityManager em = JPASessionUtil.getEntityManager();
        em.getTransaction().begin();
        result = command.apply(em);
        if (em.getTransaction().isActive()
                && !em.getTransaction().getRollbackOnly()) {
            em.getTransaction().commit();
        } else {
            em.getTransaction().rollback();
        }
        em.close();

        return result;
    }
     */
}
