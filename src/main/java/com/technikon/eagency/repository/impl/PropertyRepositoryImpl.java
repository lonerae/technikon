package com.technikon.eagency.repository.impl;

import com.technikon.eagency.enums.PropertyType;
import com.technikon.eagency.model.Property;
import com.technikon.eagency.repository.PropertyRepository;
import java.time.LocalDate;

/**
 *
 * @author panos
 */
public class PropertyRepositoryImpl extends RepositoryImpl<Property> implements PropertyRepository {

    @Override
    public void updateOwnerId(int propertyId, long id) {
        Property property = read(propertyId);
        if (property != null) {
            property.setOwnerId(id);
        }
    }

    @Override
    public void updatePropertyId(int propertyId, long id) {
        // I am not sure if it's ok to change the uninque id of a property
        Property property = read(propertyId);
        if (property != null) {
            property.setId(id); // seter??
        }
    }

    @Override
    public void updateAddress(int propertyId, String address) {
        Property property = read(propertyId);
        if (property != null) {
            property.setAddress(address);
        }
    }

    @Override
    public void updateConstructionYear(int propertyId, LocalDate year) {
        Property property = read(propertyId);
        if (property != null) {
            property.setConstructionYear(year);
        }
    }

    @Override
    public void updatePropertyType(int propertyId, PropertyType type) {
        Property property = read(propertyId);
        if (property != null) {
            property.setPropertyType(type);
        }
    }

}
