/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package com.technikon.eagency.services.impl;

import com.technikon.eagency.exceptions.OwnerException;
import com.technikon.eagency.exceptions.PropertyException;
import com.technikon.eagency.exceptions.RepairException;
import com.technikon.eagency.model.Owner;
import com.technikon.eagency.model.Property;
import com.technikon.eagency.model.Repair;
import com.technikon.eagency.repository.OwnerRepository;
import com.technikon.eagency.repository.PropertyRepository;
import com.technikon.eagency.repository.RepairRepository;
import com.technikon.eagency.repository.impl.JPAOwnerRepositoryImpl;
import com.technikon.eagency.repository.impl.JPAPropertyRepositoryImpl;
import com.technikon.eagency.repository.impl.JPARepairRepositoryImpl;
import com.technikon.eagency.repository.impl.OwnerRepositoryImpl;
import com.technikon.eagency.repository.impl.PropertyRepositoryImpl;
import com.technikon.eagency.repository.impl.RepairRepositoryImpl;
import com.technikon.eagency.services.OwnerService;
import jakarta.persistence.PersistenceException;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;

/**
 *
 * @author lonerae
 */
public class OwnerServiceImplTest {

    private final OwnerRepository ownerRepository = new JPAOwnerRepositoryImpl();
    private final PropertyRepository propertyRepository = new JPAPropertyRepositoryImpl();
    private final RepairRepository repairRepository = new JPARepairRepositoryImpl();
    private OwnerService service;

    @BeforeEach
    public void beforeEach() {
        service = new OwnerServiceImpl(ownerRepository, propertyRepository, repairRepository);
    }

    @Test
    public void addNullOwnerAndCheckIfOwnerIsNotAdded() throws OwnerException {
        Owner owner = null;
        assertThrows(OwnerException.class, () -> service.registerOwner(owner));
    }

    @Test
    public void addOwnerWithSameVatNumberAndCheckIfOwnerIsNotAdded() throws PersistenceException, OwnerException {
        Owner owner1 = new Owner();
        owner1.setEmail("p@mail.com");
        owner1.setName("panos");
        owner1.setPassword("1234567");
        owner1.setPhoneNumber("697424444");
        owner1.setSurname("vaz");
        owner1.setUsername("panv");
        owner1.setVatNumber(444444444);
        owner1.setAddress("p 4");
        service.registerOwner(owner1);

        Owner owner2 = new Owner();
        owner2.setEmail("j@mail.com");
        owner2.setName("janos");
        owner2.setPassword("1234567");
        owner2.setPhoneNumber("697555555");
        owner2.setSurname("vaz");
        owner2.setUsername("janv");
        owner2.setVatNumber(444444444);
        owner2.setAddress("j 4");
        assertThrows(PersistenceException.class, () -> service.registerOwner(owner2));
    }

    @Test
    public void addOwnerWithSameEmailAndCheckIfOwnerIsNotAdded() throws PersistenceException, OwnerException {
        Owner owner1 = new Owner();
        owner1.setEmail("p@mail.com");
        owner1.setName("panos");
        owner1.setPassword("1234567");
        owner1.setPhoneNumber("697424444");
        owner1.setSurname("vaz");
        owner1.setUsername("panv");
        owner1.setVatNumber(444444444);
        owner1.setAddress("p 4");
        service.registerOwner(owner1);

        Owner owner2 = new Owner();
        owner2.setEmail("p@mail.com");
        owner2.setName("janos");
        owner2.setPassword("1234567");
        owner2.setPhoneNumber("697555555");
        owner2.setSurname("vaz");
        owner2.setUsername("janv");
        owner2.setVatNumber(55555555);
        owner2.setAddress("j 4");
        assertThrows(PersistenceException.class, () -> service.registerOwner(owner2));
    }

    @Test
    public void addNullPropertyAndCheckIfPropertyIsNotAdded() throws PropertyException {
        Property property = null;
        assertThrows(PropertyException.class, () -> service.registerProperty(property));
    }

    @Test
    public void addNullRepairAndCheckIfRepairIsNotAdded() throws RepairException {
        Repair repair = null;
        assertThrows(RepairException.class, () -> service.submitRepair(repair));
    }

    @Test
    public void addRepairsAndCheckIfTheyAreConnectedWithAppropriateOwner() throws Exception {
        Owner owner = new Owner();
        owner.setName("John");
        owner.setVatNumber(11235813L);
        Repair repair1 = new Repair();
        repair1.setOwner(owner);
        Repair repair2 = new Repair();
        repair2.setOwner(owner);
        service.registerOwner(owner);
        //PROBLEM IN READ(), WON'T RUN
        assertEquals(2, service.findRepairs(owner.getVatNumber()).size());
    }
}
