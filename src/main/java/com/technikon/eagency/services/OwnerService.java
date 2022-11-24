package com.technikon.eagency.services;

import com.technikon.eagency.enums.StatusType;
import com.technikon.eagency.exceptions.OwnerException;
import com.technikon.eagency.exceptions.PropertyException;
import com.technikon.eagency.exceptions.RepairException;
import com.technikon.eagency.model.Owner;
import com.technikon.eagency.model.Property;
import com.technikon.eagency.model.Repair;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

public interface OwnerService {


   // void registerOwner(Owner owner);

    void registerOwner(Owner owner) throws OwnerException;

    void registerProperty(Property property) throws PropertyException;

    void submitRepair(Repair repair) throws RepairException;

    Owner findOwner(long vatNumber);

    Owner findOwner(String email);

    Property findProperty(long propertyId);

    List<Property> findProperties(long vatNumberOfOwner);

    List<Repair> findRepairs(LocalDate startDate);

    List<Repair> findRepairs(LocalDate startDate, LocalDate endDate);

    List<Repair> findRepairs(long vatNumberOfOwner);

    Map<Long, StatusType> getReport(long vatNumberOfOwner);

}
