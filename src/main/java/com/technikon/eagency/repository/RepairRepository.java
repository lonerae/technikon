package com.technikon.eagency.repository;

import com.technikon.eagency.enums.PropertyType;
import com.technikon.eagency.enums.StatusType;
import com.technikon.eagency.model.Repair;
import java.math.BigDecimal;
import java.time.LocalDateTime;

public interface RepairRepository extends Repository<Repair> {

    void updateOwner(int repairId, int ownerId);

    void updateProperty(int repairId, int propertyId);

    void updateType(int repairId, PropertyType type);

    void updateShortDescription(int repairId, String description);

    void updateSubmissionDate(int repairId, LocalDateTime date);

    void updateDescription(int repairId, String description);

    void updateProposedStartDate(int repairId, LocalDateTime date);

    void updateProposedEndDate(int repairId, LocalDateTime date);

    void updateCost(int repairId, BigDecimal cost);

    void updateAcceptance(int repairId, boolean startRepair);

    void updateStatus(int repairId, StatusType status);

    void updateActualStartDate(int repairId, LocalDateTime date);

    void updateActualEndDate(int repairId, LocalDateTime date);

}
