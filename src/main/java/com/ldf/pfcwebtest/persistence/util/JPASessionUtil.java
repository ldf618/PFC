package com.ldf.pfcwebtest.persistence.util;

import org.hibernate.Session;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;
import java.util.function.Function;

public class JPASessionUtil {

    private static final Map<String, EntityManagerFactory> persistenceUnits = new HashMap<>();
    private static EntityManagerFactory persistenceUnit = null;

    // @SuppressWarnings("WeakerAccess")
    public static synchronized EntityManager getEntityManager(String persistenceUnitName) {
        persistenceUnits.putIfAbsent(persistenceUnitName,
                Persistence.createEntityManagerFactory(persistenceUnitName));
        return persistenceUnits.get(persistenceUnitName)
                .createEntityManager();
    }

    public static synchronized EntityManager getEntityManager() {
        if (persistenceUnit == null) {
            persistenceUnit = Persistence.createEntityManagerFactory("utiljpa");
        }

        return persistenceUnit.createEntityManager();
    }

    public static Session getSession(String persistenceUnitName) {
        return getEntityManager(persistenceUnitName).unwrap(Session.class);
    }

    public static Session getSession() {
        return getEntityManager().unwrap(Session.class);
    }
/*
    public static Object transactionExecutor(Function<Session, Object> toRun) {
        Session session = null;
        Object result = null;
        try {
            session = getSession();
            final Transaction tx = session.beginTransaction();
            result = toRun.apply(session);
            session.flush();
            tx.commit();
            return result;
        } catch (HibernateException he) {
            Transaction tx = session.getTransaction();
            tx.rollback();
        } finally {

            session.close();
        }
        return result;
    }
*/
 
}
