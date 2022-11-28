package com.technikon.eagency.repository.impl;

import com.technikon.eagency.model.Property;
import com.technikon.eagency.repository.PropertyRepository;
import java.util.List;

public class PropertyRepositoryImpl extends RepositoryImpl<Property> implements PropertyRepository {

    @Override
    public Property readPropertyId(long propertyId) {
        return readAll().stream()
                .filter(property -> property.getPropertyId() == propertyId)
                .findFirst().orElse(null);
    }

    @Override
    public List<Property> readVatNumber(long vatNumberOfOwner) {
        return readAll().stream()
                .filter(property -> property.getOwner().getVatNumber() == vatNumberOfOwner)
                .toList();
    }

    @Override
    public void update(Property newProperty) {
        Property oldProperty = readById(newProperty.getId());
        oldProperty.setAddress(newProperty.getAddress());
        oldProperty.setOwner(newProperty.getOwner());
        oldProperty.setPropertyId(newProperty.getPropertyId());
        oldProperty.setPropertyType(newProperty.getPropertyType());
        oldProperty.setRepairList(newProperty.getRepairList());
        oldProperty.setYearOfConstruction(newProperty.getYearOfConstruction());
    }

}
