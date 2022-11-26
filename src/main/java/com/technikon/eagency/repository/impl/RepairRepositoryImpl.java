package com.technikon.eagency.repository.impl;

import com.technikon.eagency.model.Repair;
import com.technikon.eagency.repository.RepairRepository;
import com.technikon.eagency.util.JPAUtil;
import jakarta.persistence.EntityManager;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public class RepairRepositoryImpl extends RepositoryImpl<Repair> implements RepairRepository {

    private final EntityManager entityManager;

    public RepairRepositoryImpl() {
        entityManager = JPAUtil.getEntityManager();
    }
  
    @Override
    public Class<Repair> getEntityClass() {
        return Repair.class;
    }

    @Override
    public List<Repair> readStartDate(LocalDate date) {
        return entityManager.createQuery("from Repair r Where r.dateOfStart = :date ", Repair.class)
                .setParameter("date", date)
                .getResultList();

//        STREAM IMPLEMENTATION
//        List<Repair> resultList = read();
//        return resultList.stream()
//                .filter(repair -> repair.getDateOfStart().isEqual(date))
//                .collect(Collectors.toList());
    }

    @Override
    public List<Repair> readDateRange(LocalDate startDate, LocalDate endDate) {
        return entityManager.createQuery("from Repair r Where r.dateOfStart BETWEEN :start AND : end ", Repair.class)
                .setParameter("start", startDate)
                .setParameter("end", endDate)
                .getResultList();

//        STREAM IMPLEMENTATION
//        List<Repair> resultList = read();
//        return resultList.stream()
//                .filter(repair -> repair.getDateOfStart().compareTo(startDate) >= 0 && repair.getDateOfEnd().compareTo(startDate) <= 0)
//                .collect(Collectors.toList());
    }

    @Override
    public List<Repair> readOwner(long vatNumber) {
        return entityManager.createQuery("from Repair r join fetch r.Owner o  Where o.vatNumber = :vatNumber ", Repair.class)
                .setParameter("vatNumber", vatNumber)
                .getResultList();

//        STREAM IMPLEMENTATION
//        List<Repair> resultList = read();
//        return resultList.stream()
//                .filter(repair -> repair.getOwner().getVatNumber() == vatNumber)
//                .collect(Collectors.toList());
    }

    @Override
    public boolean updateProposedStartDate(int repairId, LocalDate date) {
        Repair repair = read(repairId);
        if (repair != null) {
            repair.setProposedDateOfStart(date);
            entityManager.getTransaction().begin();
            entityManager.persist(repair);
            entityManager.getTransaction().commit();
            return true;
        }
        return false;
    }

    @Override
    public boolean updateProposedEndDate(int repairId, LocalDate date) {
        Repair repair = read(repairId);
        if (repair != null) {
            repair.setProposedDateOfEnd(date);
            entityManager.getTransaction().begin();
            entityManager.persist(repair);
            entityManager.getTransaction().commit();
            return true;
        }
        return false;
    }

    @Override
    public boolean updateProposedCost(int repairId, BigDecimal cost) {
        Repair repair = read(repairId);
        if (repair != null) {
            repair.setProposedCost(cost);
            entityManager.getTransaction().begin();
            entityManager.persist(repair);
            entityManager.getTransaction().commit();
            return true;
        }
        return false;
    }

    @Override
    public boolean update(Repair repair) {
        // exception if it doesn't exist then return false (services?)        
        entityManager.getTransaction().begin();
        entityManager.persist(repair);
        entityManager.getTransaction().commit();
        return true;
    }

 
}
