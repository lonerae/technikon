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
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.List;
import java.util.Random;

import static java.io.File.separator;

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
        System.out.println("|-------------------START OF USE CASES-------------------|");

        System.out.println();
        System.out.println("|-------------------READ DATA FROM GIVEN CSV FILES-------------------|");
        ioService.readOwnersCsv("data" + separator + "owners.csv");
        ioService.readPropertiesCsv("data" + separator + "properties.csv");
        ioService.readRepairFromCsv("data" + separator + "repairs.csv");

        System.out.println();
        System.out.println("|-------------------DATA READ FROM GIVEN CSV FILES-------------------|");
    }

    /*4.2*/
    public static void ownerRegistrationWithTwoProperties() {
        System.out.println();
        System.out.println("|-------------------REGISTER NEW OWNER-------------------|");
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
            Owner dbOwner = ownerService.findOwner(owner.getVatNumber());
            System.out.println(pretty(dbOwner));
        } catch (OwnerException ex) {
            System.out.println(ex.getMessage());
        }

        System.out.println();
        System.out.println("|-------------------REGISTER NEW PROPERTIES-------------------|");
        Property property1 = new Property();
        property1.setPropertyId(123456789L);
        property1.setAddress("Athens");
        property1.setPropertyType(PropertyType.MAISONETTE);
        property1.setYearOfConstruction(1965);
        property1.setOwner(owner);
        try {
            ownerService.registerProperty(property1);
        } catch (PropertyException ex) {
            System.out.println(ex.getMessage());
        }

        Property property2 = new Property();
        property2.setPropertyId(56789L);
        property2.setAddress("Piraeus");
        property2.setPropertyType(PropertyType.APARTMENT_BUILDING);
        property2.setYearOfConstruction(2004);
        property2.setOwner(owner);
        try {
            ownerService.registerProperty(property2);
        } catch (PropertyException ex) {
            System.out.println(ex.getMessage());
        }

        List<Property> ownerProperties = ownerService.findProperties(owner.getVatNumber());
        for (Property ownerProperty : ownerProperties) {
            System.out.println(pretty(ownerProperty));
        }

        System.out.println();
        System.out.println("|-------------------CORRECT REGISTRATION MISTAKES-------------------|");
        correctionsOnWronglyInsertedData(owner, property1);
    }

    private static void correctionsOnWronglyInsertedData(Owner owner, Property property1) {
        ownerService.updateEmail(owner.getId(), "corrected@test.com");
        ownerService.updateAddress(owner.getId(), "Piraeus");
        ownerService.updatePassword(owner.getId(), "h3LL0w0r1d");
        Owner dbOwner = ownerService.findOwner(owner.getVatNumber());
        System.out.println(pretty(dbOwner));

        property1.setPropertyId(4242L);
        property1.setYearOfConstruction(1969);
        property1.setPropertyType(PropertyType.DETACHED_HOUSE);
        ownerService.update(property1);
        Property dbProperty1 = ownerService.findProperty(property1.getPropertyId());
        System.out.println(pretty(dbProperty1));

    }

    /*4.3*/
    public static void repairRegistration() {
        System.out.println();
        System.out.println("|-------------------SHOW ALL OWNER'S PROPERTIES-------------------|");
        List<Property> propertyList = ownerService.findProperties(owner.getVatNumber());
        for (Property property : propertyList) {
            System.out.println(pretty(property));
        }

        System.out.println();
        System.out.println("|-------------------REGISTER NEW REPAIR-------------------|");
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
            List<Repair> dbRepairList = ownerService.findRepairs(owner.getVatNumber());
            for (Repair dbRepair : dbRepairList) {
                System.out.println(pretty(dbRepair));
            }
        } catch (RepairException ex) {
            System.out.println(ex.getMessage());
        }
    }

    /*4.4*/
    public static void repairAdministration() {
        System.out.println();
        System.out.println("|-------------------SHOW ALL PENDING REPAIRS-------------------|");
        List<Repair> pendingRepairList = adminService.findAllPendingRepairs();
        for (Repair pendingRepair : pendingRepairList) {
            System.out.println(pretty(pendingRepair));
        }

        System.out.println();
        System.out.println("|-------------------ACCEPTING REPAIR SCENARIO-------------------|");

        //hard coded for testing purposes
        int maxCost = 5000;
        int minCost = 1000;
        int rangeOfCost = maxCost - minCost + 1;

        for (Repair pendingRepair : pendingRepairList) {
            try {
                adminProposes(rangeOfCost, minCost, pendingRepair);
                boolean acceptance = ownerAccepts(pendingRepair);
                adminUpdates(acceptance, pendingRepair);
            } catch (RepairException ex) {
                System.out.println(ex.getMessage());
            }
        }
        System.out.println();
        System.out.println("|-------------------FINISHED SCENARIO-------------------|");
    }

    private static void adminProposes(int rangeOfCost, int minCost, Repair pendingRepair) throws RepairException {
        double proposedCost = (Math.random() * rangeOfCost) + minCost;
        adminService.proposeCost(pendingRepair.getId(), BigDecimal.valueOf(proposedCost)
                .setScale(1, RoundingMode.HALF_UP));
        LocalDate proposedDateOfStart = pendingRepair.getDateOfSubmission().plusWeeks(1);
        adminService.proposeStartDate(pendingRepair.getId(), proposedDateOfStart);
        LocalDate proposedDateOfEnd = pendingRepair.getProposedDateOfStart().plusWeeks(2);
        adminService.proposeEndDate(pendingRepair.getId(), proposedDateOfEnd);
    }

    private static boolean ownerAccepts(Repair pendingRepair) throws RepairException {
        Random random = new Random();
        boolean acceptance = random.nextBoolean();
        ownerService.acceptRepair(pendingRepair.getId(), acceptance);
        return acceptance;
    }

    private static void adminUpdates(boolean acceptance, Repair pendingRepair) throws RepairException {
        if (acceptance) {
            adminService.updateStatusType(pendingRepair.getId(), StatusType.IN_PROGRESS);
            adminService.checkStartDate(pendingRepair.getId(), pendingRepair.getProposedDateOfStart());
            adminService.checkEndDate(pendingRepair.getId(), pendingRepair.getProposedDateOfEnd());
        } else {
            adminService.updateStatusType(pendingRepair.getId(), StatusType.DECLINED);
        }
    }

    /*4.5*/
    public static void reports() {
        System.out.println();
        System.out.println("|-------------------SHOW REPAIRS OF OWNER-------------------|");
        List<Repair> ownerRepairs = ownerService.getReport(owner.getVatNumber());
        for (Repair repair : ownerRepairs) {
            System.out.println(pretty(repair.getProperty()));
            System.out.println("Repair status for this property: \n" + pretty(repair));
        }

        System.out.println();
        System.out.println("|-------------------SHOW REPAIRS OF ALL OWNERS-------------------|");
        List<Repair> repairList = adminService.findAllRepairs();
        for (Repair repair : repairList) {
            System.out.println(pretty(repair));
        }

        System.out.println();
        System.out.println("|-------------------END OF USE CASES-------------------|");
    }

    /*------------------------------------------------------------------------*/
    public static void saveChanges() {
        ioService.saveOwnersToCsv("data" + separator + "owners.csv");
        ioService.savePropertiesToCsv("data" + separator + "properties.csv");
        ioService.saveRepairsToCsv("data" + separator + "repairs.csv");
    }

    private static String pretty(Owner owner) {
        return """
               {
               \tName: """ + owner.getName() + "\n"
                + "\tSurname: " + owner.getSurname() + "\n"
                + "\tVAT number: " + owner.getVatNumber() + "\n"
                + "\tEmail: " + owner.getEmail() + "\n"
                + "\tAddress: " + owner.getAddress() + "\n"
                + "\tPhone number: " + owner.getPhoneNumber() + "\n"
                + "\tUsername: " + owner.getUsername() + "\n"
                + "\tPassword: " + owner.getPassword() + "\n"
                + "}";
    }

    private static String pretty(Property property) {
        return """
               {
               \tE9: """ + property.getPropertyId() + "\n"
                + "\tOwner VAT number: " + property.getOwner().getVatNumber() + "\n"
                + "\tProperty type: " + property.getPropertyType() + "\n"
                + "\tAddress: " + property.getAddress() + "\n"
                + "\tYear of Construction: " + property.getYearOfConstruction() + "\n"
                + "}";
    }

    private static String pretty(Repair repair) {
        return """
               {
               \tOwner VAT number: """ + repair.getOwner().getVatNumber() + "\n"
                + "\tProperty E9: " + repair.getProperty().getPropertyId() + "\n"
                + "\tStatus: " + repair.getStatusType() + "\n"
                + "\tCost: " + repair.getProposedCost() + "\n"
                + "\tStart date: " + repair.getDateOfStart() + "\n"
                + "\tEnd date: " + repair.getDateOfEnd() + "\n"
                + "\tType of repair: " + repair.getRepairtype() + "\n"
                + "\tShort description: " + repair.getShortDescription() + "\n"
                + "}";
    }
}
