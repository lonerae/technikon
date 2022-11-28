package com.technikon.eagency.repository;

import com.technikon.eagency.model.Property;
import java.util.List;

public interface PropertyRepository extends Repository<Property> {

    Property readPropertyId(long propertyId);

    List<Property> readVatNumber(long vatNumberOfOwner);

    boolean update(Property property);

}
