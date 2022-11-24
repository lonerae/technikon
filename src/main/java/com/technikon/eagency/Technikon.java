package com.technikon.eagency;

import com.technikon.eagency.repository.OwnerRepository;
import com.technikon.eagency.repository.PropertyRepository;
import com.technikon.eagency.repository.RepairRepository;
import com.technikon.eagency.repository.impl.OwnerRepositoryImpl;
import com.technikon.eagency.repository.impl.PropertyRepositoryImpl;
import com.technikon.eagency.repository.impl.RepairRepositoryImpl;
import com.technikon.eagency.services.AdministratorService;
import com.technikon.eagency.services.OwnerService;
import com.technikon.eagency.services.impl.AdministratorServiceImpl;
import com.technikon.eagency.services.impl.OwnerServiceImpl;

/**
 *
 * @author Nick
 */
public class Technikon {

    public static void main(String[] args) {
        
        OwnerRepository ownerRepo = new OwnerRepositoryImpl();
        PropertyRepository propertyRepo = new PropertyRepositoryImpl();
        RepairRepository repairRepo = new RepairRepositoryImpl();
        
        OwnerService ownerService = new OwnerServiceImpl(ownerRepo, propertyRepo, repairRepo);
        AdministratorService adminService = new AdministratorServiceImpl(repairRepo);
        
    }
}
