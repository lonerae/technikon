package com.technikon.eagency.services.impl;

import com.technikon.eagency.enums.PropertyType;
import com.technikon.eagency.enums.RepairType;
import com.technikon.eagency.enums.StatusType;
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
import com.technikon.eagency.services.AdministratorService;
import com.technikon.eagency.services.OwnerService;
import com.technikon.eagency.util.JPAUtil;
import java.math.BigDecimal;
import java.time.LocalDate;
import org.junit.jupiter.api.Test;
import static org.junit.Assert.*;
import org.junit.jupiter.api.BeforeEach;

public class AdministratorServiceImplTest {

    private OwnerRepository ownerRepository;
    private PropertyRepository propertyRepository;
    private RepairRepository repairRepository;
    private OwnerService ownerService;
    private AdministratorService administratorService;

    @BeforeEach
    public void beforeEach() {
        JPAUtil.shutdown();
        ownerRepository = new JPAOwnerRepositoryImpl();
        propertyRepository = new JPAPropertyRepositoryImpl();
        repairRepository = new JPARepairRepositoryImpl();
        ownerService = new OwnerServiceImpl(ownerRepository, propertyRepository, repairRepository);
        administratorService = new AdministratorServiceImpl(repairRepository);
    }

    @Test
    public void addNullProposedCostAndCheckIfAdded() throws Exception {
        Owner owner1 = new Owner();
        owner1.setEmail("john@mail.com");
        owner1.setName("John");
        owner1.setPassword("1234567");
        owner1.setPhoneNumber("697424444");
        owner1.setSurname("Johny");
        owner1.setUsername("JohnJohny");
        owner1.setVatNumber(444444444);
        owner1.setAddress("Johnion 4");
        ownerService.registerOwner(owner1);

        Property property1 = new Property();
        property1.setAddress("p 18");
        property1.setOwner(owner1);
        property1.setPropertyId(111111111);
        property1.setPropertyType(PropertyType.MAISONETTE);
        property1.setYearOfConstruction(1970);
        ownerService.registerProperty(property1);

        Repair repair1 = new Repair();
        repair1.setOwner(owner1);
        repair1.setDateOfSubmission(LocalDate.now());
        repair1.setRepairtype(RepairType.PAINTING);
        repair1.setProperty(property1);
        ownerService.submitRepair(repair1);

        BigDecimal proposedCost = null;
        assertThrows(RepairException.class, () -> administratorService.proposeCost(1, proposedCost));
    }

    @Test
    public void addNonexistentRepairAtProposedCostAndCheckIfAdded() throws Exception {
        Owner owner1 = new Owner();
        owner1.setEmail("john@mail.com");
        owner1.setName("John");
        owner1.setPassword("1234567");
        owner1.setPhoneNumber("697424444");
        owner1.setSurname("Johny");
        owner1.setUsername("JohnJohny");
        owner1.setVatNumber(444444444);
        owner1.setAddress("Johnion 4");
        ownerService.registerOwner(owner1);

        Property property1 = new Property();
        property1.setAddress("p 18");
        property1.setOwner(owner1);
        property1.setPropertyId(111111111);
        property1.setPropertyType(PropertyType.MAISONETTE);
        property1.setYearOfConstruction(1970);
        ownerService.registerProperty(property1);

        Repair repair1 = new Repair();
        repair1.setOwner(owner1);
        repair1.setDateOfSubmission(LocalDate.now());
        repair1.setRepairtype(RepairType.PAINTING);
        repair1.setProperty(property1);
        ownerService.submitRepair(repair1);

        BigDecimal proposedCost = BigDecimal.valueOf(340.5);
        assertThrows(RepairException.class, () -> administratorService.proposeCost(2, proposedCost));
    }

    @Test
    public void addRepairAtProposedCostAndCheckIfAdded() throws Exception {
        Owner owner1 = new Owner();
        owner1.setEmail("john@mail.com");
        owner1.setName("John");
        owner1.setPassword("1234567");
        owner1.setPhoneNumber("697424444");
        owner1.setSurname("Johny");
        owner1.setUsername("JohnJohny");
        owner1.setVatNumber(444444444);
        owner1.setAddress("Johnion 4");
        ownerService.registerOwner(owner1);

        Property property1 = new Property();
        property1.setAddress("p 18");
        property1.setOwner(owner1);
        property1.setPropertyId(111111111);
        property1.setPropertyType(PropertyType.MAISONETTE);
        property1.setYearOfConstruction(1970);
        ownerService.registerProperty(property1);

        Repair repair1 = new Repair();
        repair1.setOwner(owner1);
        repair1.setDateOfSubmission(LocalDate.now());
        repair1.setRepairtype(RepairType.PAINTING);
        repair1.setProperty(property1);
        ownerService.submitRepair(repair1);

        BigDecimal proposedCost = BigDecimal.valueOf(340.5);
        administratorService.proposeCost(1, proposedCost);
        assertTrue(proposedCost.equals(repairRepository.readById(1).getProposedCost()));
    }

