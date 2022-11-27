/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package com.technikon.eagency.services.impl;

import com.technikon.eagency.enums.PropertyType;
import com.technikon.eagency.enums.RepairType;
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
import java.time.LocalDate;
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
        Owner o1 = new Owner();
        o1.setName("John");
        o1.setSurname("Doe");
        o1.setVatNumber(11235813L);
        o1.setAddress("athens");
        o1.setEmail("placeholder2@test.com");
        o1.setPhoneNumber("6999999999");
        o1.setUsername("jdoe");
        o1.setPassword("123456");
        service.registerOwner(o1);
        
        Owner o2 = new Owner();
        o2.setName("janos");
        o2.setSurname("vaz");
        o2.setVatNumber(55555555);
        o2.setAddress("j 4");
        o2.setEmail("placeholder2@test.com");
        o2.setPhoneNumber("697555555");
        o2.setUsername("janv");
        o2.setPassword("1234567");
        assertThrows(OwnerException.class, () -> service.registerOwner(o2));
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
        Owner o1 = new Owner();
        o1.setName("John");
        o1.setSurname("Doe");
        o1.setVatNumber(11235813L);
        o1.setAddress("athens");
        o1.setEmail("placeholder2@test.com");
        o1.setPhoneNumber("6999999999");
        o1.setUsername("jdoe");
        o1.setPassword("123456");
        service.registerOwner(o1);
        
        Property p1 = new Property();
        p1.setAddress("p 18");
        p1.setOwner(o1);
        p1.setPropertyId(111111111);
        p1.setPropertyType(PropertyType.MAISONETTE);
        p1.setYearOfConstruction(1970);
        service.registerProperty(p1);
        
        Repair r1 = new Repair();
        r1.setDateOfSubmission(LocalDate.of(2022, 7, 5));
        r1.setOwner(o1);
        r1.setRepairtype(RepairType.PAINTING);
        r1.setShortDescription("mpla");
        r1.setOwner(o1);
        r1.setProperty(p1);
        service.submitRepair(r1);
        
        Repair r2 = new Repair();
        r2.setDateOfSubmission(LocalDate.of(2022, 7, 5));
        r2.setRepairtype(RepairType.PAINTING);
        r2.setShortDescription("mpla");
        r2.setOwner(o1);
        r2.setProperty(p1);
        service.submitRepair(r2);
        
        assertEquals(2, service.findRepairs(o1.getVatNumber()).size());
    }
}
