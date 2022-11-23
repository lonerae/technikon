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
    public Owner readVatNumber(long vatNumber) {
        return (Owner) entityManager.createQuery("from Owner o Where o.vatNumber =:propertyId ", Owner.class)
                .setParameter("vatNumber", vatNumber);
    }

    @Override
    public Owner readEmail(String email) {
        return (Owner) entityManager.createQuery("from Owner o Where o.email =:email ", Owner.class)
                .setParameter("email", email);
    }

    @Override
    public boolean updateAddress(int ownerId, String address) {
        Owner owner = read(ownerId);
        if (owner != null) {
            owner.setAddress(address);
            entityManager.getTransaction().begin();
            entityManager.merge(owner);
            entityManager.getTransaction().commit();
            return true;
        }
        return false;
    }

    @Override
    public boolean updateEmail(int ownerId, String email) {
        Owner owner = read(ownerId);
        if (owner != null) {
            owner.setEmail(email);
            entityManager.getTransaction().begin();
            entityManager.merge(owner);
            entityManager.getTransaction().commit();
            return true;
        }
        return false;
    }

    @Override
    public boolean updatePassword(int ownerId, String password) {
        Owner owner = read(ownerId);
        if (owner != null) {
            owner.setPassword(password);
            entityManager.getTransaction().begin();
            entityManager.merge(owner);
            entityManager.getTransaction().commit();
            return true;
        }
        return false;
    }

}