    @Test
    public void addNullProposedStartDateAndCheckIfAdded() throws Exception {
        Owner owner1 = new Owner();
        owner1.setEmail("john@mail.com");
        owner1.setName("John");
        owner1.setPassword("1234567");
        owner1.setPhoneNumber("697424444");
        owner1.setSurname("Johny");
        owner1.setUsername("JohnJohny");
        owner1.setVatNumber(444444444);
        owner1.setAddress("Johnion 4");
        ownerService.registerOwner(owner1);

        Property property1 = new Property();
        property1.setAddress("p 18");
        property1.setOwner(owner1);
        property1.setPropertyId(111111111);
        property1.setPropertyType(PropertyType.MAISONETTE);
        property1.setYearOfConstruction(1970);
        ownerService.registerProperty(property1);

        Repair repair1 = new Repair();
        repair1.setOwner(owner1);
        repair1.setDateOfSubmission(LocalDate.now());
        repair1.setRepairtype(RepairType.PAINTING);
        repair1.setProperty(property1);
        ownerService.submitRepair(repair1);

        LocalDate proposedStartDate = null;
        assertThrows(RepairException.class, () -> administratorService.proposeStartDate(1, proposedStartDate));
    }

    @Test
    public void addNonexistentRepairAtProposedStartDateAndCheckIfAdded() throws Exception {
        Owner owner1 = new Owner();
        owner1.setEmail("john@mail.com");
        owner1.setName("John");
        owner1.setPassword("1234567");
        owner1.setPhoneNumber("697424444");
        owner1.setSurname("Johny");
        owner1.setUsername("JohnJohny");
        owner1.setVatNumber(444444444);
        owner1.setAddress("Johnion 4");
        ownerService.registerOwner(owner1);

        Property property1 = new Property();
        property1.setAddress("p 18");
        property1.setOwner(owner1);
        property1.setPropertyId(111111111);
        property1.setPropertyType(PropertyType.MAISONETTE);
        property1.setYearOfConstruction(1970);
        ownerService.registerProperty(property1);

        Repair repair1 = new Repair();
        repair1.setOwner(owner1);
        repair1.setDateOfSubmission(LocalDate.now());
        repair1.setRepairtype(RepairType.PAINTING);
        repair1.setProperty(property1);
        ownerService.submitRepair(repair1);

        LocalDate proposedStartDate = null;
        assertThrows(RepairException.class, () -> administratorService.proposeStartDate(2, proposedStartDate));
    }

    @Test
    public void addRepairAtProposedStartDateAndCheckIfAdded() throws Exception {
        Owner owner1 = new Owner();
        owner1.setEmail("john@mail.com");
        owner1.setName("John");
        owner1.setPassword("1234567");
        owner1.setPhoneNumber("697424444");
        owner1.setSurname("Johny");
        owner1.setUsername("JohnJohny");
        owner1.setVatNumber(444444444);
        owner1.setAddress("Johnion 4");
        ownerService.registerOwner(owner1);

        Property property1 = new Property();
        property1.setAddress("p 18");
        property1.setOwner(owner1);
        property1.setPropertyId(111111111);
        property1.setPropertyType(PropertyType.MAISONETTE);
        property1.setYearOfConstruction(1970);
        ownerService.registerProperty(property1);

        Repair repair1 = new Repair();
        repair1.setOwner(owner1);
        repair1.setDateOfSubmission(LocalDate.now());
        repair1.setRepairtype(RepairType.PAINTING);
        repair1.setProperty(property1);
        ownerService.submitRepair(repair1);

        LocalDate proposedStartDate = LocalDate.of(2022, 5, 5);
        administratorService.proposeStartDate(1, proposedStartDate);
        assertTrue(proposedStartDate.equals(repairRepository.readById(1).getProposedDateOfStart()));
    }

