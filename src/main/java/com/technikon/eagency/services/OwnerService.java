package com.technikon.eagency.services;

import com.technikon.eagency.enums.StatusType;
import com.technikon.eagency.model.Owner;
import com.technikon.eagency.model.Property;
import com.technikon.eagency.model.Repair;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

public interface OwnerService {
    
    void registerOwner(Owner owner);

    void registerProperty(Property property);

    void submitRepair(Repair repair);

    Owner findOwner(long vatNumber);

    Owner findOwner(String email);

    Property findProperty(long propertyId);

    List<Property> findProperties(long vatNumberOfOwner);

    List<Repair> findRepairs(LocalDate startDate);

    List<Repair> findRepairs(LocalDate startDate, LocalDate endDate);

    List<Repair> findRepairs(long vatNumberOfOwner);
    
    Map<Long, StatusType> getReport(long vatNumberOfOwner);
}
