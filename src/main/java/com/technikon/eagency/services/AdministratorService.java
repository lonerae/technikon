package com.technikon.eagency.services;

import com.technikon.eagency.enums.StatusType;
import com.technikon.eagency.exceptions.RepairException;
import com.technikon.eagency.model.Repair;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public interface AdministratorService {

    /**
     * Instantiates the cost of the Repair with the given id.
     *
     * @param repairId
     * @param proposedCost
     * @throws RepairException
     */
    void proposeCost(int repairId, BigDecimal proposedCost) throws RepairException;

    /**
     * Instantiates the start date of the Repair with the given id.
     *
     * @param repairId
     * @param proposedStartDate
     * @throws RepairException if no Repair with given id exists or if the given
     * date is null
     */
    void proposeStartDate(int repairId, LocalDate proposedStartDate) throws RepairException;

    /**
     * Instantiates the proposed end date of the Repair with the given id.
     *
     * @param repairId
     * @param proposedEndDate
     * @throws RepairException if no Repair with given id exists or if the given
     * date is null
     */
    void proposeEndDate(int repairId, LocalDate proposedEndDate) throws RepairException;

    /**
     * Updates the status of the Repair with the given id.
     *
     * @param repairId
     * @param status
     * @throws RepairException if no Repair with given id exists or if the given
     * status is null
     */
    void updateStatusType(int repairId, StatusType status) throws RepairException;

    /**
     * Instantiates and returns the start date of the Repair with the given id.
     *
     * @param repairId
     * @param dateOfStart
     * @return start date as LocalDate object
     * @throws RepairException if no Repair with given id exists
     */
    LocalDate checkStartDate(int repairId, LocalDate dateOfStart) throws RepairException;

    /**
     * Instantiates and returns the end date of Repair with the given id.
     *
     * @param repairId
     * @param dateOfEnd
     * @return end date as LocalDate object
     * @throws RepairException if no Repair with given id exists
     */
    LocalDate checkEndDate(int repairId, LocalDate dateOfEnd) throws RepairException;

    /**
     * Returns all Repairs with PENDING status.
     *
     * @return list of Repair objects
     */
    List<Repair> findAllPendingRepairs();

    /**
     * Returns all Repairs.
     *
     * @return list of Repair objects
     */
    List<Repair> findAllRepairs();

}
