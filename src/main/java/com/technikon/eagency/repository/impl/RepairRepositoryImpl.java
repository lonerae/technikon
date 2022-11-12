package com.technikon.eagency.repository.impl;

import com.technikon.eagency.enums.PropertyType;
import com.technikon.eagency.enums.StatusType;
import com.technikon.eagency.model.Repair;
import com.technikon.eagency.repository.RepairRepository;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 *
 * @author panos
 */
public class RepairRepositoryImpl extends RepositoryImpl<Repair> implements RepairRepository {

    //Error: missin fields and getters/setters 
    @Override
    public void updateOwner(int repairId, int ownerId) {
        Repair repair = read(repairId);
        if (repair != null) {
            repair.setOwnerId(ownerId);
        }
    }

    @Override
    public void updateProperty(int repairId, int propertyId) {
        Repair repair = read(repairId);
        if (repair != null) {
            repair.setPropertyId(propertyId);
        }
    }

    @Override
    public void updateType(int repairId, PropertyType type) {
        Repair repair = read(repairId);
        if (repair != null) {
            repair.setPropertyType(type);
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
    public void updateCost(int repairId, BigDecimal cost) {
        Repair repair = read(repairId);
        if (repair != null) {
            repair.setCost(cost);
        }
    }

    @Override
    public void updateAcceptance(int repairId, boolean startRepair) {
        Repair repair = read(repairId);
        if (repair != null) {
            repair.setStartRepair(startRepair);
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
