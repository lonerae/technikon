package com.technikon.eagency.services.impl;

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

/**
 *
 * @author Hara
 */
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
    public void saveOwnerToCsv(String filename) throws OwnerException {
        File file = new File(filename);
        List<Owner> ownerList = ownerRepository.read();
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
            logger.error("File {} could not be made.", filename);
        }
    }

    @Override
    public void savePropertyToCsv(String filename) throws PropertyException {
        File file = new File(filename);
        List<Property> propertyList = propertyRepository.read();
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
            logger.error("File {} could not be made.", filename);
        }
    }

    @Override
    public void saveRepairToCsv(String filename) throws RepairException {
        File file = new File(filename);
        List<Repair> repairList = repairRepository.read();
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
                        + repair.getDateOfSubmisssion() + ","
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
            logger.error("File {} could not be made.", filename);
        }
    }

    @Override
    public int readOwnerFromCsv(String filename) throws OwnerException {
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
                    owner.setId(Integer.parseInt(words[0]));
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
                    rowsRead++;
                } catch (NumberFormatException e) {
                    logger.warn("Row {} dropped.", rowsRead);
                    rowsRead++;
                }
            }
        } catch (FileNotFoundException ex) {
            logger.error("File {} could not be found.", filename);
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
                    property.setId(Integer.parseInt(words[0]));
                    property.setActive(Boolean.parseBoolean(words[1].trim()));
                    property.setPropertyId(Long.parseLong(words[2].trim()));
                    property.setAddress(words[3].trim());
                    property.setYearOfConstruction(Integer.parseInt(words[4].trim()));
                    property.setOwner(ownerRepository.read(Integer.parseInt(words[5].trim())));
                    property.setPropertyType(PropertyType.valueOf(words[6]));
                    propertyRepository.create(property);
                    rowsRead++;
                } catch (NumberFormatException e) {
                    logger.warn("Row {} dropped.", rowsRead);
                    rowsRead++;
                }
            }
        } catch (FileNotFoundException ex) {
            logger.error("File {} could not be found.", filename);
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
                    Property property = new Property();
                    Owner owner = new Owner();
                    repair.setId(Integer.parseInt(words[0]));
                    repair.setActive(Boolean.parseBoolean(words[1].trim()));
                    repair.setProperty(propertyRepository.read(Integer.parseInt(words[2].trim())));
                    repair.setShortDescription(words[3].trim());
                    repair.setOwner(ownerRepository.read(Integer.parseInt(words[4].trim())));
                    repair.setDateOfSubmisssion(LocalDate.parse(words[5].trim()));
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
                    rowsRead++;
                } catch (NumberFormatException e) {
                    logger.warn("Row {} dropped.", rowsRead);
                    rowsRead++;
                }
            }
        } catch (FileNotFoundException ex) {
            logger.error("File {} could not be found.", filename);
        }
        return rowsRead;
    }
}
