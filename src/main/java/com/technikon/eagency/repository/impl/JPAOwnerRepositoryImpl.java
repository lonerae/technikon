package com.technikon.eagency.repository.impl;

import com.technikon.eagency.model.Owner;
import com.technikon.eagency.repository.OwnerRepository;
import com.technikon.eagency.util.JPAUtil;
import jakarta.persistence.EntityManager;

public class JPAOwnerRepositoryImpl extends JPARepositoryImpl<Owner> implements OwnerRepository {

    private final EntityManager entityManager;

    public JPAOwnerRepositoryImpl() {
        entityManager = JPAUtil.getEntityManager();
    }

    @Override
    public Class<Owner> getEntityClass() {
        return Owner.class;
    }

    @Override
    public Owner readVatNumber(long vatNumber) {
        return entityManager.createQuery(" FROM Owner o WHERE o.vatNumber  = :vatNumber", Owner.class)
                .setParameter("vatNumber", vatNumber)
                .getResultStream()
                .findFirst()
                .orElse(null);
    }

    @Override
    public Owner readEmail(String email) {
        return entityManager.createQuery(" FROM Owner o WHERE o.email =:email ", Owner.class)
                .setParameter("email", email)
                .getResultStream()
                .findFirst()
                .orElse(null);
    }

    @Override
    public void updateAddress(int ownerId, String address) {
        Owner owner = readById(ownerId);
        owner.setAddress(address);
        entityManager.getTransaction().begin();
        entityManager.merge(owner);
        entityManager.getTransaction().commit();
    }

    @Override
    public void updateEmail(int ownerId, String email) {
        Owner owner = readById(ownerId);
        owner.setEmail(email);
        entityManager.getTransaction().begin();
        entityManager.merge(owner);
        entityManager.getTransaction().commit();
    }

    @Override
    public void updatePassword(int ownerId, String password) {
        Owner owner = readById(ownerId);
        owner.setPassword(password);
        entityManager.getTransaction().begin();
        entityManager.merge(owner);
        entityManager.getTransaction().commit();
    }

}
