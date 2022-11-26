package com.technikon.eagency.repository.impl;

import com.technikon.eagency.model.Repair;
import com.technikon.eagency.repository.RepairRepository;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public class RepairRepositoryImpl extends RepositoryImpl<Repair> implements RepairRepository {

    @Override
    public List<Repair> readStartDate(LocalDate date) {
        return readAll().stream()
                .filter(repair -> repair.getDateOfStart().equals(date))
                .toList();
    }

    @Override
    public List<Repair> readDateRange(LocalDate startDate, LocalDate endDate) {
        return readAll().stream()
                .filter(repair -> repair.getDateOfStart().equals(startDate)
                && repair.getDateOfEnd().equals(endDate))
                .toList();
    }

    @Override
    public List<Repair> readOwner(long vatNumber) {
        return readAll().stream()
                .filter(repair -> repair.getOwner().getVatNumber() == vatNumber)
                .toList();
    }

    @Override
    public boolean updateProposedStartDate(int repairId, LocalDate date) {
        Repair repair = readById(repairId);
        if (repair != null) {
            repair.setDateOfStart(date);
            return true;
        }
        return false;
    }

    @Override
    public boolean updateProposedEndDate(int repairId, LocalDate date) {
        Repair repair = readById(repairId);
        if (repair != null) {
            repair.setDateOfEnd(date);
            return true;
        }
        return false;
    }

    @Override
    public boolean updateProposedCost(int repairId, BigDecimal cost) {
        Repair repair = readById(repairId);
        if (repair != null) {
            repair.setProposedCost(cost);
            return true;
        }
        return false;

    }

    @Override
    public boolean update(Repair newRepair) {
        Repair oldRepair = readById(newRepair.getId());
        if (oldRepair != null) {
            oldRepair.setAcceptance(newRepair.isAcceptance());
            oldRepair.setDateOfEnd(newRepair.getDateOfEnd());
            oldRepair.setDateOfStart(newRepair.getDateOfStart());
            oldRepair.setDateOfSubmisssion(newRepair.getDateOfSubmisssion());
            oldRepair.setDescriptionOfWork(newRepair.getDescriptionOfWork());
            oldRepair.setOwner(newRepair.getOwner());
            oldRepair.setProperty(newRepair.getProperty());
            oldRepair.setProposedCost(newRepair.getProposedCost());
            oldRepair.setProposedDateOfEnd(newRepair.getProposedDateOfEnd());
            oldRepair.setProposedDateOfStart(newRepair.getProposedDateOfStart());
            oldRepair.setRepairtype(newRepair.getRepairtype());
            oldRepair.setShortDescription(newRepair.getShortDescription());
            oldRepair.setStatusType(newRepair.getStatusType());
            return true;
        }
        return false;
    }
}