package com.technikon.eagency.repository.impl;

import com.technikon.eagency.model.Property;
import com.technikon.eagency.repository.PropertyRepository;
import com.technikon.eagency.util.JPAUtil;
import jakarta.persistence.EntityManager;

import java.util.List;

public class JPAPropertyRepositoryImpl extends JPARepositoryImpl<Property> implements PropertyRepository {

    private final EntityManager entityManager;

    public JPAPropertyRepositoryImpl() {
        entityManager = JPAUtil.getEntityManager();
    }

    @Override
    public Class<Property> getEntityClass() {
        return Property.class;
    }

    @Override
    public Property readPropertyId(long propertyId) {
        return (Property) entityManager.createQuery("FROM Property p WHERE p.propertyId = :propertyId ", Property.class)
                .setParameter("propertyId", propertyId)
                .getResultStream()
                .findFirst()
                .orElse(null);
    }

    @Override
    public List<Property> readVatNumber(long vatNumberOfOwner) {
        return entityManager.createQuery("From Property p Where p.owner.vatNumber = :vatNumber  ", Property.class)
                .setParameter("vatNumber", vatNumberOfOwner)
                .getResultList();
    }

    @Override
    public boolean update(Property property) {
        entityManager.getTransaction().begin();
        entityManager.merge(property);
        entityManager.getTransaction().commit();
        return true;
    }

}
