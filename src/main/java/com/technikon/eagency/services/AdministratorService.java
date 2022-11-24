package com.technikon.eagency.services;

import com.technikon.eagency.model.Repair;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;


public interface AdministratorService {

    void proposeCost(int repairId, BigDecimal proposedCost);

    void proposeStartDate(int repairId, LocalDate proposedStartDate);

    void proposeEndDate(int repairId, LocalDate proposedEndDate);

    LocalDate checkStartDate(int repairId);

    LocalDate checkEndDate(int repairId);

    List<Repair> findAllPendingRepairs();

    List<Repair> findAllRepairs();

}
