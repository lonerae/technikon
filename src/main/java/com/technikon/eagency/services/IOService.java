/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.technikon.eagency.services;

import com.technikon.eagency.exceptions.OwnerException;

/**
 *
 * @author Hara
 */
public interface IOService {

    /*
    void saveOwnerToCsv (String filename) throws OwnerException;
    void savePropertyToCsv (String filename); 
    void saveRepairToCsv (String filename);
     */
    int readOwnerFromCsv(String filename) throws OwnerException;

    int readPropertyFromCsv(String filename);

    int readRepairFromCsv(String filename);

}
