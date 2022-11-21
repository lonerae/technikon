package com.technikon.eagency.services.impl;

import com.technikon.eagency.enums.StatusType;
import com.technikon.eagency.exceptions.RepairException;
import com.technikon.eagency.exceptions.RepairExceptionCodes;
import com.technikon.eagency.model.Repair;
import com.technikon.eagency.repository.RepairRepository;
import com.technikon.eagency.services.AdministratorService;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

public class AdministratorServiceImpl implements AdministratorService {

    private final RepairRepository repairRepository;

    public AdministratorServiceImpl(RepairRepository repairRepository) {
        this.repairRepository = repairRepository;
    }

    @Override
    public void proposeCost(int repairId, BigDecimal proposedCost) throws RepairException {
        if (repairId < 1 && repairId > 20) {
            throw new RepairException(RepairExceptionCodes.REPAIR_INVALID_DATA);
        }
        if (proposedCost == null) {
            throw new RepairException(RepairExceptionCodes.REPAIR_IS_NULL);
        }
        Logger.getLogger(AdministratorServiceImpl.class.getName()).log(Level.SEVERE, null, repairId);
        Logger.getLogger(AdministratorServiceImpl.class.getName()).log(Level.SEVERE, null, proposedCost);
        // exception?
        repairRepository.updateProposedCost(repairId, proposedCost);
    }

    @Override
    public void proposeStartDate(int repairId, LocalDate proposedStartDate) throws RepairException {
        if (repairId < 1 && repairId > 20) {
            throw new RepairException(RepairExceptionCodes.REPAIR_INVALID_DATA);
        }
        if (proposedStartDate == null) {
            throw new RepairException(RepairExceptionCodes.REPAIR_IS_NULL);
        }
        Logger.getLogger(AdministratorServiceImpl.class.getName()).log(Level.SEVERE, null, repairId);
        Logger.getLogger(AdministratorServiceImpl.class.getName()).log(Level.SEVERE, null, proposedStartDate);
        // exception?
        repairRepository.updateProposedStartDate(repairId, proposedStartDate);
    }

    @Override
    public void proposeEndDate(int repairId, LocalDate proposedEndDate) throws RepairException {
        if (repairId < 1 && repairId > 20) {
            throw new RepairException(RepairExceptionCodes.REPAIR_INVALID_DATA);
        }
        if (proposedEndDate == null) {
            throw new RepairException(RepairExceptionCodes.REPAIR_IS_NULL);
        }
        Logger.getLogger(AdministratorServiceImpl.class.getName()).log(Level.SEVERE, null, repairId);
        Logger.getLogger(AdministratorServiceImpl.class.getName()).log(Level.SEVERE, null, proposedEndDate);
        // exception?
        repairRepository.updateProposedEndDate(repairId, proposedEndDate);
    }

    @Override
    public LocalDate checkStartDate(int repairId) throws RepairException {
        if (repairId < 1 && repairId > 20) {
            throw new RepairException(RepairExceptionCodes.REPAIR_INVALID_DATA);
        }
        // exception?
        Optional<Repair> repair = repairRepository.read(repairId);
        return repair.get().getDateOfStart();
    }

    @Override
    public LocalDate checkEndDate(int repairId) throws RepairException {
        if (repairId < 1 && repairId > 20) {
            throw new RepairException(RepairExceptionCodes.REPAIR_INVALID_DATA);
        }
        // exception?
        Optional<Repair> repair = repairRepository.read(repairId);
        return repair.get().getDateOfEnd();
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
