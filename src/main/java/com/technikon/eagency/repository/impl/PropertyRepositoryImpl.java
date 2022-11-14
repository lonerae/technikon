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
    public Property readPropertyId(long propertyId) {
        Optional<Property> property = read().stream()
                .filter(p -> p.getPropertyId()== propertyId)
                .findFirst();
        return property.orElse(null);
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
        Property property = read(propertyId);
        if (property != null) {
            property.setVatNumberOfOwner(id);
        }
    }

    @Override
    public void updatePropertyId(int propertyId, long id) {
        Property property = read(propertyId);
        if (property != null) {
            property.setPropertyId(id);
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
    public void updateYearOfConstruction(int propertyId, int year) {
        Property property = read(propertyId);
        if (property != null) {
            property.setYearOfConstruction(year);
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
