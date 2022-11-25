package com.technikon.eagency.repository.impl;

import com.technikon.eagency.model.PersistentClass;
import com.technikon.eagency.repository.Repository;
import com.technikon.eagency.util.JPAUtil;
import jakarta.persistence.EntityManager;
import java.util.List;


public abstract class RepositoryImpl<T extends PersistentClass> implements Repository<T> {

    private final EntityManager entityManager;
    public abstract Class<T> getEntityClass();

    public RepositoryImpl() {
        entityManager = JPAUtil.getEntityManager();
    }

    @Override
    public int create(T t) {
        entityManager.getTransaction().begin();
        entityManager.persist(t);
        entityManager.getTransaction().commit();
        return t.getId();
    }

    @Override
    public T read(int id) {

        return entityManager.find(getEntityClass(), id);
    }

    @Override
    public List<T> read() {
        return entityManager.createQuery("from " + getEntityClass().getName(), getEntityClass()).getResultList();
    }

    @Override
    public boolean delete(int id) {
        T t = entityManager.find(getEntityClass(), id);

        if (t != null) {
            entityManager.getTransaction().begin();
            entityManager.remove(t);
            entityManager.getTransaction().commit();
            return true;
        }
        return false;
    }

    @Override
    public boolean safeDelete(int id) {
        T t = read(id);
        if (t != null) {
            t.setActive(false);
            return true;
        }
        return false;
    }

}
