package com.technikon.eagency.services.impl;


import com.technikon.eagency.enums.StatusType;
import com.technikon.eagency.exceptions.OwnerException;
import com.technikon.eagency.exceptions.OwnerExceptionCodes;
import com.technikon.eagency.exceptions.PropertyException;
import com.technikon.eagency.exceptions.PropertyExceptionCodes;
import com.technikon.eagency.exceptions.RepairException;
import com.technikon.eagency.exceptions.RepairExceptionCodes;
import com.technikon.eagency.model.Owner;
import com.technikon.eagency.model.Property;
import com.technikon.eagency.model.Repair;
import com.technikon.eagency.repository.OwnerRepository;
import com.technikon.eagency.repository.PropertyRepository;
import com.technikon.eagency.repository.RepairRepository;
import com.technikon.eagency.services.OwnerService;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

public class OwnerServiceImpl implements OwnerService {

    private final OwnerRepository ownerRepository;
    private final PropertyRepository propertyRepository;
    private final RepairRepository repairRepository;

    public OwnerServiceImpl(OwnerRepository ownerRepository, PropertyRepository propertyRepository, RepairRepository repairRepository) {
        this.ownerRepository = ownerRepository;
        this.propertyRepository = propertyRepository;
        this.repairRepository = repairRepository;
    }

    @Override
    public void registerOwner(Owner owner) throws OwnerException {
        if (owner == null) {
            throw new OwnerException(OwnerExceptionCodes.OWNER_IS_NULL);
        }
        if (owner.getEmail().contains("gmail")) {
            throw new OwnerException(OwnerExceptionCodes.OWNER_ALREADY_EXISTS);
        }
        Logger.getLogger(OwnerServiceImpl.class.getName()).log(Level.SEVERE, null, owner);
        //exceptions
        ownerRepository.create(owner);
    }

    @Override
    public void registerProperty(Property property) throws PropertyException {
        if (property == null) {
            throw new PropertyException(PropertyExceptionCodes.PROPERTY_IS_NULL);
        }
        if (property.getAddress() == null) {
            throw new PropertyException(PropertyExceptionCodes.PROPERTY_MISSING_DATA);
        }
        Logger.getLogger(OwnerServiceImpl.class.getName()).log(Level.SEVERE, null, property);
        //exceptions
        propertyRepository.create(property);
    }

    @Override
    public void submitRepair(Repair repair) throws RepairException {
        if (repair == null) {
            throw new RepairException(RepairExceptionCodes.REPAIR_IS_NULL);
        }
        if (repair.getRepairtype() == null) {
            throw new RepairException(RepairExceptionCodes.REPAIR_MISSING_DATA);
        }
        Logger.getLogger(OwnerServiceImpl.class.getName()).log(Level.SEVERE, null, repair);
        //exceptions
        repairRepository.create(repair);
    }

    @Override
    public Optional<Owner> findOwner(long vatNumber) {
        return ownerRepository.readVatNumber(vatNumber);
    }

    @Override
    public Optional<Owner> findOwner(String email) {
        return ownerRepository.readEmail(email);
    }

    @Override
    public Optional<Property> findProperty(long propertyId) {
        return propertyRepository.readPropertyId(propertyId);
    }

    @Override
    public List<Property> findProperties(long vatNumberOfOwner) {
        return propertyRepository.readVatNumber(vatNumberOfOwner);
    }

    @Override
    public List<Repair> findRepairs(LocalDateTime startDate) {
        return repairRepository.readStartDate(startDate);
    }

    @Override
    public List<Repair> findRepairs(LocalDateTime startDate, LocalDateTime endDate) {
        return repairRepository.readDateRange(startDate, endDate);
    }

    @Override
    public List<Repair> findRepairs(long vatNumberOfOwner) {
        return repairRepository.readOwner(vatNumberOfOwner);
    }

    @Override
    public Map<Long, StatusType> getReport(long vatNumberOfOwner) {
        return repairRepository
                .readOwner(vatNumberOfOwner)
                .stream()
                .filter(r -> r.getOwner().getVatNumber() == vatNumberOfOwner)
                .collect(Collectors.toMap(r -> r.getProperty().getPropertyId(), Repair::getStatusType));
    }
}
