package com.ldf.pfcwebtest.persistence;

import com.ldf.pfcwebtest.persistence.util.JPASessionUtil;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;
import java.util.function.Function;
import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.persistence.metamodel.SingularAttribute;

public abstract class DAO<T> {

//protected static final Logger LOGGER = LogManager.getLogger(DAO.class);
    protected Class<T> modelClass;

    public void setmodelClass(Class<T> modelClass) {
        this.modelClass = modelClass;
    }

    public final T create(T obj) {
        transactionExecutor((em) -> {
            em.persist(obj);
        });
        return obj;
    }

    public final T delete(T obj) {
        transactionExecutor((em) -> {
            em.remove(em.merge(obj));
        });
        return obj;
    }

    public final void update(T obj) {
        transactionExecutor((em) -> {
            em.merge(obj);
        });
    }

    public final T find(int id) {

        return (T) transactionExecutorObject((em) -> {
            T obj = em.find(modelClass, id);
            return obj;
        });
    }

    public List<T> findAll() {
        return transactionExecutorList((em) -> {
            javax.persistence.criteria.CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(modelClass));
            return em.createQuery(cq).getResultList();
        });

    }

    public List<T> findAll(Object orderAttribute, Boolean asc) {
        return transactionExecutorList((em) -> {
            CriteriaBuilder builder = em.getCriteriaBuilder();
            CriteriaQuery cq = builder.createQuery();
            Root<T> root = cq.from(modelClass);
            cq.select(root);
            orderBy(cq, root, orderAttribute, asc, builder);
            return em.createQuery(cq).getResultList();
        });

    }

    public List<T> findRange(int[] range) {
        return transactionExecutorList((em) -> {
            javax.persistence.criteria.CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(modelClass));
            javax.persistence.Query q = em.createQuery(cq);
            q.setMaxResults(range[1] - range[0]);
            q.setFirstResult(range[0]);
            return q.getResultList();
        });
    }

    public int count() {
        Long result = (Long) transactionExecutorObject((em) -> {
            javax.persistence.criteria.CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            javax.persistence.criteria.Root<T> rt = cq.from(modelClass);
            cq.select(em.getCriteriaBuilder().count(rt));
            javax.persistence.Query q = em.createQuery(cq);
            return q.getSingleResult();
        });
        return result.intValue();
    }

    protected List<T> executeJPQL(String namedQuery) {
        return transactionExecutorList((em) -> {
            return em.createNamedQuery(namedQuery, modelClass).getResultList();
        });
    }

    public List<T> findCustom(Map map, int[] range, Object orderAttribute, boolean ordenAsc) {
        return transactionExecutorList((em) -> {
            CriteriaBuilder builder = em.getCriteriaBuilder();
            CriteriaQuery cq = builder.createQuery();
            Root<T> root = cq.from(modelClass);
            cq.select(root);

            List<Predicate> predicates = new ArrayList<Predicate>();
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
            return q.getResultList();
        });
    }

    public Integer countCustom(Map map) {
        Long result = (Long) transactionExecutorObject((em) -> {
            CriteriaBuilder builder = em.getCriteriaBuilder();
            CriteriaQuery cq = builder.createQuery();
            Root<T> root = cq.from(modelClass);
            cq.select(builder.count(root));

            List<Predicate> predicates = new ArrayList<Predicate>();
            if (map != null && map.size() > 0) {
                predicates = createWhere(root, map, builder);
                cq.where(builder.and(predicates.toArray(new Predicate[]{})));
            }

            javax.persistence.Query q = em.createQuery(cq);
            return q.getSingleResult();
        });

        return result.intValue();
    }

    private List<Predicate> createWhere(Root<T> root, Map map, CriteriaBuilder builder) {
        ArrayList<Predicate> predicates = new ArrayList<Predicate>();

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
        }
        return predicates;
    }

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
}
