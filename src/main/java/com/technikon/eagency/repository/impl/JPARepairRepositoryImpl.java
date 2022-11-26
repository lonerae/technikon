package com.technikon.eagency.repository.impl;

import com.technikon.eagency.model.Repair;
import com.technikon.eagency.repository.RepairRepository;
import com.technikon.eagency.util.JPAUtil;
import jakarta.persistence.EntityManager;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public class JPARepairRepositoryImpl extends JPARepositoryImpl<Repair> implements RepairRepository {

    private final EntityManager entityManager;

    public JPARepairRepositoryImpl() {
        entityManager = JPAUtil.getEntityManager();
    }

    @Override
    public Class<Repair> getEntityClass() {
        return Repair.class;
    }

    @Override
    public List<Repair> readSubmissionDate(LocalDate date) {
        return entityManager.createQuery("from Repair r Where r.dateOfSubmission = :date ", Repair.class)
                .setParameter("date", date)
                .getResultList();
    }

    @Override
    public List<Repair> readDateRange(LocalDate startDate, LocalDate endDate) {
        return entityManager.createQuery("from Repair r Where r.dateOfSubmission BETWEEN :start AND : end ", Repair.class)
                .setParameter("start", startDate)
                .setParameter("end", endDate)
                .getResultList();
    }

    @Override
    public List<Repair> readOwner(long vatNumberOfOwner) {
        return entityManager.createQuery("from Repair r Where r.owner.vatNumber = :vatNumber ", Repair.class)
                .setParameter("vatNumber", vatNumberOfOwner)
                .getResultList();
    }

    @Override
    public boolean updateProposedStartDate(int repairId, LocalDate date) {
        Repair repair = readById(repairId);
        if (repair != null) {
            repair.setProposedDateOfStart(date);
            entityManager.getTransaction().begin();
            entityManager.merge(repair);
            entityManager.getTransaction().commit();
            return true;
        }
        return false;
    }

    @Override
    public boolean updateProposedEndDate(int repairId, LocalDate date) {
        Repair repair = readById(repairId);
        if (repair != null) {
            repair.setProposedDateOfEnd(date);
            entityManager.getTransaction().begin();
            entityManager.merge(repair);
            entityManager.getTransaction().commit();
            return true;
        }
        return false;
    }

    @Override
    public boolean updateProposedCost(int repairId, BigDecimal cost) {
        Repair repair = readById(repairId);
        if (repair != null) {
            repair.setProposedCost(cost);
            entityManager.getTransaction().begin();
            entityManager.merge(repair);
            entityManager.getTransaction().commit();
            return true;
        }
        return false;
    }

    @Override
    public boolean update(Repair repair) {
        // exception if it doesn't exist then return false (services?)        
        entityManager.getTransaction().begin();
        entityManager.merge(repair);
        entityManager.getTransaction().commit();
        return true;
    }

}
