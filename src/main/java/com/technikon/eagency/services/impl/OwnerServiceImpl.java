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
    private static Logger logger = LoggerFactory.getLogger(OwnerServiceImpl.class);

    public OwnerServiceImpl(OwnerRepository ownerRepository, PropertyRepository propertyRepository, RepairRepository repairRepository) {
        this.ownerRepository = ownerRepository;
        this.propertyRepository = propertyRepository;
        this.repairRepository = repairRepository;
    }

    @Override
    public void registerOwner(Owner owner) throws OwnerException {
        if (owner == null) {
            logger.warn("The owner is null");
            throw new OwnerException(OwnerExceptionCodes.OWNER_IS_NULL);
        }
        //exceptions
        ownerRepository.create(owner);
        logger.info("The register was successful");
    }

    @Override
    public void registerProperty(Property property) throws PropertyException {
        if (property == null) {
            logger.warn("The property is null");
            throw new PropertyException(PropertyExceptionCodes.PROPERTY_IS_NULL);
        }
        if (property.getAddress() == null) {
            logger.warn("Not all data are given to create a property");
            throw new PropertyException(PropertyExceptionCodes.PROPERTY_MISSING_DATA);
        }
        //exceptions
        propertyRepository.create(property);
        logger.info("The register of Property was successful");
    }

    @Override
    public void submitRepair(Repair repair) throws RepairException {
        if (repair == null) {
            logger.warn("The repair is null");
            throw new RepairException(RepairExceptionCodes.REPAIR_IS_NULL);
        }
        if (repair.getRepairtype() == null) {
            logger.warn("The Repairtype is null");
            throw new RepairException(RepairExceptionCodes.REPAIR_MISSING_DATA);
        }
        //exceptions
        repairRepository.create(repair);
        logger.info("The submit of repair was successful");
    }

    @Override
    public Owner findOwner(long vatNumber) throws OwnerException {
        if (ownerRepository.readVatNumber(vatNumber) == null) {
            logger.warn("The owner is null");
            throw new OwnerException(OwnerExceptionCodes.OWNER_IS_NULL);
        }
        logger.info("The search of owner was successful");
        return ownerRepository.readVatNumber(vatNumber);
    }

    @Override
    public Owner findOwner(String email) throws OwnerException {
        if (ownerRepository.readEmail(email) == null) {
            logger.warn("The owner is null");
            throw new OwnerException(OwnerExceptionCodes.OWNER_IS_NULL);
        }
        logger.info("The search of owner was successful");
        return ownerRepository.readEmail(email);
    }

    @Override
    public Property findProperty(long propertyId) throws PropertyException {
        if (propertyRepository.readPropertyId(propertyId) == null) {
            logger.warn("The property is null");
            throw new PropertyException(PropertyExceptionCodes.PROPERTY_IS_NULL);
        }
        logger.info("The search of property was successful");
        return propertyRepository.readPropertyId(propertyId);
    }

    @Override
    public List<Property> findProperties(long vatNumberOfOwner) throws PropertyException {
        if (propertyRepository.readVatNumber(vatNumberOfOwner) == null) {
            logger.warn("The property is null");
            throw new PropertyException(PropertyExceptionCodes.PROPERTY_IS_NULL);
        }
        logger.info("The search of property was successful");
        return propertyRepository.readVatNumber(vatNumberOfOwner);
    }

    @Override
    public List<Repair> findRepairs(LocalDate startDate) throws RepairException {
        if (repairRepository.readStartDate(startDate) == null) {
            logger.warn("The repair is null");
            throw new RepairException(RepairExceptionCodes.REPAIR_IS_NULL);
        }
        logger.info("The search of repair was successful");
        return repairRepository.readStartDate(startDate);
    }

    @Override
    public List<Repair> findRepairs(LocalDate startDate, LocalDate endDate) throws RepairException {
        if (repairRepository.readDateRange(startDate, endDate) == null) {
            logger.warn("The repair is null");
            throw new RepairException(RepairExceptionCodes.REPAIR_IS_NULL);
        }
        logger.info("The search of repair was successful");
        return repairRepository.readDateRange(startDate, endDate);
    }

    @Override
    public List<Repair> findRepairs(long vatNumberOfOwner) throws RepairException {
        if (ownerRepository.readVatNumber(vatNumberOfOwner) == null) {
            logger.warn("The repair is null");
            throw new RepairException(RepairExceptionCodes.REPAIR_IS_NULL);
        }
        logger.info("The search of repair was successful");
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
