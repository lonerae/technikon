package com.technikon.eagency.util;

import com.technikon.eagency.enums.PropertyType;
import com.technikon.eagency.enums.RepairType;
import com.technikon.eagency.enums.StatusType;
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
import com.technikon.eagency.services.AdministratorService;
import com.technikon.eagency.services.IOService;
import com.technikon.eagency.services.OwnerService;
import com.technikon.eagency.services.impl.AdministratorServiceImpl;
import com.technikon.eagency.services.impl.IOServiceImpl;
import com.technikon.eagency.services.impl.OwnerServiceImpl;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Random;

public class UseCasesUtil {

    private static final OwnerRepository ownerRepo = new JPAOwnerRepositoryImpl();
    private static final PropertyRepository propertyRepo = new JPAPropertyRepositoryImpl();
    private static final RepairRepository repairRepo = new JPARepairRepositoryImpl();

    private static final IOService ioService = new IOServiceImpl(ownerRepo, propertyRepo, repairRepo);
    private static final OwnerService ownerService = new OwnerServiceImpl(ownerRepo, propertyRepo, repairRepo);
    private static final AdministratorService adminService = new AdministratorServiceImpl(repairRepo);

    private static Owner owner;

    /*4.1*/
    public static void dataPopulation() {
        ioService.readOwnersCsv("data/owners.csv");
        ioService.readPropertiesCsv("data/properties.csv");
        ioService.readRepairFromCsv("data/repairs.csv");
    }

    /*4.2*/
    public static void ownerRegistrationWithTwoProperties() throws OwnerException, PropertyException {
        owner = new Owner();
        owner.setVatNumber(11235813L);
        owner.setName("John");
        owner.setSurname("Doe");
        owner.setAddress("Athens");
        owner.setEmail("placeholder@test.com");
        owner.setPhoneNumber("6999999999");
        owner.setUsername("jdoe");
        owner.setPassword("password");
        ownerService.registerOwner(owner);

        Property property1 = new Property();
        property1.setPropertyId(123456789L);
        property1.setAddress("Athens");
        property1.setPropertyType(PropertyType.MAISONETTE);
        property1.setYearOfConstruction(1965);
        property1.setOwner(owner);
        ownerService.registerProperty(property1);

        Property property2 = new Property();
        property2.setPropertyId(987654321L);
        property2.setAddress("Piraeus");
        property2.setPropertyType(PropertyType.APARTMENT_BUILDING);
        property2.setYearOfConstruction(2004);
        property2.setOwner(owner);
        ownerService.registerProperty(property2);

        correctionsOnWronglyInsertedData(owner, property1);
    }

    private static void correctionsOnWronglyInsertedData(Owner owner, Property property1) {
        ownerService.updateEmail(owner.getId(), "corrected@test.com");
        ownerService.updateAddress(owner.getId(), "Piraeus");
        ownerService.updatePassword(owner.getId(), "h3LL0w0r1d");

        property1.setPropertyId(4242L);
        property1.setYearOfConstruction(1969);
        property1.setPropertyType(PropertyType.DETACHED_HOUSE);
        ownerService.update(property1);
    }

    /*4.3*/
    public static void repairRegistration() throws RepairException {
        List<Property> propertyList = ownerService.findProperties(owner.getVatNumber());
        System.out.println(propertyList);

        Repair repair = new Repair();
        repair.setOwner(owner);
        repair.setProperty(propertyList.get(0));
        repair.setDateOfSubmission(LocalDate.of(2022, 11, 27));
        repair.setDescriptionOfWork("Painting of the kitchen and living room walls in shades of blue.");
        repair.setShortDescription("Wall Painting");
        repair.setRepairtype(RepairType.PAINTING);
        repair.setStatusType(StatusType.PENDING);

        ownerService.submitRepair(repair);
    }

    /*4.4*/
    public static void repairAdministration() throws RepairException {
        List<Repair> pendingRepairList = adminService.findAllPendingRepairs();
        int maxCost = 5000;
        int minCost = 1000;
        int rangeOfCost = maxCost - minCost + 1;
        Random random = new Random();
        for (Repair pendingRepair : pendingRepairList) {
            double proposedCost = (Math.random() * rangeOfCost) + minCost;
            adminService.proposeCost(pendingRepair.getId(), BigDecimal.valueOf(proposedCost));
            LocalDate proposedDateOfStart = pendingRepair.getDateOfSubmission().plusWeeks(1);
            adminService.proposeStartDate(pendingRepair.getId(), proposedDateOfStart);
            LocalDate proposedDateOfEnd = pendingRepair.getProposedDateOfStart().plusWeeks(2);
            adminService.proposeEndDate(pendingRepair.getId(), proposedDateOfEnd);

            
            boolean acceptance = random.nextBoolean();
            ownerService.acceptRepair(pendingRepair.getId(), acceptance);
            
            if (acceptance) {
                adminService.updateStatusType(pendingRepair.getId(), StatusType.IN_PROGRESS);
                adminService.checkStartDate(pendingRepair.getId(), pendingRepair.getProposedDateOfStart());
                adminService.checkEndDate(pendingRepair.getId(), pendingRepair.getProposedDateOfEnd());
            } else {
                adminService.updateStatusType(pendingRepair.getId(), StatusType.DECLINED);
            }
        }
    }

    /*4.5*/
    public static void reports() {
        List<Repair> ownerRepairs = ownerService.findRepairs(owner.getVatNumber());
        ownerRepairs.stream()
                .map(ownerRepair -> "Owner: " + ownerRepair.getOwner().getVatNumber()
                + ", Property: " + ownerRepair.getProperty().getPropertyId())
                .forEach(System.out::println);

        List<Repair> repairList = adminService.findAllRepairs();
        repairList.stream()
                .map(ownerRepair -> "Owner: " + ownerRepair.getOwner().getVatNumber()
                + ", Property: " + ownerRepair.getProperty().getPropertyId()
                + ", Status: " + ownerRepair.getStatusType())
                .forEach(System.out::println);
    }

    /*------------------------------------------------------------------------*/
    public static void saveChanges() {
        ioService.saveOwnersToCsv("data/owners.csv");
        ioService.savePropertiesToCsv("data/properties.csv");
        ioService.saveRepairsToCsv("data/repairs.csv");
    }
}
