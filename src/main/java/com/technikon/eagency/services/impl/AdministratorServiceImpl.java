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
    private static Logger logger;

    public AdministratorServiceImpl(RepairRepository repairRepository) {
        this.repairRepository = repairRepository;
        logger = LoggerFactory.getLogger(AdministratorServiceImpl.class);
    }

    @Override
    public void proposeCost(int repairId, BigDecimal proposedCost) throws RepairException {
        if (repairRepository.readById(repairId) == null) {
            logger.warn("The repair is null.");
            throw new RepairException(RepairExceptionCodes.REPAIR_IS_NULL);
        }
        if (proposedCost == null) {
            logger.warn("No appropriate cost was given.");
            throw new RepairException(RepairExceptionCodes.REPAIR_MISSING_DATA);
        }
        repairRepository.updateProposedCost(repairId, proposedCost);
        logger.info("Cost of Repair #{} was successfully updated to {}.", repairId, proposedCost.toString());
    }

    @Override
    public void proposeStartDate(int repairId, LocalDate proposedStartDate) throws RepairException {
        if (repairRepository.readById(repairId) == null) {
            logger.warn("The repair is null.");
            throw new RepairException(RepairExceptionCodes.REPAIR_IS_NULL);
        }
        if (proposedStartDate == null) {
            logger.warn("No appropriate date was given.");
            throw new RepairException(RepairExceptionCodes.REPAIR_MISSING_DATA);
        }
        repairRepository.updateProposedStartDate(repairId, proposedStartDate);
        logger.info("Proposed start date of Repair #{} was successfully updated to {}.", repairId, proposedStartDate.toString());
    }

    @Override
    public void proposeEndDate(int repairId, LocalDate proposedEndDate) throws RepairException {
        if (repairRepository.readById(repairId) == null) {
            logger.warn("No repair under id {}", repairId);
            throw new RepairException(RepairExceptionCodes.REPAIR_IS_NULL);
        }
        if (proposedEndDate == null) {
            logger.warn("No appropriate date was given.");
            throw new RepairException(RepairExceptionCodes.REPAIR_MISSING_DATA);
        }
        repairRepository.updateProposedEndDate(repairId, proposedEndDate);
        logger.info("Proposed end date of Repair #{} was successfully updated to {}.", repairId, proposedEndDate.toString());
    }

    @Override
    public LocalDate checkStartDate(int repairId, LocalDate dateOfStart) throws RepairException {
        Repair repair = repairRepository.readById(repairId);
        if (repair == null) {
            logger.warn("No repair under id {}", repairId);
            throw new RepairException(RepairExceptionCodes.REPAIR_IS_NULL);
        }
        repair.setDateOfStart(dateOfStart);
        repairRepository.update(repair);
        logger.info("Start date of Repair #{} was checked.", repairId);
        return dateOfStart;
    }

    @Override
    public LocalDate checkEndDate(int repairId, LocalDate dateOfEnd) throws RepairException {
        Repair repair = repairRepository.readById(repairId);
        if (repair == null) {
            logger.warn("No repair under id {}", repairId);
            throw new RepairException(RepairExceptionCodes.REPAIR_IS_NULL);
        }
        repair.setDateOfEnd(dateOfEnd);
        repairRepository.update(repair);
        logger.info("End date of Repair #{} was checked.", repairId);
        return dateOfEnd;
    }

    @Override
    public List<Repair> findAllPendingRepairs() {
        return repairRepository.readAll()
                .stream()
                .filter(r -> StatusType.PENDING.equals(r.getStatusType()))
                .collect(Collectors.toList());
    }

    @Override
    public List<Repair> findAllRepairs() {
        return repairRepository.readAll();
    }

}
