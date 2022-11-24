

package com.technikon.eagency.services.impl;

import com.technikon.eagency.enums.PropertyType;
import com.technikon.eagency.enums.RepairType;
import com.technikon.eagency.enums.StatusType;
import com.technikon.eagency.exceptions.OwnerException;
import com.technikon.eagency.model.Owner;
import com.technikon.eagency.model.Property;
import com.technikon.eagency.model.Repair;
import com.technikon.eagency.repository.OwnerRepository;
import com.technikon.eagency.repository.PropertyRepository;
import com.technikon.eagency.repository.RepairRepository;
import com.technikon.eagency.services.IOService;
import java.io.File;
import java.io.FileNotFoundException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Hara
 */
public class IOServiceImpl implements IOService {

    private OwnerRepository ownerRepository;
    private PropertyRepository propertyRepository;
    private RepairRepository repairRepository;

    public IOServiceImpl(OwnerRepository ownerRepository) {
        this.ownerRepository = ownerRepository;
    }

    public IOServiceImpl(PropertyRepository propertyRepository) {
        this.propertyRepository = propertyRepository;
    }

    public IOServiceImpl(RepairRepository repairRepository) {
        this.repairRepository = repairRepository;
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
                } catch (Exception e) {
                    System.out.println("This row has been dropped");
                }
            }

        } catch (FileNotFoundException ex) {
            Logger.getLogger(IOServiceImpl.class.getName()).log(Level.SEVERE, null, ex);

        }
        return rowsRead;
    }

    @Override
    public int readPropertyFromCsv(String filename) {
        File file = new File(filename);
        int rowsRead = 0;
        
        try{
            Scanner scanner2 = new Scanner(file);
            
            scanner2.nextLine();
            while(scanner2.hasNext()){
                String line = scanner2.nextLine();
                try{
                    String[] words = line.split(",");
                    Property property = new Property();
                    //Owner owner = new Owner();
                    property.setId(Integer.parseInt(words[0]));
                    property.setPropertyId(Long.parseLong(words[1].trim()));
                    property.setAddress(words[2].trim());
                    property.setYearOfConstruction(Integer.parseInt(words[3].trim()));
                   // property.setOwner(ownerRepository.read(Integer.parseInt(words[4].trim())));
                    property.setPropertyType(PropertyType.valueOf(words[5]));
                    
                    propertyRepository.create(property);
                    rowsRead++;
                }
                catch(Exception e)
                {
                    System.out.println("This row has been dropped");
                }
            }
        }
        catch (FileNotFoundException ex) {
            Logger.getLogger(IOServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
      
    }
        return rowsRead;
    }
    

    @Override
    public int readRepairFromCsv(String filename) {
        
        File file = new File(filename);
        int rowsRead = 0;
        try{
            Scanner scanner3 = new Scanner(file);
            
            scanner3.nextLine();
            
            while (scanner3.hasNext()){
                String line = scanner3.nextLine();
                try{
                    String[] words = line.split(",");
                    Repair repair = new Repair();
                    Property property = new Property();
                    Owner owner = new Owner();
                    
                    repair.setId(Integer.parseInt(words[0]));
                    //repair.setProperty(propertyRepository.read(Integer.parseInt(words[1].trim())));
                    
                    repair.setShortDescription(words[2].trim());
                    //repair.setOwner(ownerRepository.read(Integer.parseInt(words[3].trim())));
           
                    repair.setDateOfSubmisssion(LocalDate.parse(words[4].trim()));
                    repair.setDescriptionOfWork(words[5].trim());
                    repair.setProposedDateOfStart(LocalDate.parse(words[6].trim()));
                    repair.setProposedDateOfEnd(LocalDate.parse(words[7].trim()));
                    repair.setProposedCost(BigDecimal.valueOf(Double.parseDouble(words[8].trim())));
                    repair.setAcceptance(Boolean.parseBoolean(words[9].trim()));
                    repair.setDateOfStart(LocalDate.parse(words[10].trim()));
                    repair.setDateOfEnd(LocalDate.parse(words[11].trim()));
                    repair.setRepairtype(RepairType.valueOf(words[12].trim()));
                    repair.setStatustype(StatusType.valueOf(words[13].trim()));
                    
                    repairRepository.create(repair);
                    rowsRead++;
                    
                }
                catch(Exception e)
                {
                    System.out.println("This row has been dropped");
                }
            }
            
        }
        catch (FileNotFoundException ex) {
            Logger.getLogger(IOServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
         return rowsRead;
        
    
    }
    }


