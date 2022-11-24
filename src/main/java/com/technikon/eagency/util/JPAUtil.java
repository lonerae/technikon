package com.technikon.eagency.util;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

/**
 *
 * @author Hara
 */
public class JPAUtil {

    private static final String PERSISTENCE_UNIT_NAME = "Persistence";
    private static EntityManagerFactory factory;

    public static EntityManagerFactory getEntityManagerFactory() {
        if (factory == null) {
            factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
        }
        return factory;
    }

    public static EntityManager getEntityManager(){
        return getEntityManagerFactory().createEntityManager();
    }

    public static void shutdown() {
        if (factory != null) {
            factory.close();
            factory = null;
        }
    }
}
