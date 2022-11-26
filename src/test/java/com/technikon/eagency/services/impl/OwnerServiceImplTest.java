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
import com.technikon.eagency.repository.impl.OwnerRepositoryImpl;
import com.technikon.eagency.repository.impl.PropertyRepositoryImpl;
import com.technikon.eagency.repository.impl.RepairRepositoryImpl;
import com.technikon.eagency.services.OwnerService;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;

/**
 *
 * @author lonerae
 */
public class OwnerServiceImplTest {

    private final OwnerRepository ownerRepository = new OwnerRepositoryImpl();
    private final PropertyRepository propertyRepository = new PropertyRepositoryImpl();
    private final RepairRepository repairRepository = new RepairRepositoryImpl();
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
    public void addOwnerWithSameVatNumberAndCheckIfOwnerIsNotAdded() throws OwnerException {
        Owner owner1 = new Owner();
        owner1.setVatNumber(123456789L);
        service.registerOwner(owner1);
        Owner owner2 = new Owner();
        owner2.setVatNumber(123456789L);
        assertThrows(OwnerException.class, () -> service.registerOwner(owner2));
    }
    
    @Test
    public void addOwnerWithSameEmailAndCheckIfOwnerIsNotAdded() throws OwnerException {
        Owner owner1 = new Owner();
        owner1.setEmail("placeholder@test.com");
        service.registerOwner(owner1);
        Owner owner2 = new Owner();
        owner2.setEmail("placeholder@test.com");
        assertThrows(OwnerException.class, () -> service.registerOwner(owner2));
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
