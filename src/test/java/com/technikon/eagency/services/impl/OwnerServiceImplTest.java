/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package com.technikon.eagency.services.impl;

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

/**
 *
 * @author lonerae
 */
public class OwnerServiceImplTest {

    private final OwnerRepository ownerRepo;
    private final PropertyRepository propertyRepo;
    private final RepairRepository repairRepo;
    private final OwnerService service;
    
    public OwnerServiceImplTest() {
        ownerRepo = new OwnerRepositoryImpl();
        propertyRepo = new PropertyRepositoryImpl();
        repairRepo = new RepairRepositoryImpl();
        service = new OwnerServiceImpl(ownerRepo, propertyRepo, repairRepo);
    }

    @Test
    public void addNullPropertyAndCheckIfPropertyIsNotAdded() {
        Property property = null;
        service.registerProperty(property);
        assertEquals(0, propertyRepo.read().size());
    }
    
    @Test
    public void addNullRepairAndCheckIfRepairIsNotAdded() {
        Repair repair = null;
        service.submitRepair(repair);
        assertEquals(0, repairRepo.read().size());
    }
    
}
