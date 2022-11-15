package com.technikon.eagency.repository.impl;

import com.technikon.eagency.enums.RepairType;
import com.technikon.eagency.enums.StatusType;
import com.technikon.eagency.model.Repair;
import com.technikon.eagency.repository.RepairRepository;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

/**
 *
 * @author panos
 */
public class RepairRepositoryImpl extends RepositoryImpl<Repair> implements RepairRepository {

    @Override
    public List<Repair> readStartDate(LocalDateTime date) {
        List<Repair> repairs = read().stream()
                .filter(r -> r.getActualStartDate().isEqual(date))
                .toList();
        return repairs;
    }

    @Override
    public List<Repair> readDateRange(LocalDateTime startDate, LocalDateTime endDate) {
        List<Repair> repairs = read().stream()
                .filter(r -> r.getActualStartDate().isEqual(startDate))
                .filter(r -> r.getActualEndDate().isEqual(endDate))
                .toList();
        return repairs;
    }

    @Override
    public List<Repair> readOwner(long vatNumber) {
        List<Repair> repairs = read().stream()
                .filter(r -> r.getVatNumberOfOwner() == vatNumber)
                .toList();
        return repairs;
    }

    @Override
    public void updateVatNumberOfOwner(int repairId, long vatNumberOfOwner) {
        Optional<Repair> repair = read(repairId);
        if (repair.isPresent()) {
            repair.get().setVatNumberOfOwner(vatNumberOfOwner);
        }
    }

    @Override
    public void updatePropertyId(int repairId, long propertyId) {
        Optional<Repair> repair = read(repairId);
        if (repair.isPresent()) {
            repair.get().setPropertyId(propertyId);
        }
    }

    @Override
    public void updateRepairType(int repairId, RepairType type) {
        Optional<Repair> repair = read(repairId);
        if (repair.isPresent()) {
            repair.get().setRepairtype(type);
        }
    }

    @Override
    public void updateShortDescription(int repairId, String description) {
        Optional<Repair> repair = read(repairId);
        if (repair.isPresent()) {
            repair.get().setShortDescription(description);
        }
    }

    @Override
    public void updateSubmissionDate(int repairId, LocalDateTime date) {
        Optional<Repair> repair = read(repairId);
        if (repair.isPresent()) {
            repair.get().setSubmissionDate(date);
        }
    }

    @Override
    public void updateDescription(int repairId, String description) {
        Optional<Repair> repair = read(repairId);
        if (repair.isPresent()) {
            repair.get().setDescription(description);
        }
    }

    @Override
    public void updateProposedStartDate(int repairId, LocalDateTime date) {
        Optional<Repair> repair = read(repairId);
        if (repair.isPresent()) {
            repair.get().setProposedStartDate(date);
        }
    }

    @Override
    public void updateProposedEndDate(int repairId, LocalDateTime date) {
        Optional<Repair> repair = read(repairId);
        if (repair.isPresent()) {
            repair.get().setProposedEndDate(date);
        }
    }

    @Override
    public void updateProposedCost(int repairId, BigDecimal cost) {
        Optional<Repair> repair = read(repairId);
        if (repair.isPresent()) {
            repair.get().setProposedCost(cost);
        }
    }

    @Override
    public void updateAcceptance(int repairId, boolean startRepair) {
        Optional<Repair> repair = read(repairId);
        if (repair.isPresent()) {
            repair.get().setAcceptance(startRepair);
        }
    }

    @Override
    public void updateStatus(int repairId, StatusType status) {
        Optional<Repair> repair = read(repairId);
        if (repair.isPresent()) {
            repair.get().setStatusType(status);
        }
    }

    @Override
    public void updateActualStartDate(int repairId, LocalDateTime date) {
        Optional<Repair> repair = read(repairId);
        if (repair.isPresent()) {
            repair.get().setActualStartDate(date);
        }
    }

    @Override
    public void updateActualEndDate(int repairId, LocalDateTime date) {
        Optional<Repair> repair = read(repairId);
        if (repair.isPresent()) {
            repair.get().setActualEndDate(date);
        }
    }

}
