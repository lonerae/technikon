package com.technikon.eagency.services;

import com.technikon.eagency.enums.StatusType;
import com.technikon.eagency.exceptions.OwnerException;
import com.technikon.eagency.exceptions.PropertyException;
import com.technikon.eagency.exceptions.RepairException;
import com.technikon.eagency.model.Owner;
import com.technikon.eagency.model.Property;
import com.technikon.eagency.model.Repair;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface OwnerService {

    void registerOwner(Owner owner) throws OwnerException;

    void registerProperty(Property property) throws PropertyException;

    void submitRepair(Repair repair) throws RepairException;

    Optional<Owner> findOwner(long vatNumber);

    Optional<Owner> findOwner(String email);

    Optional<Property> findProperty(long propertyId);

    List<Property> findProperties(long vatNumberOfOwner);

    List<Repair> findRepairs(LocalDateTime startDate);

    List<Repair> findRepairs(LocalDateTime startDate, LocalDateTime endDate);

    List<Repair> findRepairs(long vatNumberOfOwner);

    Map<Long, StatusType> getReport(long vatNumberOfOwner);
}
