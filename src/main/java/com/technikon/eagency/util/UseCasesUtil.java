package com.technikon.eagency.util;

import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
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
import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

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
    public static void ownerRegistrationWithTwoProperties() {
        owner = new Owner();
        owner.setVatNumber(11235813L);
        owner.setName("John");
        owner.setSurname("Doe");
        owner.setAddress("Athens");
        owner.setEmail("placeholder@test.com");
        owner.setPhoneNumber("6999999999");
        owner.setUsername("jdoe");
        owner.setPassword("password");
        try {
            ownerService.registerOwner(owner);
        } catch (OwnerException ex) {
        }

        Property property1 = new Property();
        property1.setPropertyId(123456789L);
        property1.setAddress("Athens");
        property1.setPropertyType(PropertyType.MAISONETTE);
        property1.setYearOfConstruction(1965);
        property1.setOwner(owner);
        try {
            ownerService.registerProperty(property1);
        } catch (PropertyException ex) {
        }

        Property property2 = new Property();
        property2.setPropertyId(987654321L);
        property2.setAddress("Piraeus");
        property2.setPropertyType(PropertyType.APARTMENT_BUILDING);
        property2.setYearOfConstruction(2004);
        property2.setOwner(owner);
        try {
            ownerService.registerProperty(property2);
        } catch (PropertyException ex) {
        }

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
    public static void repairRegistration() {
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

        try {
            ownerService.submitRepair(repair);
        } catch (RepairException ex) {
        }
    }

    /*4.4*/
    public static void repairAdministration() {
        List<Repair> pendingRepairList = adminService.findAllPendingRepairs();
        int maxCost = 5000;
        int minCost = 1000;
        int rangeOfCost = maxCost - minCost + 1;
        Random random;
        for (Repair pendingRepair : pendingRepairList) {
            try {
                double proposedCost = (Math.random() * rangeOfCost) + minCost;
                adminService.proposeCost(pendingRepair.getId(), BigDecimal.valueOf(proposedCost)
                        .setScale(1, RoundingMode.HALF_UP));
                LocalDate proposedDateOfStart = pendingRepair.getDateOfSubmission().plusWeeks(1);
                adminService.proposeStartDate(pendingRepair.getId(), proposedDateOfStart);
                LocalDate proposedDateOfEnd = pendingRepair.getProposedDateOfStart().plusWeeks(2);
                adminService.proposeEndDate(pendingRepair.getId(), proposedDateOfEnd);

                random = new Random();
                boolean acceptance = random.nextBoolean();
                ownerService.acceptRepair(pendingRepair.getId(), acceptance);

                if (acceptance) {
                    adminService.updateStatusType(pendingRepair.getId(), StatusType.IN_PROGRESS);
                    adminService.checkStartDate(pendingRepair.getId(), pendingRepair.getProposedDateOfStart());
                    adminService.checkEndDate(pendingRepair.getId(), pendingRepair.getProposedDateOfEnd());
                } else {
                    adminService.updateStatusType(pendingRepair.getId(), StatusType.DECLINED);
                    adminService.checkStartDate(pendingRepair.getId(), null);
                    adminService.checkEndDate(pendingRepair.getId(), null);
                }
            } catch (RepairException repairException) {
            }
        }
    }

    /*4.5*/
    public static void reports() {
        Map<Long, StatusType> ownerRepairs = ownerService.getReport(owner.getVatNumber());
        System.out.println("-------------------------------------------------");
        System.out.println("Properties and Repair statuses of Owner " + owner.getName() + " " + owner.getSurname());
        System.out.println(new GsonBuilder().setPrettyPrinting().create().toJson(ownerRepairs));

        List<Repair> repairList = adminService.findAllRepairs();
        System.out.println("-------------------------------------------------");
        System.out.println("Repairs of Technikon (admin)");
        repairList.stream()
                .map(repair -> pretty(repair))
                .forEach(System.out::println);
    }

    private static String pretty(Repair repair) {
        return "{\n"
                + "\tOwner VAT number: " + repair.getOwner().getVatNumber() + "\n"
                + "\tProperty E9: " + repair.getProperty().getPropertyId() + "\n"
                + "\tStatus: " + repair.getStatusType() + "\n"
                + "\tCost: " + repair.getProposedCost() + "\n"
                + "\tStart date: " + repair.getDateOfStart() + "\n"
                + "\tEnd date: " + repair.getDateOfEnd() + "\n"
                + "\tType of repair: " + repair.getRepairtype() + "\n"
                + "\tShort description: " + repair.getShortDescription() + "\n"
                + "}";
    }

    /*------------------------------------------------------------------------*/
    public static void saveChanges() {
        ioService.saveOwnersToCsv("data/owners.csv");
        ioService.savePropertiesToCsv("data/properties.csv");
        ioService.saveRepairsToCsv("data/repairs.csv");
    }

}
