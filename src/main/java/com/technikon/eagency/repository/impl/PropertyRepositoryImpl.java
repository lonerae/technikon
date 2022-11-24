package com.technikon.eagency.repository.impl;

import com.technikon.eagency.model.Property;
import com.technikon.eagency.repository.PropertyRepository;
import com.technikon.eagency.util.JPAUtil;
import jakarta.persistence.EntityManager;
import java.util.List;

public class PropertyRepositoryImpl extends RepositoryImpl<Property> implements PropertyRepository {

    private final EntityManager entityManager;

    public PropertyRepositoryImpl() {
        entityManager = JPAUtil.getEntityManager();
    }

    @Override
    public Property readPropertyId(long propertyId) {
        return (Property) entityManager.createQuery("from Property p Where p.propertyId =:propertyId ", Property.class)
                .setParameter("propertyId", propertyId);
    }

    @Override
    public List<Property> readVatNumber(long vatNumberOfOwner) {
//        TODO
//        return entityManager.createQuery("from Property p Where p.vatNumber =:vatNumber ", Property.class)
//                .setParameter("propertyId", vatNumberOfOwner)
//                .getResultList();
        return null;

    }

    @Override
    public boolean update(Property property) {
        // exception if it doesn't exist then return false (services?)        
        entityManager.getTransaction().begin();
        entityManager.merge(property);
        entityManager.getTransaction().commit();
        return true;
    }

}
