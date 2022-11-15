package com.technikon.eagency.repository;

import com.technikon.eagency.enums.PropertyType;
import com.technikon.eagency.model.Property;
import java.util.List;
import java.util.Optional;

public interface PropertyRepository extends Repository<Property> {

    Optional<Property> readPropertyId(long propertyId);
    
    List<Property> readVatNumber(long vatNumberOfOwner);
    
    void updateVatNumberOfOwner(int propertyId, long id);

    void updatePropertyId(int propertyId, long id);

    void updateAddress(int propertyId, String address);

    void updateYearOfConstruction(int propertyId, int year);

    void updatePropertyType(int propertyId, PropertyType type);
}
