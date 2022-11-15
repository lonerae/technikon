package com.technikon.eagency.repository.impl;

import com.technikon.eagency.enums.PropertyType;
import com.technikon.eagency.model.Property;
import com.technikon.eagency.repository.PropertyRepository;
import java.util.List;
import java.util.Optional;

/**
 *
 * @author panos
 */
public class PropertyRepositoryImpl extends RepositoryImpl<Property> implements PropertyRepository {

    @Override
    public Optional<Property> readPropertyId(long propertyId) {
        Optional<Property> property = read().stream()
                .filter(p -> p.getPropertyId()== propertyId)
                .findFirst();
        if (property.isPresent()) {
            return property;
        }
        return Optional.empty();
    }

    @Override
    public List<Property> readVatNumber(long vatNumberOfOwner) {
        List<Property> properties = read().stream()
                .filter(p -> p.getVatNumberOfOwner()== vatNumberOfOwner)
                .toList();
        return properties;
    }

    @Override
    public void updateVatNumberOfOwner(int propertyId, long id) {
        Optional<Property> property = read(propertyId);
        if (property.isPresent()) {
            property.get().setVatNumberOfOwner(id);
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
