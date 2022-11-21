package com.technikon.eagency.repository.impl;


import com.technikon.eagency.enums.RepairType;
import com.technikon.eagency.enums.StatusType;
import com.technikon.eagency.model.Repair;
import com.technikon.eagency.repository.RepairRepository;
import com.technikon.eagency.util.JPAUtil;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 *
 * @author panos
 */
public class RepairRepositoryImpl extends RepositoryImpl<Repair> implements RepairRepository {

    private EntityManager entityManager = JPAUtil.getEntityManager();
    private List<Repair> listOfRepairs = new ArrayList<>();

    @Override
    public List<Repair> readStartDate(LocalDate date) {
        return entityManager.createQuery(listOfRepairs.getClass(), date).getResultList();
    }

    @Override
    public List<Repair> readDateRange(LocalDate startDate, LocalDate endDate) {
        return entityManager.createQuery(listOfRepairs.getClass(), startDate, endDate).getResultList();
    }

    @Override
    public List<Repair> readOwner(long vatNumber) {
        return entityManager.createQuery(listOfRepairs.getClass(), vatNumber).getResultList();
    }

    @Override
    public void updateVatNumberOfOwner(int repairId, long vatNumberOfOwner) {
        Optional<Repair> repair = read(repairId);
        if (repair.isPresent()) {
            repair.get().getOwner().setVatNumber(vatNumberOfOwner);
        }
    }

    @Override
    public void updatePropertyId(int repairId, long propertyId) {
        Optional<Repair> repair = read(repairId);
        if (repair.isPresent()) {
            repair.get().getProperty().setPropertyId(propertyId);
        }
    }

    @Override
    public void updateRepairType(int repairId, RepairType type) {
        Optional<Repair> repair = read(repairId);
        if (repair.isPresent()) {
            repair.get().setRepairtype(type);
        }
    }

    @Override
    public void updateShortDescription(int repairId, String description) {
        Optional<Repair> repair = read(repairId);
        if (repair.isPresent()) {
            repair.get().setShortDescription(description);
        }
    }

    @Override
    public void updateSubmissionDate(int repairId, LocalDate date) {
        Optional<Repair> repair = read(repairId);
        if (repair.isPresent()) {
            repair.get().setDateOfSubmisssion(date);
        }
    }

    @Override
    public void updateDescription(int repairId, String description) {
        Optional<Repair> repair = read(repairId);
        if (repair.isPresent()) {
            repair.get().setDescriptionOfWork(description);
        }
    }

    @Override
    public void updateProposedStartDate(int repairId, LocalDate date) {
        Optional<Repair> repair = read(repairId);
        if (repair.isPresent()) {
            repair.get().setProposedDateOfStart(date);
        }
    }

    @Override
    public void updateProposedEndDate(int repairId, LocalDate date) {
        Optional<Repair> repair = read(repairId);
        if (repair.isPresent()) {
            repair.get().setProposedDateOfEnd(date);
        }
    }

    @Override
    public void updateProposedCost(int repairId, BigDecimal cost) {
        Optional<Repair> repair = read(repairId);
        if (repair.isPresent()) {
            repair.get().setProposedCost(cost);
        }
    }

    @Override
    public void updateAcceptance(int repairId, boolean startRepair) {
        Optional<Repair> repair = read(repairId);
        if (repair.isPresent()) {
            repair.get().setAcceptance(startRepair);
        }
    }

    @Override
    public void updateStatus(int repairId, StatusType status) {
        Optional<Repair> repair = read(repairId);
        if (repair.isPresent()) {
            repair.get().setStatustype(status);
        }
    }

    @Override
    public void updateActualStartDate(int repairId, LocalDate date) {
        Optional<Repair> repair = read(repairId);
        if (repair.isPresent()) {
            repair.get().setDateOfStart(date);
        }
    }

    @Override
    public void updateActualEndDate(int repairId, LocalDate date) {
        Optional<Repair> repair = read(repairId);
        if (repair.isPresent()) {
            repair.get().setDateOfEnd(date);
        }
    }

}