    @Test
    public void addNonexistentRepairAtProposedEndDateAndCheckIfAdded() throws Exception {
        Owner owner1 = new Owner();
        owner1.setEmail("john@mail.com");
        owner1.setName("John");
        owner1.setPassword("1234567");
        owner1.setPhoneNumber("697424444");
        owner1.setSurname("Johny");
        owner1.setUsername("JohnJohny");
        owner1.setVatNumber(444444444);
        owner1.setAddress("Johnion 4");
        ownerService.registerOwner(owner1);

        Property property1 = new Property();
        property1.setAddress("p 18");
        property1.setOwner(owner1);
        property1.setPropertyId(111111111);
        property1.setPropertyType(PropertyType.MAISONETTE);
        property1.setYearOfConstruction(1970);
        ownerService.registerProperty(property1);

        Repair repair1 = new Repair();
        repair1.setOwner(owner1);
        repair1.setDateOfSubmission(LocalDate.now());
        repair1.setRepairtype(RepairType.PAINTING);
        repair1.setProperty(property1);
        ownerService.submitRepair(repair1);

        LocalDate proposedEndDate = LocalDate.now();
        assertThrows(RepairException.class, () -> administratorService.proposeEndDate(2, proposedEndDate));
    }

    @Test
    public void addNullProposedEndDateAndCheckIfAdded() throws Exception {
        Owner owner1 = new Owner();
        owner1.setEmail("john@mail.com");
        owner1.setName("John");
        owner1.setPassword("1234567");
        owner1.setPhoneNumber("697424444");
        owner1.setSurname("Johny");
        owner1.setUsername("JohnJohny");
        owner1.setVatNumber(444444444);
        owner1.setAddress("Johnion 4");
        ownerService.registerOwner(owner1);

        Property property1 = new Property();
        property1.setAddress("p 18");
        property1.setOwner(owner1);
        property1.setPropertyId(111111111);
        property1.setPropertyType(PropertyType.MAISONETTE);
        property1.setYearOfConstruction(1970);
        ownerService.registerProperty(property1);

        Repair repair1 = new Repair();
        repair1.setOwner(owner1);
        repair1.setDateOfSubmission(LocalDate.now());
        repair1.setRepairtype(RepairType.PAINTING);
        repair1.setProperty(property1);
        ownerService.submitRepair(repair1);

        LocalDate proposedEndDate = null;
        assertThrows(RepairException.class, () -> administratorService.proposeEndDate(1, proposedEndDate));
    }

    @Test
    public void addRepairAtProposedEndDateAndCheckIfAdded() throws Exception {
        Owner owner1 = new Owner();
        owner1.setEmail("john@mail.com");
        owner1.setName("John");
        owner1.setPassword("1234567");
        owner1.setPhoneNumber("697424444");
        owner1.setSurname("Johny");
        owner1.setUsername("JohnJohny");
        owner1.setVatNumber(444444444);
        owner1.setAddress("Johnion 4");
        ownerService.registerOwner(owner1);

        Property property1 = new Property();
        property1.setAddress("p 18");
        property1.setOwner(owner1);
        property1.setPropertyId(111111111);
        property1.setPropertyType(PropertyType.MAISONETTE);
        property1.setYearOfConstruction(1970);
        ownerService.registerProperty(property1);

        Repair repair1 = new Repair();
        repair1.setOwner(owner1);
        repair1.setDateOfSubmission(LocalDate.now());
        repair1.setRepairtype(RepairType.PAINTING);
        repair1.setProperty(property1);
        ownerService.submitRepair(repair1);

        LocalDate proposedEndDate = LocalDate.of(2022, 5, 5);
        administratorService.proposeEndDate(1, proposedEndDate);
        assertTrue(proposedEndDate.equals(repairRepository.readById(1).getProposedDateOfEnd()));
    }

    @Test
    public void addNonexistentRepairAtCheckStartDateAndCheckIfReturns() throws Exception {
        assertThrows(RepairException.class, () -> administratorService.checkStartDate(0, LocalDate.now()));
    }

    @Test
    public void addRepairAtCheckStartDateAndCheckIfReturns() throws Exception {
        Owner owner1 = new Owner();
        owner1.setEmail("john@mail.com");
        owner1.setName("John");
        owner1.setPassword("1234567");
        owner1.setPhoneNumber("697424444");
        owner1.setSurname("Johny");
        owner1.setUsername("JohnJohny");
        owner1.setVatNumber(444444444);
        owner1.setAddress("Johnion 4");
        ownerService.registerOwner(owner1);

        Property property1 = new Property();
        property1.setAddress("p 18");
        property1.setOwner(owner1);
        property1.setPropertyId(111111111);
        property1.setPropertyType(PropertyType.MAISONETTE);
        property1.setYearOfConstruction(1970);
        ownerService.registerProperty(property1);

        Repair repair1 = new Repair();
        repair1.setOwner(owner1);
        repair1.setDateOfSubmission(LocalDate.now());
        repair1.setRepairtype(RepairType.PAINTING);
        repair1.setProperty(property1);
        repair1.setDateOfStart(LocalDate.of(2022, 5, 10));
        ownerService.submitRepair(repair1);

        assertTrue(LocalDate.now().equals(administratorService.checkStartDate(1, LocalDate.now())));
    }

