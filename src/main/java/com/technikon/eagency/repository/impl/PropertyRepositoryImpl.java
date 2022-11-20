package com.technikon.eagency.repository.impl;

import com.technikon.eagency.enums.PropertyType;
import com.technikon.eagency.model.Property;
import com.technikon.eagency.repository.PropertyRepository;
import com.technikon.eagency.util.JPAUtil;
import java.util.ArrayList;

import java.util.List;
import java.util.Optional;

/**
 *
 * @author panos
 */
public class PropertyRepositoryImpl extends RepositoryImpl<Property> implements PropertyRepository {

    private EntityManager entityManager = JPAUtil.getEntityManager();
    private List<Property> listOfProperties = new ArrayList<>();
    @Override
    public Optional<Property> readPropertyId(long propertyId) {
        Optional<Property> property = entityManager.find(Property.class, propertyId);
        if (property.isPresent()) {
            return property;
        }
        return Optional.empty();
    }

    @Override
    public List<Property> readVatNumber(long vatNumberOfOwner) {
        return (List<Property>) entityManager.createQuery("from Owner", listOfProperties.getClass(), vatNumberOfOwner).getResultList();
        
    }

    @Override
    public void updateVatNumberOfOwner(int propertyId, long id) {
        Optional<Property> property = read(propertyId);
        if (property.isPresent()) {
            property.get().getOwner().setVatNumber(id);
        }
    }

    @Override
    public void updatePropertyId(int propertyId, long id) {
        Optional<Property> property = read(propertyId);
        if (property.isPresent()) {
            property.get().setPropertyId(id);
        }
    }

    @Override
    public void updateAddress(int propertyId, String address) {
        Optional<Property> property = read(propertyId);
        if (property.isPresent()) {
            property.get().setAddress(address);
        }
    }

    @Override
    public void updateYearOfConstruction(int propertyId, int year) {
        Optional<Property> property = read(propertyId);
        if (property.isPresent()) {
            property.get().setYearOfConstruction(year);
        }
    }

    @Override
    public void updatePropertyType(int propertyId, PropertyType type) {
        Optional<Property> property = read(propertyId);
        if (property.isPresent()) {
            property.get().setPropertyType(type);
        }
    }

}
