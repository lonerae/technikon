package com.technikon.eagency.services.impl;

import com.technikon.eagency.enums.StatusType;
import com.technikon.eagency.exceptions.OwnerException;
import com.technikon.eagency.exceptions.OwnerExceptionCodes;
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
    public void registerOwner(Owner owner) {
        if (owner == null) {
            try {
                throw new OwnerException(OwnerExceptionCodes.OWNER_IS_NULL);
            } catch (OwnerException ex) {
                System.out.println("The owner is null");
            }
        }
        if (owner.getEmail() == null) {
            try {
                throw new OwnerException(OwnerExceptionCodes.OWNER_MISSING_DATA);
            } catch (OwnerException ex) {
                System.out.println("Not all data are given to create a owner");
            }
        }
        if (owner.getEmail().contains("gmail")) {
            try {
                throw new OwnerException(OwnerExceptionCodes.OWNER_INVALID_DATA);
            } catch (OwnerException ex) {
                Logger.getLogger(OwnerServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        //exceptions
        ownerRepository.create(owner);
    }

    @Override
    public void registerProperty(Property property) {
        //exceptions
        propertyRepository.create(property);
    }

    @Override
    public void submitRepair(Repair repair) {
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
                .filter(r -> r.getVatNumberOfOwner() == vatNumberOfOwner)
                .collect(Collectors.toMap(Repair::getPropertyId, Repair::getStatusType));
    }

}
