package com.technikon.eagency.services.impl;

import com.technikon.eagency.enums.StatusType;
import com.technikon.eagency.exceptions.RepairException;
import com.technikon.eagency.exceptions.RepairExceptionCodes;
import com.technikon.eagency.model.Repair;
import com.technikon.eagency.repository.PropertyRepository;
import com.technikon.eagency.repository.RepairRepository;
import com.technikon.eagency.services.AdministratorService;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AdministratorServiceImpl implements AdministratorService {

    private final RepairRepository repairRepository;
    private final PropertyRepository propertyRepository;
    private static Logger logger = LoggerFactory.getLogger(AdministratorServiceImpl.class);

    public AdministratorServiceImpl(RepairRepository repairRepository, PropertyRepository propertyRepository) {
        this.repairRepository = repairRepository;
        this.propertyRepository = propertyRepository;
    }

    @Override
    public void proposeCost(int repairId, BigDecimal proposedCost) throws RepairException {
        if (propertyRepository.readPropertyId(repairId) == null) {
            logger.warn("The repair is null");
            throw new RepairException(RepairExceptionCodes.REPAIR_IS_NULL);
        }
        if (proposedCost == null) {
            logger.warn("The proposedCost of repair is null");
            throw new RepairException(RepairExceptionCodes.REPAIR_IS_NULL);
        }
        // exception?
        repairRepository.updateProposedCost(repairId, proposedCost);
        logger.info("The repair was successful");
    }

    @Override
    public void proposeStartDate(int repairId, LocalDate proposedStartDate) throws RepairException {
        if (propertyRepository.readPropertyId(repairId) == null) {
            logger.warn("The repair is null");
            throw new RepairException(RepairExceptionCodes.REPAIR_IS_NULL);
        }
        if (repairRepository.readStartDate(proposedStartDate) == null) {
            logger.warn("The proposedStartDate is null");
            throw new RepairException(RepairExceptionCodes.REPAIR_IS_NULL);
        }
        // exception?
        repairRepository.updateProposedStartDate(repairId, proposedStartDate);
        logger.info("The repair was successful");
    }

    @Override
    public void proposeEndDate(int repairId, LocalDate proposedEndDate) throws RepairException {
        if (propertyRepository.readPropertyId(repairId) == null) {
            logger.warn("The repair is null");
            throw new RepairException(RepairExceptionCodes.REPAIR_IS_NULL);
        }
        if (repairRepository.readStartDate(proposedEndDate) == null) {
            logger.warn("The proposedEndDate is null");
            throw new RepairException(RepairExceptionCodes.REPAIR_IS_NULL);
        }
        // exception?
        repairRepository.updateProposedEndDate(repairId, proposedEndDate);
        logger.info("The repair was successful");
    }

    @Override
    public LocalDate checkStartDate(int repairId) throws RepairException {
        if (propertyRepository.readPropertyId(repairId) == null) {
            logger.warn("The repair is null");
            throw new RepairException(RepairExceptionCodes.REPAIR_IS_NULL);
        }
        // exception?
        logger.info("The check of StartDate was successful");
        return repair.getDateOfStart();
    }

    @Override
    public LocalDate checkEndDate(int repairId) throws RepairException {
        if (propertyRepository.readPropertyId(repairId) == null) {
            logger.warn("The repair is null");
            throw new RepairException(RepairExceptionCodes.REPAIR_IS_NULL);
        }
        // exception?
        logger.info("The check of EndDate was successful");
        return repair.getDateOfEnd();
    }

    @Override
    public List<Repair> findAllPendingRepairs() {
        return repairRepository.read()
                .stream()
                .filter(r -> StatusType.PENDING == r.getStatustype())
                .collect(Collectors.toList());

    }

    @Override
    public List<Repair> findAllRepairs() {
        return repairRepository.read();
    }

}
