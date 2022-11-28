package com.technikon.eagency.repository;

import com.technikon.eagency.model.Repair;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public interface RepairRepository extends Repository<Repair> {

    List<Repair> readSubmissionDate(LocalDate date);

    List<Repair> readDateRange(LocalDate startDate, LocalDate endDate);

    List<Repair> readOwner(long vatNumber);

    void updateProposedStartDate(int repairId, LocalDate date);

    void updateProposedEndDate(int repairId, LocalDate date);

    void updateProposedCost(int repairId, BigDecimal cost);

    void update(Repair repair);

}
