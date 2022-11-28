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
        return entityManager.createQuery("FROM Repair r WHERE r.dateOfSubmission = :date ", Repair.class)
                .setParameter("date", date)
                .getResultList();
    }

    @Override
    public List<Repair> readDateRange(LocalDate startDate, LocalDate endDate) {
        return entityManager.createQuery("FROM Repair r WHERE r.dateOfSubmission BETWEEN :start AND : end ", Repair.class)
                .setParameter("start", startDate)
                .setParameter("end", endDate)
                .getResultList();
    }

    @Override
    public List<Repair> readOwner(long vatNumberOfOwner) {
        return entityManager.createQuery("FROM Repair r WHERE r.owner.vatNumber = :vatNumber ", Repair.class)
                .setParameter("vatNumber", vatNumberOfOwner)
                .getResultList();
    }

    @Override
    public void updateProposedStartDate(int repairId, LocalDate date) {
        Repair repair = readById(repairId);
        repair.setProposedDateOfStart(date);
        entityManager.getTransaction().begin();
        entityManager.merge(repair);
        entityManager.getTransaction().commit();
    }

    @Override
    public void updateProposedEndDate(int repairId, LocalDate date) {
        Repair repair = readById(repairId);
        repair.setProposedDateOfEnd(date);
        entityManager.getTransaction().begin();
        entityManager.merge(repair);
        entityManager.getTransaction().commit();
    }

    @Override
    public void updateProposedCost(int repairId, BigDecimal cost) {
        Repair repair = readById(repairId);
        repair.setProposedCost(cost);
        entityManager.getTransaction().begin();
        entityManager.merge(repair);
        entityManager.getTransaction().commit();
    }

    @Override
    public void update(Repair repair) {
        entityManager.getTransaction().begin();
        entityManager.merge(repair);
        entityManager.getTransaction().commit();
    }

}
