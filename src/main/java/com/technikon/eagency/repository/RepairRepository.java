package com.technikon.eagency.repository;

import com.technikon.eagency.enums.RepairType;
import com.technikon.eagency.enums.StatusType;
import com.technikon.eagency.model.Repair;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public interface RepairRepository extends Repository<Repair> {

    List<Repair> readStartDate(LocalDateTime date);
    
    List<Repair> readDateRange(LocalDateTime startDate, LocalDateTime endDate);
    
    List<Repair> readOwner(long vatNumber);
    
    void updateVatNumberOfOwner(int repairId, long vatNumberOfOwner);

    void updatePropertyId(int repairId, long propertyId);

    void updateRepairType(int repairId, RepairType type);

    void updateShortDescription(int repairId, String description);

    void updateSubmissionDate(int repairId, LocalDateTime date);

    void updateDescription(int repairId, String description);

    void updateProposedStartDate(int repairId, LocalDateTime date);

    void updateProposedEndDate(int repairId, LocalDateTime date);

    void updateProposedCost(int repairId, BigDecimal cost);

    void updateAcceptance(int repairId, boolean startRepair);

    void updateStatus(int repairId, StatusType status);

    void updateActualStartDate(int repairId, LocalDateTime date);

    void updateActualEndDate(int repairId, LocalDateTime date);

}
