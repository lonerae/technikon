package com.technikon.eagency.services;

import com.technikon.eagency.exceptions.RepairException;
import com.technikon.eagency.model.Repair;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;


public interface AdministratorService {

    void proposeCost(int repairId, BigDecimal proposedCost) throws RepairException;

    void proposeStartDate(int repairId, LocalDate proposedStartDate) throws RepairException;

    void proposeEndDate(int repairId, LocalDate proposedEndDate) throws RepairException;

    LocalDate checkStartDate(int repairId) throws RepairException;

    LocalDate checkEndDate(int repairId) throws RepairException;

    List<Repair> findAllPendingRepairs();

    List<Repair> findAllRepairs();

}
