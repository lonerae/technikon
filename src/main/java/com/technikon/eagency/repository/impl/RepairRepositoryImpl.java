package com.technikon.eagency.repository.impl;

import com.technikon.eagency.model.Repair;
import com.technikon.eagency.repository.RepairRepository;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public class RepairRepositoryImpl extends RepositoryImpl<Repair> implements RepairRepository {

    @Override
    public List<Repair> readSubmissionDate(LocalDate date) {
        return readAll().stream()
                .filter(repair -> repair.getDateOfSubmission().equals(date))
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
    public void updateProposedStartDate(int repairId, LocalDate date) {
        Repair repair = readById(repairId);
        repair.setDateOfStart(date);
    }

    @Override
    public void updateProposedEndDate(int repairId, LocalDate date) {
        Repair repair = readById(repairId);
        repair.setDateOfEnd(date);
    }

    @Override
    public void updateProposedCost(int repairId, BigDecimal cost) {
        Repair repair = readById(repairId);
        repair.setProposedCost(cost);
    }

    @Override
    public void update(Repair newRepair) {
        Repair oldRepair = readById(newRepair.getId());
        oldRepair.setAcceptance(newRepair.isAcceptance());
        oldRepair.setDateOfEnd(newRepair.getDateOfEnd());
        oldRepair.setDateOfStart(newRepair.getDateOfStart());
        oldRepair.setDateOfSubmission(newRepair.getDateOfSubmission());
        oldRepair.setDescriptionOfWork(newRepair.getDescriptionOfWork());
        oldRepair.setOwner(newRepair.getOwner());
        oldRepair.setProperty(newRepair.getProperty());
        oldRepair.setProposedCost(newRepair.getProposedCost());
        oldRepair.setProposedDateOfEnd(newRepair.getProposedDateOfEnd());
        oldRepair.setProposedDateOfStart(newRepair.getProposedDateOfStart());
        oldRepair.setRepairtype(newRepair.getRepairtype());
        oldRepair.setShortDescription(newRepair.getShortDescription());
        oldRepair.setStatusType(newRepair.getStatusType());
    }
}
