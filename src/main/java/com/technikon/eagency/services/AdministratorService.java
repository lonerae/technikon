package com.technikon.eagency.services;

import com.technikon.eagency.model.Repair;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public interface AdministratorService {

    void proposeCost(int repairId, BigDecimal proposedCost);

    void proposeStartDate(int repairId, LocalDateTime proposedStartDate);

    void proposeEndDate(int repairId, LocalDateTime proposedEndDate);

    LocalDateTime checkStartDate(int repairId);

    LocalDateTime checkEndDate(int repairId);

    List<Repair> findAllPendingRepairs();

    List<Repair> findAllRepairs();

}
