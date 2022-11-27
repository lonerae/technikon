package com.technikon.eagency.services.impl;

import com.technikon.eagency.enums.PropertyType;
import com.technikon.eagency.enums.RepairType;
import com.technikon.eagency.enums.StatusType;
import com.technikon.eagency.model.Owner;
import com.technikon.eagency.model.Property;
import com.technikon.eagency.model.Repair;
import com.technikon.eagency.repository.OwnerRepository;
import com.technikon.eagency.repository.PropertyRepository;
import com.technikon.eagency.repository.RepairRepository;
import com.technikon.eagency.services.IOService;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class IOServiceImpl implements IOService {

    private final OwnerRepository ownerRepository;
    private final PropertyRepository propertyRepository;
    private final RepairRepository repairRepository;
    private final Logger logger;

    public IOServiceImpl(OwnerRepository ownerRepository, PropertyRepository propertyRepository, RepairRepository repairRepository) {
        this.ownerRepository = ownerRepository;
        this.propertyRepository = propertyRepository;
        this.repairRepository = repairRepository;
        logger = LoggerFactory.getLogger(IOServiceImpl.class);
    }

    @Override
    public void saveOwnersToCsv(String filename) {
        List<Owner> ownerList = getOwners();
        createOwnerCsv(filename, ownerList);
    }

    private List<Owner> getOwners() {
        List<Owner> ownerList = ownerRepository.readAll();
        return ownerList;
    }

    private void createOwnerCsv(String filename, List<Owner> ownerList) {
        File file = new File(filename);
        try ( PrintWriter pw = new PrintWriter(file)) {
            populateOwnerCsv(pw, ownerList);
        } catch (FileNotFoundException ex) {
            logger.error("File {} for Owners could not be made.", filename);
        }
    }

    private void populateOwnerCsv(final PrintWriter pw, List<Owner> ownerList) {
        pw.println("﻿id,isActive,username,password,vatNumber,name,surname,address,phoneNumber,email");
        for (Owner owner : ownerList) {
            saveOwnerToCsv(pw, owner);
        }
    }

    private void saveOwnerToCsv(final PrintWriter pw, Owner owner) {
        pw.println(
                owner.getId() + ","
                + owner.isActive() + ","
                + owner.getUsername() + ","
                + owner.getPassword() + ","
                + owner.getVatNumber() + ","
                + owner.getName() + ","
                + owner.getSurname() + ","
                + owner.getAddress() + ","
                + owner.getPhoneNumber() + ","
                + owner.getEmail()
        );
    }

    @Override
    public void savePropertiesToCsv(String filename) {
        List<Property> propertyList = getProperties();
        createPropertyCsv(filename, propertyList);
    }

    private List<Property> getProperties() {
        List<Property> propertyList = propertyRepository.readAll();
        return propertyList;
    }

    private void createPropertyCsv(String filename, List<Property> propertyList) {
        File file = new File(filename);
        try ( PrintWriter pw = new PrintWriter(file)) {
            populatePropertyCsv(pw, propertyList);
        } catch (FileNotFoundException ex) {
            logger.error("File {} for Properties could not be made.", filename);
        }
    }

    private void populatePropertyCsv(final PrintWriter pw, List<Property> propertyList) {
        pw.println("﻿id,isActive,propertyId,address,yearOfConstruction,ownerId,propertyType");
        for (Property property : propertyList) {
            savePropertyToCsv(pw, property);
        }
    }

    private void savePropertyToCsv(final PrintWriter pw, Property property) {
        pw.println(
                property.getId() + ","
                + property.isActive() + ","
                + property.getPropertyId() + ","
                + property.getAddress() + ","
                + property.getYearOfConstruction() + ","
                + property.getOwner().getId() + ","
                + property.getPropertyType()
        );
    }

    @Override
    public void saveRepairsToCsv(String filename) {
        List<Repair> repairList = getRepairs();
        createRepairCsv(filename, repairList);
    }

    private List<Repair> getRepairs() {
        List<Repair> repairList = repairRepository.readAll();
        return repairList;
    }

    private void createRepairCsv(String filename, List<Repair> repairList) {
        File file = new File(filename);
        try ( PrintWriter pw = new PrintWriter(file)) {
            populateRepairCsv(pw, repairList);
        } catch (FileNotFoundException ex) {
            logger.error("File {} for Repairs could not be made.", filename);
        }
    }

    private void populateRepairCsv(final PrintWriter pw, List<Repair> repairList) {
        pw.println("﻿id,isActive,propertyId,shortDescription,ownerId,dateOfSubmission,"
                + "descriptionOfWork,proposedDateOfStart,proposedDateOfEnd,proposedCost,"
                + "acceptance,dateOfStart,dateOfEnd,repairType,statusType");
        for (Repair repair : repairList) {
            saveRepairToCsv(pw, repair);
        }
    }

    private void saveRepairToCsv(final PrintWriter pw, Repair repair) {
        pw.println(
                repair.getId() + ","
                + repair.isActive() + ","
                + repair.getProperty().getId() + ","
                + repair.getShortDescription() + ","
                + repair.getOwner().getId() + ","
                + repair.getDateOfSubmission() + ","
                + repair.getDescriptionOfWork() + ","
                + repair.getProposedDateOfStart() + ","
                + repair.getProposedDateOfEnd() + ","
                + repair.getProposedCost() + ","
                + repair.isAcceptance() + ","
                + repair.getDateOfStart() + ","
                + repair.getDateOfEnd() + ","
                + repair.getRepairtype() + ","
                + repair.getStatusType()
        );
    }

    @Override
    public int readOwnersCsv(String filename) {
        File file = new File(filename);
        int rowsRead = 0;
        rowsRead = readOwnersFromCsv(file, rowsRead, filename);
        return rowsRead;
    }

    private int readOwnersFromCsv(File file, int rowsRead, String filename) {
        try {
            Scanner scanner1 = new Scanner(file);
            scanner1.nextLine();
            while (scanner1.hasNext()) {
                rowsRead = readOwnerFromCsv(scanner1, rowsRead);
            }
        } catch (FileNotFoundException ex) {
            logger.error("File {} of Owners could not be found.", filename);
        }
        return rowsRead;
    }

    private int readOwnerFromCsv(Scanner scanner1, int rowsRead) {
        String line = scanner1.nextLine();
        rowsRead = saveOwnerToDatabaseAndProtect(line, rowsRead);
        return rowsRead;
    }

    private int saveOwnerToDatabaseAndProtect(String line, int rowsRead) {
        try {
            saveOwnerToDatabase(line);
            rowsRead++;
        } catch (NumberFormatException e) {
            logger.warn(e.getMessage());
            logger.warn("Row dropped.");
        }
        return rowsRead;
    }

    private void saveOwnerToDatabase(String line) throws NumberFormatException {
        String[] words = line.split(",");
        Owner owner = createOwner(words);
        ownerRepository.create(owner);
        logger.info("The registration of Owner with VAT number {} was successful.", owner.getVatNumber());
    }

    private Owner createOwner(String[] words) throws NumberFormatException {
        Owner owner = new Owner();
        owner.setActive(Boolean.parseBoolean(words[1].trim()));
        owner.setUsername(words[2].trim());
        owner.setPassword(words[3].trim());
        owner.setVatNumber(Long.parseLong(words[4].trim()));
        owner.setName(words[5].trim());
        owner.setSurname(words[6].trim());
        owner.setAddress(words[7].trim());
        owner.setPhoneNumber(words[8].trim());
        owner.setEmail(words[9].trim());
        return owner;
    }

    @Override
    public int readPropertiesCsv(String filename) {
        File file = new File(filename);
        int rowsRead = 0;
        rowsRead = readPropertiesFromCsv(file, rowsRead, filename);
        return rowsRead;
    }

    private int readPropertiesFromCsv(File file, int rowsRead, String filename) {
        try {
            Scanner scanner = new Scanner(file);
            scanner.nextLine();
            while (scanner.hasNext()) {
                rowsRead = readPropertyFromCsv(scanner, rowsRead);
            }
        } catch (FileNotFoundException ex) {
            logger.error("File {} of Properties could not be found.", filename);
        }
        return rowsRead;
    }

    private int readPropertyFromCsv(Scanner scanner, int rowsRead) {
        String line = scanner.nextLine();
        rowsRead = savePropertyToDatabaseAndProtect(line, rowsRead);
        return rowsRead;
    }

    private int savePropertyToDatabaseAndProtect(String line, int rowsRead) {
        try {
            savePropertyToDatabase(line);
            rowsRead++;
        } catch (NumberFormatException e) {
            logger.warn(e.getMessage());
            logger.warn("Row dropped.");
        }
        return rowsRead;
    }

    private void savePropertyToDatabase(String line) throws NumberFormatException {
        String[] words = line.split(",");
        Property property = createProperty(words);
        propertyRepository.create(property);
        logger.info("The registration of Property with E9 {} was successful.", property.getPropertyId());
    }

    private Property createProperty(String[] words) throws NumberFormatException {
        Property property = new Property();
        property.setActive(Boolean.parseBoolean(words[1].trim()));
        property.setPropertyId(Long.parseLong(words[2].trim()));
        property.setAddress(words[3].trim());
        property.setYearOfConstruction(Integer.parseInt(words[4].trim()));
        property.setOwner(ownerRepository.readById(Integer.parseInt(words[5].trim())));
        property.setPropertyType(PropertyType.valueOf(words[6]));
        return property;
    }

    @Override
    public int readRepairFromCsv(String filename) {
        File file = new File(filename);
        int rowsRead = 0;
        rowsRead = readRepairsFromCsv(file, rowsRead, filename);
        return rowsRead;
    }

    private int readRepairsFromCsv(File file, int rowsRead, String filename) {
        try {
            Scanner scanner = new Scanner(file);
            scanner.nextLine();
            while (scanner.hasNext()) {
                rowsRead = readRepairFromCsv(scanner, rowsRead);
            }
        } catch (FileNotFoundException ex) {
            logger.error("File {} of Repairs could not be found.", filename);
        }
        return rowsRead;
    }

    private int readRepairFromCsv(Scanner scanner, int rowsRead) {
        String line = scanner.nextLine();
        rowsRead = saveRepairToDatabaseAndProtect(line, rowsRead);
        return rowsRead;
    }

    private int saveRepairToDatabaseAndProtect(String line, int rowsRead) {
        try {
            String[] words = line.split(",");
            Repair repair = saveRepairToDatabase(words);
            logger.info("The submission of Repair on Property with id {}, of Owner with id {}, was successful.",
                    repair.getProperty().getId(), repair.getOwner().getId());
            rowsRead++;
        } catch (NumberFormatException e) {
            logger.warn(e.getMessage());
            logger.warn("Row dropped.");
        }
        return rowsRead;
    }

    private Repair saveRepairToDatabase(String[] words) throws NumberFormatException {
        Repair repair = createRepair(words);
        repairRepository.create(repair);
        return repair;
    }

    private Repair createRepair(String[] words) throws NumberFormatException {
        Repair repair = new Repair();
        repair.setActive(Boolean.parseBoolean(words[1].trim()));
        Property property = propertyRepository.readById(Integer.parseInt(words[2].trim()));
        repair.setProperty(property);
        repair.setShortDescription(words[3].trim());
        Owner owner = ownerRepository.readById(Integer.parseInt(words[4].trim()));
        repair.setOwner(owner);
        repair.setDateOfSubmission(LocalDate.parse(words[5].trim()));
        repair.setDescriptionOfWork(words[6].trim());
        repair.setProposedDateOfStart(LocalDate.parse(words[7].trim()));
        repair.setProposedDateOfEnd(LocalDate.parse(words[8].trim()));
        repair.setProposedCost(BigDecimal.valueOf(Double.parseDouble(words[9].trim())));
        repair.setAcceptance(Boolean.parseBoolean(words[10].trim()));
        repair.setDateOfStart(LocalDate.parse(words[11].trim()));
        repair.setDateOfEnd(LocalDate.parse(words[12].trim()));
        repair.setRepairtype(RepairType.valueOf(words[13].trim()));
        repair.setStatusType(StatusType.valueOf(words[14].trim()));
        return repair;
    }
}
