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
        Repair repairRetrieved = repairRepository.readById(repairId);
        if (repairRetrieved == null || !repairRetrieved.isActive()) {
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
        Repair repairRetrieved = repairRepository.readById(repairId);

        if (repairRetrieved == null || !repairRetrieved.isActive()) {
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
        Repair repairRetrieved = repairRepository.readById(repairId);

        if (repairRetrieved == null || !repairRetrieved.isActive()) {
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
    public void updateStatusType(int repairId, StatusType status) throws RepairException {
        Repair repair = repairRepository.readById(repairId);
        if (repair == null || !repair.isActive()) {
            logger.warn("The repair is null.");
            throw new RepairException(RepairExceptionCodes.REPAIR_IS_NULL);
        }
        if (status == null) {
            logger.warn("No appropriate status was given.");
            throw new RepairException(RepairExceptionCodes.REPAIR_MISSING_DATA);
        }
        repair.setStatusType(status);
        repairRepository.update(repair);
        logger.info("Status of Repair #{} was successfully updated to {}.", repairId, status.toString());
    }

    @Override
    public LocalDate checkStartDate(int repairId, LocalDate dateOfStart) throws RepairException {
        Repair repair = repairRepository.readById(repairId);
        if (repair == null || !repair.isActive()) {
            logger.warn("No repair under id {}", repairId);
            throw new RepairException(RepairExceptionCodes.REPAIR_IS_NULL);
        }
        if (dateOfStart != null && dateOfStart.isBefore(repair.getDateOfSubmission())) {
            logger.warn("Start date is before the date of submission");
            throw new RepairException(RepairExceptionCodes.REPAIR_INVALID_DATE_OF_START);
        }
        repair.setDateOfStart(dateOfStart);
        repairRepository.update(repair);
        logger.info("Start date of Repair #{} was checked.", repairId);
        return dateOfStart;
    }

    @Override
    public LocalDate checkEndDate(int repairId, LocalDate dateOfEnd) throws RepairException {
        Repair repair = repairRepository.readById(repairId);
        if (repair == null || !repair.isActive()) {
            logger.warn("No repair under id {}", repairId);
            throw new RepairException(RepairExceptionCodes.REPAIR_IS_NULL);
        }
        if (dateOfEnd != null && dateOfEnd.isBefore(repair.getDateOfSubmission())) {
            logger.warn("End date is before the date of submission");
            throw new RepairException(RepairExceptionCodes.REPAIR_INVALID_DATE_OF_END);
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
                .filter(r -> StatusType.PENDING.equals(r.getStatusType()) && r.isActive())
                .collect(Collectors.toList());
    }

    @Override
    public List<Repair> findAllRepairs() {
        return repairRepository.readAll()
                .stream()
                .filter(r -> r.isActive())
                .collect(Collectors.toList());
    }

}
