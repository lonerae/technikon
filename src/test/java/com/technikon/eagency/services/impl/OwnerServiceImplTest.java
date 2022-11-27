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
import com.technikon.eagency.services.OwnerService;
import jakarta.persistence.PersistenceException;
import java.time.LocalDate;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;

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
    public void addPropertyWithTheSamePropertyIdAndCheckIfPropertyIsNotAdded() throws Exception {
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

        Property property1 = new Property();
        property1.setAddress("ppp 18");
        property1.setOwner(owner1);
        property1.setPropertyId(111111111);
        property1.setPropertyType(PropertyType.MAISONETTE);
        property1.setYearOfConstruction(1970);
        service.registerProperty(property1);

        Property property2 = new Property();
        property2.setAddress("aaa 18");
        property2.setOwner(owner1);
        property2.setPropertyId(111111111);
        property2.setPropertyType(PropertyType.DETACHED_HOUSE);
        property2.setYearOfConstruction(1980);

        assertThrows(PersistenceException.class, () -> service.registerProperty(property2));
    }

    @Test
    public void addPropertyWithNoAddressAndCheckIfPropertyIsNotAdded() throws Exception {
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

        Property property1 = new Property();
        property1.setOwner(owner1);
        property1.setPropertyId(111111111);
        property1.setPropertyType(PropertyType.MAISONETTE);
        property1.setYearOfConstruction(1970);

        assertThrows(PropertyException.class, () -> service.registerProperty(property1));
    }

    @Test
    public void addNullRepairAndCheckIfRepairIsNotAdded() throws RepairException {
        Repair repair = null;
        assertThrows(RepairException.class, () -> service.submitRepair(repair));
    }

    @Test
    public void addRepairsAndCheckIfTheyAreConnectedWithAppropriateOwner() throws Exception {
        Owner owner1 = new Owner();
        owner1.setEmail("john@mail.com");
        owner1.setName("John");
        owner1.setPassword("1234567");
        owner1.setPhoneNumber("697424444");
        owner1.setSurname("Johny");
        owner1.setUsername("JohnJohny");
        owner1.setVatNumber(444444444);
        owner1.setAddress("Johnion 4");
        service.registerOwner(owner1);

        Property property1 = new Property();
        property1.setAddress("p 18");
        property1.setOwner(owner1);
        property1.setPropertyId(111111111);
        property1.setPropertyType(PropertyType.MAISONETTE);
        property1.setYearOfConstruction(1970);
        service.registerProperty(property1);

        Repair repair1 = new Repair();
        repair1.setOwner(owner1);
        repair1.setDateOfSubmission(LocalDate.now());
        repair1.setRepairtype(RepairType.PAINTING);
        repair1.setProperty(property1);
        service.submitRepair(repair1);

        Repair repair2 = new Repair();
        repair2.setOwner(owner1);
        repair2.setDateOfSubmission(LocalDate.now());
        repair2.setRepairtype(RepairType.PAINTING);
        repair2.setProperty(property1);
        service.submitRepair(repair2);

        assertEquals(2, service.findRepairs(owner1.getVatNumber()).size());
    }

    @Test
    public void addRepairWithNoTypeAndCheckIfNoAdded() throws Exception {
        Owner owner1 = new Owner();
        owner1.setEmail("john@mail.com");
        owner1.setName("John");
        owner1.setPassword("1234567");
        owner1.setPhoneNumber("697424444");
        owner1.setSurname("Johny");
        owner1.setUsername("JohnJohny");
        owner1.setVatNumber(444444444);
        owner1.setAddress("Johnion 4");
        service.registerOwner(owner1);

        Property property1 = new Property();
        property1.setAddress("p 18");
        property1.setOwner(owner1);
        property1.setPropertyId(111111111);
        property1.setPropertyType(PropertyType.MAISONETTE);
        property1.setYearOfConstruction(1970);
        service.registerProperty(property1);

        Repair repair1 = new Repair();
        repair1.setOwner(owner1);
        repair1.setDateOfSubmission(LocalDate.now());
        repair1.setProperty(property1);
        assertThrows(RepairException.class, () -> service.submitRepair(repair1));
    }

}
