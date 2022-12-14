package com.technikon.eagency.services;

import com.technikon.eagency.exceptions.OwnerException;
import com.technikon.eagency.exceptions.PropertyException;
import com.technikon.eagency.exceptions.RepairException;
import com.technikon.eagency.model.Owner;
import com.technikon.eagency.model.Property;
import com.technikon.eagency.model.Repair;
import java.time.LocalDate;
import java.util.List;

public interface OwnerService {

    /**
     * Adds a new owner to the Owner database.
     *
     * @param owner
     * @throws OwnerException when owner is null or already exists (duplicate
     * VAT number or email)
     */
    void registerOwner(Owner owner) throws OwnerException;

    /**
     * Adds a new property to the Property database.
     *
     * @param property
     * @throws PropertyException when property is null or misses an address
     */
    void registerProperty(Property property) throws PropertyException;

    /**
     * Adds a new repair to the Repair database.
     *
     * @param repair
     * @throws RepairException when repair is null or misses a repair type
     */
    void submitRepair(Repair repair) throws RepairException;

    /**
     * Searches for an owner in the Owner database.
     *
     * @param vatNumber
     * @return owner with given VAT number or null if none is found
     */
    Owner findOwner(long vatNumber);

    /**
     * Searches for an owner in the Owner database.
     *
     * @param email
     * @return owner with given email or null if none is found
     */
    Owner findOwner(String email);

    /**
     * Searches for a property in the Property database.
     *
     * @param propertyId
     * @return property with given E9 or null if none is found
     */
    Property findProperty(long propertyId);

    /**
     * Searches for properties in the Property database.
     *
     * @param vatNumberOfOwner
     * @return list of properties that belong to owner with given VAT number or
     * an empty list
     */
    List<Property> findProperties(long vatNumberOfOwner);

    /**
     * Searches for repairs in the Repair database.
     *
     * @param startDate
     * @return list or repairs that are set to start on given date or an empty
     * list
     */
    List<Repair> findRepairs(LocalDate startDate);

    /**
     * Searches for repairs in the Repair database.
     *
     * @param startDate
     * @param endDate
     * @return list or repair that are set to start and end on the given dates
     * or an empty list
     */
    List<Repair> findRepairs(LocalDate startDate, LocalDate endDate);

    /**
     * Searches for repairs in the Repair database.
     *
     * @param vatNumberOfOwner
     * @return list or repairs that are set to happen on properties of owner
     * with given VAT number or an empty list
     */
    List<Repair> findRepairs(long vatNumberOfOwner);

    /**
     * Changes the address of the owner with the given id and updates the Owner
     * database.
     *
     * @param ownerId
     * @param address
     * @return true if update was successful, false otherwise
     */
    boolean updateAddress(int ownerId, String address);

    /**
     * Changes the email of the owner with the given id and updates the Owner
     * database.
     *
     * @param ownerId
     * @param email
     * @return true if update was successful, false otherwise
     */
    boolean updateEmail(int ownerId, String email);

    /**
     * Changes the password of the owner with the given id and updates the Owner
     * database.
     *
     * @param ownerId
     * @param password
     * @return true if update was successful, false otherwise
     */
    boolean updatePassword(int ownerId, String password);

    /**
     * Overwrites the fields of a property with the same primary id in the
     * database.
     *
     * @param property
     * @return true if update was successful, false otherwise
     */
    boolean update(Property property);

    /**
     * Instantiates acceptance field to {@code acceptance} and updates the
     * database.
     *
     * @param repairId
     * @param acceptance
     * @return true if acceptance was successfully submitted, false otherwise
     */
    boolean acceptRepair(int repairId, boolean acceptance);

    /**
     * Gives a report with information of an owner's active properties and
     * repairs.
     *
     * @param vatNumberOfOwner
     * @return a list with all the repairs of owner with given VAT number
     */
    List<Repair> getReport(long vatNumberOfOwner);

}