    @Test
    public void addNonexistentRepairAtCheckEndDateAndCheckIfReturns() throws Exception {
        assertThrows(RepairException.class, () -> administratorService.checkEndDate(0, LocalDate.now()));
    }

    @Test
    public void addRepairsAndCheckIfFoundAllInReport() throws Exception {
        Owner owner1 = new Owner();
        owner1.setEmail("john@mail.com");
        owner1.setName("John");
        owner1.setPassword("1234567");
        owner1.setPhoneNumber("697424444");
        owner1.setSurname("Johny");
        owner1.setUsername("JohnJohny");
        owner1.setVatNumber(444444444);
        owner1.setAddress("Johnion 4");
        ownerService.registerOwner(owner1);

        Property property1 = new Property();
        property1.setAddress("p 18");
        property1.setOwner(owner1);
        property1.setPropertyId(111111111);
        property1.setPropertyType(PropertyType.MAISONETTE);
        property1.setYearOfConstruction(1970);
        ownerService.registerProperty(property1);

        Repair repair1 = new Repair();
        repair1.setOwner(owner1);
        repair1.setDateOfSubmission(LocalDate.now());
        repair1.setRepairtype(RepairType.PAINTING);
        repair1.setProperty(property1);
        repair1.setStatusType(StatusType.COMPLETE);
        ownerService.submitRepair(repair1);

        Repair repair2 = new Repair();
        repair2.setOwner(owner1);
        repair2.setDateOfSubmission(LocalDate.now());
        repair2.setRepairtype(RepairType.FRAMES);
        repair2.setProperty(property1);
        repair2.setStatusType(StatusType.PENDING);
        ownerService.submitRepair(repair2);

        Repair repair3 = new Repair();
        repair3.setOwner(owner1);
        repair3.setDateOfSubmission(LocalDate.now());
        repair3.setRepairtype(RepairType.FRAMES);
        repair3.setProperty(property1);
        repair3.setStatusType(StatusType.PENDING);
        ownerService.submitRepair(repair3);

        assertEquals(3, administratorService.findAllRepairs().size());
    }

    @Test
    public void addNoRepairsAndCheckIfFoundAllInReport() throws Exception {
        assertEquals(0, administratorService.findAllRepairs().size());
    }

    @Test
    public void addRepairsAndCheckIfFoundAllPendindInReport() throws Exception {
        Owner owner1 = new Owner();
        owner1.setEmail("john@mail.com");
        owner1.setName("John");
        owner1.setPassword("1234567");
        owner1.setPhoneNumber("697424444");
        owner1.setSurname("Johny");
        owner1.setUsername("JohnJohny");
        owner1.setVatNumber(444444444);
        owner1.setAddress("Johnion 4");
        ownerService.registerOwner(owner1);

        Property property1 = new Property();
        property1.setAddress("p 18");
        property1.setOwner(owner1);
        property1.setPropertyId(111111111);
        property1.setPropertyType(PropertyType.MAISONETTE);
        property1.setYearOfConstruction(1970);
        ownerService.registerProperty(property1);

        Repair repair1 = new Repair();
        repair1.setOwner(owner1);
        repair1.setDateOfSubmission(LocalDate.now());
        repair1.setRepairtype(RepairType.PAINTING);
        repair1.setProperty(property1);
        repair1.setStatusType(StatusType.COMPLETE);
        ownerService.submitRepair(repair1);

        Repair repair2 = new Repair();
        repair2.setOwner(owner1);
        repair2.setDateOfSubmission(LocalDate.now());
        repair2.setRepairtype(RepairType.FRAMES);
        repair2.setProperty(property1);
        repair2.setStatusType(StatusType.PENDING);
        ownerService.submitRepair(repair2);

        Repair repair3 = new Repair();
        repair3.setOwner(owner1);
        repair3.setDateOfSubmission(LocalDate.now());
        repair3.setRepairtype(RepairType.FRAMES);
        repair3.setProperty(property1);
        repair3.setStatusType(StatusType.PENDING);
        ownerService.submitRepair(repair3);

        assertEquals(2, administratorService.findAllPendingRepairs().size());
    }

    @Test
    public void addNoRepairsAndCheckIfFoundAllPendingInReport() throws Exception {
        assertEquals(0, administratorService.findAllPendingRepairs().size());
    }
}
