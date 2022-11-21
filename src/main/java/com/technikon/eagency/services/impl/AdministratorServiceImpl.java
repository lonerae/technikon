package com.technikon.eagency.services.impl;

import com.technikon.eagency.enums.StatusType;
import com.technikon.eagency.exceptions.RepairException;
import com.technikon.eagency.model.Repair;
import com.technikon.eagency.repository.RepairRepository;
import com.technikon.eagency.services.AdministratorService;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class AdministratorServiceImpl implements AdministratorService {

    private final RepairRepository repairRepository;

    public AdministratorServiceImpl(RepairRepository repairRepository) {
        this.repairRepository = repairRepository;
    }

    @Override
    public void proposeCost(int repairId, BigDecimal proposedCost) throws RepairException {
        if (repairId == null) {
            throw new RepairException(OwnerExceptionCodes.OWNER_IS_NULL);
        }
        // exception?
        repairRepository.updateProposedCost(repairId, proposedCost);
    }

    @Override
    public void proposeStartDate(int repairId, LocalDate proposedStartDate) {
        // exception?
        repairRepository.updateProposedStartDate(repairId, proposedStartDate);
    }

    @Override
    public void proposeEndDate(int repairId, LocalDate proposedEndDate) {
        // exception?
        repairRepository.updateProposedEndDate(repairId, proposedEndDate);
    }

    @Override
    public LocalDate checkStartDate(int repairId) {
        // exception?
        Optional<Repair> repair = repairRepository.read(repairId);
        return repair.get().getDateOfStart();
    }

    @Override
    public LocalDate checkEndDate(int repairId) {
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
