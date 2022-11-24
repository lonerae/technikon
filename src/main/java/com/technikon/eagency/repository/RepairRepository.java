package com.technikon.eagency.repository;

import com.technikon.eagency.model.Repair;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public interface RepairRepository extends Repository<Repair> {

  List<Repair> readStartDate(LocalDate date);
    
  List<Repair> readDateRange(LocalDate startDate, LocalDate endDate);
    
  List<Repair> readOwner(long vatNumber);
    
  boolean updateProposedStartDate(int repairId, LocalDate date);

  boolean updateProposedEndDate(int repairId, LocalDate date);

  boolean updateProposedCost(int repairId, BigDecimal cost);
    
  boolean update(Repair repair);

}

