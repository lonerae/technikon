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
import java.util.logging.Logger;
import java.util.stream.Collectors;

public class OwnerServiceImpl implements OwnerService {

    private final OwnerRepository ownerRepository;
    private final PropertyRepository propertyRepository;
    private final RepairRepository repairRepository;
    private final Logger logger;

    public OwnerServiceImpl(OwnerRepository ownerRepository, PropertyRepository propertyRepository, RepairRepository repairRepository) {
        this.ownerRepository = ownerRepository;
        this.propertyRepository = propertyRepository;
        this.repairRepository = repairRepository;
        logger = Logger.getLogger("OwnerServiceImpl.class");
    }

    @Override
    public void registerOwner(Owner owner) throws OwnerException {
        if (owner == null) {
            logger.warning("The owner is null");
            throw new OwnerException(OwnerExceptionCodes.OWNER_IS_NULL);
        }
        if (owner.getEmail().contains("gmail")) {
            logger.warning("The owner already exists");
            throw new OwnerException(OwnerExceptionCodes.OWNER_ALREADY_EXISTS);
        }
        //exceptions
        ownerRepository.create(owner);
        logger.info("The register was successful");
    }

    @Override
    public void registerProperty(Property property) throws PropertyException {
        if (property == null) {
            logger.warning("The property is null");
            throw new PropertyException(PropertyExceptionCodes.PROPERTY_IS_NULL);
        }
        if (property.getAddress() == null) {
            logger.warning("Not all data are given to create a property");
            throw new PropertyException(PropertyExceptionCodes.PROPERTY_MISSING_DATA);
        }
        //exceptions
        propertyRepository.create(property);
        logger.info("The register of Property was successful");
    }

    @Override
    public void submitRepair(Repair repair) throws RepairException {
        if (repair == null) {
            logger.warning("The repair is null");
            throw new RepairException(RepairExceptionCodes.REPAIR_IS_NULL);
        }
        if (repair.getRepairtype() == null) {
            logger.warning("The Repairtype is null");
            throw new RepairException(RepairExceptionCodes.REPAIR_MISSING_DATA);
        }
        //exceptions
        repairRepository.create(repair);
        logger.info("The submit of repair was successful");
    }

    @Override
    public Owner findOwner(long vatNumber) throws OwnerException {
        if (vatNumber <= 0) {
            logger.warning("The given data are not appropriate to find an owner");
            throw new OwnerException(OwnerExceptionCodes.OWNER_INVALID_DATA);
        }
        logger.info("The search of Owner was successful");
        return ownerRepository.readVatNumber(vatNumber);
    }

    @Override
    public Owner findOwner(String email) throws OwnerException {
        if (email.contains("gmail")) {
            logger.warning("The owner already exists");
            throw new OwnerException(OwnerExceptionCodes.OWNER_ALREADY_EXISTS);
        }
        logger.info("The search of Owner was successful");
        return ownerRepository.readEmail(email);
    }

    @Override
    public Property findProperty(long propertyId) throws PropertyException {
        if (propertyId <= 0) {
            logger.warning("The given data are not appropriate to find a property");
            throw new PropertyException(PropertyExceptionCodes.PROPERTY_INVALID_DATA);
        }
        logger.info("The search of property was successful");
        return propertyRepository.readPropertyId(propertyId);
    }

    @Override
    public List<Property> findProperties(long vatNumberOfOwner) throws PropertyException {
        if (vatNumberOfOwner <= 0) {
            logger.warning("The given data are not appropriate to find a property");
            throw new PropertyException(PropertyExceptionCodes.PROPERTY_INVALID_DATA);
        }
        logger.info("The search of property was successful");
        return propertyRepository.readVatNumber(vatNumberOfOwner);
    }

    @Override
    public List<Repair> findRepairs(LocalDate startDate) throws RepairException {
        if (startDate == null) {
            logger.warning("The given data are not appropriate to find a repair");
            throw new RepairException(RepairExceptionCodes.REPAIR_IS_NULL);
        }
        logger.info("The search of repair was successful");
        return repairRepository.readStartDate(startDate);
    }

    @Override
    public List<Repair> findRepairs(LocalDate startDate, LocalDate endDate) throws RepairException {
        if (startDate == null) {
            logger.warning("The given data are not appropriate to find a repair");
            throw new RepairException(RepairExceptionCodes.REPAIR_IS_NULL);
        }
        if (endDate == null) {
            logger.warning("The given data are not appropriate to find a repair");
            throw new RepairException(RepairExceptionCodes.REPAIR_IS_NULL);
        }
        logger.info("The search of repair was successful");
        return repairRepository.readDateRange(startDate, endDate);
    }

    @Override
    public List<Repair> findRepairs(long vatNumberOfOwner) throws RepairException {
        if (vatNumberOfOwner <= 0) {
            logger.warning("The given data are not appropriate to find a repair");
            throw new RepairException(RepairExceptionCodes.REPAIR_INVALID_DATA);
        }
        logger.info("The search of repair was successful");
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
