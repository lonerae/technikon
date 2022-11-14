package com.technikon.eagency.repository.impl;

import com.technikon.eagency.enums.RepairType;
import com.technikon.eagency.enums.StatusType;
import com.technikon.eagency.model.Repair;
import com.technikon.eagency.repository.RepairRepository;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

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
        Repair repair = read(repairId);
        if (repair != null) {
            repair.setVatNumberOfOwner(vatNumberOfOwner);
        }
    }

    @Override
    public void updatePropertyId(int repairId, long propertyId) {
        Repair repair = read(repairId);
        if (repair != null) {
            repair.setPropertyId(propertyId);
        }
    }

    @Override
    public void updateRepairType(int repairId, RepairType type) {
        Repair repair = read(repairId);
        if (repair != null) {
            repair.setRepairtype(type);
        }
    }

    @Override
    public void updateShortDescription(int repairId, String description) {
        Repair repair = read(repairId);
        if (repair != null) {
            repair.setShortDescription(description);
        }
    }

    @Override
    public void updateSubmissionDate(int repairId, LocalDateTime date) {
        Repair repair = read(repairId);
        if (repair != null) {
            repair.setSubmissionDate(date);
        }
    }

    @Override
    public void updateDescription(int repairId, String description) {
        Repair repair = read(repairId);
        if (repair != null) {
            repair.setDescription(description);
        }
    }

    @Override
    public void updateProposedStartDate(int repairId, LocalDateTime date) {
        Repair repair = read(repairId);
        if (repair != null) {
            repair.setProposedStartDate(date);
        }
    }

    @Override
    public void updateProposedEndDate(int repairId, LocalDateTime date) {
        Repair repair = read(repairId);
        if (repair != null) {
            repair.setProposedEndDate(date);
        }
    }

    @Override
    public void updateProposedCost(int repairId, BigDecimal cost) {
        Repair repair = read(repairId);
        if (repair != null) {
            repair.setProposedCost(cost);
        }
    }

    @Override
    public void updateAcceptance(int repairId, boolean startRepair) {
        Repair repair = read(repairId);
        if (repair != null) {
            repair.setAcceptance(startRepair);
        }
    }

    @Override
    public void updateStatus(int repairId, StatusType status) {
        Repair repair = read(repairId);
        if (repair != null) {
            repair.setStatusType(status);
        }
    }

    @Override
    public void updateActualStartDate(int repairId, LocalDateTime date) {
        Repair repair = read(repairId);
        if (repair != null) {
            repair.setActualStartDate(date);
        }
    }

    @Override
    public void updateActualEndDate(int repairId, LocalDateTime date) {
        Repair repair = read(repairId);
        if (repair != null) {
            repair.setActualEndDate(date);
        }
    }

}
