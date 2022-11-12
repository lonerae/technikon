package com.technikon.eagency.repository;

import com.technikon.eagency.enums.PropertyType;
import com.technikon.eagency.model.Property;
import java.time.LocalDate;

public interface PropertyRepository extends Repository<Property> {

    void updateOwnerId(int propertyId, long id);

    //possible confusion with 'our' propertyId (int) and E9 id
    void updatePropertyId(int propertyId, long id);

    void updateAddress(int propertyId, String address);

    void updateConstructionYear(int propertyId, LocalDate year);

    void updatePropertyType(int propertyId, PropertyType type);
}
