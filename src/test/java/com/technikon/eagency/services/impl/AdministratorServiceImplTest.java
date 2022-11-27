/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package com.technikon.eagency.services.impl;

import com.technikon.eagency.exceptions.RepairException;
import com.technikon.eagency.model.Repair;
import com.technikon.eagency.repository.OwnerRepository;
import com.technikon.eagency.repository.PropertyRepository;
import com.technikon.eagency.repository.RepairRepository;
import com.technikon.eagency.repository.impl.OwnerRepositoryImpl;
import com.technikon.eagency.repository.impl.PropertyRepositoryImpl;
import com.technikon.eagency.repository.impl.RepairRepositoryImpl;
import com.technikon.eagency.services.AdministratorService;
import com.technikon.eagency.services.OwnerService;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.jupiter.api.BeforeEach;

/**
 *
 * @author panos
 */
public class AdministratorServiceImplTest {
    
    public AdministratorServiceImplTest() {
    }
    
    private final OwnerRepository ownerRepository = new OwnerRepositoryImpl();
    private final PropertyRepository propertyRepository = new PropertyRepositoryImpl();
    private final RepairRepository repairRepository = new RepairRepositoryImpl();
    private AdministratorService administratorService;
    
    @BeforeEach
    public void beforeEach() {
        administratorService = new AdministratorServiceImpl(repairRepository);
    }

    /**
     * Test of proposeCost method, of class AdministratorServiceImpl.
     */
    @Test
    public void addNullProposeCostAndCheckIfAdded() throws RepairException {
        System.out.println("proposeCost");
        int repairId = 0;
        BigDecimal proposedCost = null;
        AdministratorServiceImpl instance = null;
        instance.proposeCost(repairId, proposedCost);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of proposeStartDate method, of class AdministratorServiceImpl.
     */
    @Test
    public void testProposeStartDate() throws Exception {
        System.out.println("proposeStartDate");
        int repairId = 0;
        LocalDate proposedStartDate = null;
        AdministratorServiceImpl instance = null;
        instance.proposeStartDate(repairId, proposedStartDate);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of proposeEndDate method, of class AdministratorServiceImpl.
     */
    @Test
    public void testProposeEndDate() throws Exception {
        System.out.println("proposeEndDate");
        int repairId = 0;
        LocalDate proposedEndDate = null;
        AdministratorServiceImpl instance = null;
        instance.proposeEndDate(repairId, proposedEndDate);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of checkStartDate method, of class AdministratorServiceImpl.
     */
    @Test
    public void testCheckStartDate() throws Exception {
        System.out.println("checkStartDate");
        int repairId = 0;
        AdministratorServiceImpl instance = null;
        LocalDate expResult = null;
        LocalDate result = instance.checkStartDate(repairId);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of checkEndDate method, of class AdministratorServiceImpl.
     */
    @Test
    public void testCheckEndDate() throws Exception {
        System.out.println("checkEndDate");
        int repairId = 0;
        AdministratorServiceImpl instance = null;
        LocalDate expResult = null;
        LocalDate result = instance.checkEndDate(repairId);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of findAllPendingRepairs method, of class AdministratorServiceImpl.
     */
    @Test
    public void testFindAllPendingRepairs() {
        System.out.println("findAllPendingRepairs");
        AdministratorServiceImpl instance = null;
        List<Repair> expResult = null;
        List<Repair> result = instance.findAllPendingRepairs();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of findAllRepairs method, of class AdministratorServiceImpl.
     */
    @Test
    public void testFindAllRepairs() {
        System.out.println("findAllRepairs");
        AdministratorServiceImpl instance = null;
        List<Repair> expResult = null;
        List<Repair> result = instance.findAllRepairs();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
