/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.technikon.eagency.services;

import com.technikon.eagency.exceptions.OwnerException;
import com.technikon.eagency.exceptions.PropertyException;
import com.technikon.eagency.exceptions.RepairException;

/**
 *
 * @author Hara
 */
public interface IOService {

    void saveOwnerToCsv(String filename) throws OwnerException;

    void savePropertyToCsv(String filename) throws PropertyException;

    void saveRepairToCsv(String filename) throws RepairException;

    int readOwnerFromCsv(String filename) throws OwnerException;

    int readPropertyFromCsv(String filename) throws PropertyException;

    int readRepairFromCsv(String filename) throws RepairException;

}
