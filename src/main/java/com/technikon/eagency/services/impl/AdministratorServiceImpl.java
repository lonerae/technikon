package com.technikon.eagency.services.impl;

import com.technikon.eagency.enums.StatusType;
import com.technikon.eagency.exceptions.RepairException;
import com.technikon.eagency.exceptions.RepairExceptionCodes;
import com.technikon.eagency.model.Repair;
import com.technikon.eagency.repository.RepairRepository;
import com.technikon.eagency.services.AdministratorService;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

public class AdministratorServiceImpl implements AdministratorService {

    private final RepairRepository repairRepository;
    private final Logger logger;

    public AdministratorServiceImpl(RepairRepository repairRepository) {
        this.repairRepository = repairRepository;
        logger = Logger.getLogger("AdministratorServiceImpl.class");
    }

    @Override
    public void proposeCost(int repairId, BigDecimal proposedCost) throws RepairException {
        if (repairId <= 0) {
            logger.warning("The given data are not appropriate to create a repairId");
            throw new RepairException(RepairExceptionCodes.REPAIR_INVALID_DATA);
        }
        if (proposedCost == null) {
            logger.warning("The proposedCost is null");
            throw new RepairException(RepairExceptionCodes.REPAIR_IS_NULL);
        }
        Logger.getLogger(AdministratorServiceImpl.class.getName()).log(Level.SEVERE, null, repairId);
        Logger.getLogger(AdministratorServiceImpl.class.getName()).log(Level.SEVERE, null, proposedCost);
        // exception?
        repairRepository.updateProposedCost(repairId, proposedCost);
        logger.info("The register was successful");
    }

    @Override
    public void proposeStartDate(int repairId, LocalDate proposedStartDate) throws RepairException {
        if (repairId <= 0) {
            logger.warning("The given data are not appropriate to create a repairId");
            throw new RepairException(RepairExceptionCodes.REPAIR_INVALID_DATA);
        }
        if (proposedStartDate == null) {
            logger.warning("Not all data are given to create a proposedStartDate");
            throw new RepairException(RepairExceptionCodes.REPAIR_MISSING_DATA);
        }
        // exception?
        repairRepository.updateProposedStartDate(repairId, proposedStartDate);
        logger.info("The register was successful");
    }

    @Override
    public void proposeEndDate(int repairId, LocalDate proposedEndDate) throws RepairException {
        if (repairId <= 0) {
            logger.warning("The given data are not appropriate to create a repairId");
            throw new RepairException(RepairExceptionCodes.REPAIR_INVALID_DATA);
        }
        if (proposedEndDate == null) {
            logger.warning("The property is null");
            throw new RepairException(RepairExceptionCodes.REPAIR_MISSING_DATA);
        }
        // exception?
        repairRepository.updateProposedEndDate(repairId, proposedEndDate);
        logger.info("The register was successful");
    }

    @Override
    public LocalDate checkStartDate(int repairId) throws RepairException {
        if (repairId <= 0) {
            logger.warning("The given data are not appropriate to create a repairId");
            throw new RepairException(RepairExceptionCodes.REPAIR_INVALID_DATA);
        }
        // exception?
        Repair repair = repairRepository.read(repairId);
        return repair.getDateOfStart();
    }

    @Override
    public LocalDate checkEndDate(int repairId) throws RepairException {
        if (repairId <= 0) {
            logger.warning("The given data are not appropriate to create a repairId");
            throw new RepairException(RepairExceptionCodes.REPAIR_INVALID_DATA);
        }
        // exception?
        Repair repair = repairRepository.read(repairId);
        return repair.getDateOfEnd();
    }

    @Override
    public List<Repair> findAllPendingRepairs() {
        return repairRepository.read()
                .stream()
                .filter(r -> StatusType.PENDING == r.getStatusType())
                .collect(Collectors.toList());

    }

    @Override
    public List<Repair> findAllRepairs() {
        return repairRepository.read();
    }

}
