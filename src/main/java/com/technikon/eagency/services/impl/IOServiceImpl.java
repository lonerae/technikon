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
    public void saveOwnerToCsv(String filename) {
        File file = new File(filename);
        List<Owner> ownerList = ownerRepository.readAll();
        try ( PrintWriter pw = new PrintWriter(file)) {
            pw.println("﻿id,isActive,username,password,vatNumber,name,surname,address,phoneNumber,email");
            for (Owner owner : ownerList) {
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
        } catch (FileNotFoundException ex) {
            logger.error("File {} for Owners could not be made.", filename);
        }
    }

    @Override
    public void savePropertyToCsv(String filename) {
        File file = new File(filename);
        List<Property> propertyList = propertyRepository.readAll();
        try ( PrintWriter pw = new PrintWriter(file)) {
            pw.println("﻿id,isActive,propertyId,address,yearOfConstruction,ownerId,propertyType");
            for (Property property : propertyList) {
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
        } catch (FileNotFoundException ex) {
            logger.error("File {} for Properties could not be made.", filename);
        }
    }

    @Override
    public void saveRepairToCsv(String filename) {
        File file = new File(filename);
        List<Repair> repairList = repairRepository.readAll();
        try ( PrintWriter pw = new PrintWriter(file)) {
            pw.println("﻿id,isActive,propertyId,shortDescription,ownerId,dateOfSubmission,"
                    + "descriptionOfWork,proposedDateOfStart,proposedDateOfEnd,proposedCost,"
                    + "acceptance,dateOfStart,dateOfEnd,repairType,statusType");
            for (Repair repair : repairList) {
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
        } catch (FileNotFoundException ex) {
            logger.error("File {} for Repairs could not be made.", filename);
        }
    }

    @Override
    public int readOwnerFromCsv(String filename) {
        File file = new File(filename);
        int rowsRead = 0;
        try {
            Scanner scanner1 = new Scanner(file);
            scanner1.nextLine();
            while (scanner1.hasNext()) {
                String line = scanner1.nextLine();
                try {
                    String[] words = line.split(",");
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
                    ownerRepository.create(owner);
                    logger.info("The registration of Owner with VAT number {} was successful.", owner.getVatNumber());
                    rowsRead++;
                } catch (NumberFormatException e) {
                    logger.warn(e.getMessage());
                    logger.warn("Row dropped.");
                }
            }
        } catch (FileNotFoundException ex) {
            logger.error("File {} of Owners could not be found.", filename);
        }
        return rowsRead;
    }

    @Override
    public int readPropertyFromCsv(String filename) {
        File file = new File(filename);
        int rowsRead = 0;
        try {
            Scanner scanner = new Scanner(file);
            scanner.nextLine();
            while (scanner.hasNext()) {
                String line = scanner.nextLine();
                try {
                    String[] words = line.split(",");
                    Property property = new Property();
                    property.setActive(Boolean.parseBoolean(words[1].trim()));
                    property.setPropertyId(Long.parseLong(words[2].trim()));
                    property.setAddress(words[3].trim());
                    property.setYearOfConstruction(Integer.parseInt(words[4].trim()));
                    property.setOwner(ownerRepository.readById(Integer.parseInt(words[5].trim())));
                    property.setPropertyType(PropertyType.valueOf(words[6]));
                    propertyRepository.create(property);
                    logger.info("The registration of Property with E9 {} was successful.", property.getPropertyId());
                    rowsRead++;
                } catch (NumberFormatException e) {
                    logger.warn(e.getMessage());
                    logger.warn("Row dropped.");
                }
            }
        } catch (FileNotFoundException ex) {
            logger.error("File {} of Properties could not be found.", filename);
        }
        return rowsRead;
    }

    @Override
    public int readRepairFromCsv(String filename) {
        File file = new File(filename);
        int rowsRead = 0;
        try {
            Scanner scanner = new Scanner(file);
            scanner.nextLine();
            while (scanner.hasNext()) {
                String line = scanner.nextLine();
                try {
                    String[] words = line.split(",");
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
                    repairRepository.create(repair);
                    logger.info("The submission of Repair on Property with id {}, of Owner with id {}, was successful.",
                            property.getId(), owner.getId());
                    rowsRead++;
                } catch (NumberFormatException e) {
                    logger.warn(e.getMessage());
                    logger.warn("Row dropped.");
                }
            }
        } catch (FileNotFoundException ex) {
            logger.error("File {} of Repairs could not be found.", filename);
        }
        return rowsRead;
    }
}