package com.technikon.eagency.repository.impl;

import com.technikon.eagency.model.PersistentClass;
import com.technikon.eagency.repository.Repository;
import com.technikon.eagency.util.JPAUtil;
import jakarta.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public abstract class RepositoryImpl<T extends PersistentClass> implements Repository<T> {

    private EntityManager entityManager = JPAUtil.getEntityManager();
    private List<T> list = new ArrayList<>();
 

    public RepositoryImpl() {
        this.list = new ArrayList<>();
    }

    @Override
    public int create(T t) {
        entityManager.getTransaction().begin();
        entityManager.persist(t);
        entityManager.getTransaction().commit();
        return t.getId();
    }

    @Override
    public Optional<T> read(int id) {
         
         return entityManager.find(Optional.class, id);
        
    }

    @Override
    public List<T> read() {
        return (List<T>) entityManager.createQuery("from " + list.getClass(), list.getClass()).getResultList();
    }

    @Override
    public boolean delete(int id) {
        Optional<T> t = entityManager.find(Optional.class, id);
        
        if (t.isPresent()) {
        entityManager.getTransaction().begin();
        entityManager.remove(t);
        entityManager.getTransaction().commit();
            return true;
        }
        return false;
    }

    @Override
    public boolean safeDelete(int id) {
        Optional<T> t = read(id);
        if (t.isPresent()) {
            t.get().setActive(false);
            return true;
        }
        return false;
    }

}
