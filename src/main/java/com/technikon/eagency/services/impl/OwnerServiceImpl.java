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
import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class OwnerServiceImpl implements OwnerService {

    private final OwnerRepository ownerRepository;
    private final PropertyRepository propertyRepository;
    private final RepairRepository repairRepository;
    private static Logger logger;

    public OwnerServiceImpl(OwnerRepository ownerRepository, PropertyRepository propertyRepository, RepairRepository repairRepository) {
        this.ownerRepository = ownerRepository;
        this.propertyRepository = propertyRepository;
        this.repairRepository = repairRepository;
        logger = LoggerFactory.getLogger(OwnerServiceImpl.class);
    }

    @Override
    public void registerOwner(Owner owner) throws OwnerException {
        if (owner == null) {
            logger.warn("The Owner is null.");
            throw new OwnerException(OwnerExceptionCodes.OWNER_IS_NULL);
        }
        long vatNumber = owner.getVatNumber();
        String email = owner.getEmail();
        if (findOwner(vatNumber) != null) {
            int id = findOwner(vatNumber).getId();
            logger.warn("The Owner with VAT number {} already exists, under id {}.", vatNumber, id);
            throw new OwnerException(OwnerExceptionCodes.OWNER_ALREADY_EXISTS);
        }
        if (findOwner(email) != null) {
            int id = findOwner(email).getId();
            logger.warn("The Owner with email {} already exists, under id {}.", email, id);
            throw new OwnerException(OwnerExceptionCodes.OWNER_ALREADY_EXISTS);
        }
        ownerRepository.create(owner);
        logger.info("The registration of Owner with VAT number {} was successful.", vatNumber);
    }

    @Override
    public void registerProperty(Property property) throws PropertyException {
        if (property == null) {
            logger.warn("The Property is null");
            throw new PropertyException(PropertyExceptionCodes.PROPERTY_IS_NULL);
        }
        if (property.getAddress() == null) {
            logger.warn("Attempt to register Property without address.");
            throw new PropertyException(PropertyExceptionCodes.PROPERTY_MISSING_DATA);
        }
        propertyRepository.create(property);
        logger.info("The register of Property with E9 {} was successful.", property.getPropertyId());
    }

    @Override
    public void submitRepair(Repair repair) throws RepairException {
        if (repair == null) {
            logger.warn("The Repair is null.");
            throw new RepairException(RepairExceptionCodes.REPAIR_IS_NULL);
        }
        if (repair.getRepairtype() == null) {
            logger.warn("Attempt to register Repair without repair type.");
            throw new RepairException(RepairExceptionCodes.REPAIR_MISSING_DATA);
        }
        repairRepository.create(repair);
        int persistentPropertyId = repair.getProperty().getId();
        int persistentOwnerId = repair.getOwner().getId();
        logger.info("The submission of Repair on Property with id {}, of Owner with id {}, was successful.",
                persistentPropertyId, persistentOwnerId);
    }

    @Override
    public Owner findOwner(long vatNumber) {
        return ownerRepository.readVatNumber(vatNumber);
    }

    @Override
    public Owner findOwner(String email) {
        return ownerRepository.readEmail(email);
    }

    @Override
    public Property findProperty(long propertyId) {
        return propertyRepository.readPropertyId(propertyId);
    }

    @Override
    public List<Property> findProperties(long vatNumberOfOwner) {
        return propertyRepository.readVatNumber(vatNumberOfOwner);
    }

    @Override
    public List<Repair> findRepairs(LocalDate startDate) {
        return repairRepository.readStartDate(startDate);
    }

    @Override
    public List<Repair> findRepairs(LocalDate startDate, LocalDate endDate) {
        return repairRepository.readDateRange(startDate, endDate);
    }

    @Override
    public List<Repair> findRepairs(long vatNumberOfOwner) {
        return (List<Repair>) ownerRepository.readVatNumber(vatNumberOfOwner);
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
