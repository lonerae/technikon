package com.technikon.eagency.model;

import com.technikon.eagency.enums.PropertyType;

/**
 *
 * @author Nick
 */
public class Property extends PersistentClass {

    private long propertyId;
    private String address;
    private int yearOfConstruction;
    private int vatNumberOfOwner;
    private PropertyType propertyType;

    public long getPropertyId() {
        return propertyId;
    }

    public void setPropertyId(long propertyId) {
        this.propertyId = propertyId;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getYearOfConstruction() {
        return yearOfConstruction;
    }

    public void setYearOfConstruction(int yearOfConstruction) {
        this.yearOfConstruction = yearOfConstruction;
    }

    public int getVatNumberOfOwner() {
        return vatNumberOfOwner;
    }

    public void setVatNumberOfOwner(int vatNumberOfOwner) {
        this.vatNumberOfOwner = vatNumberOfOwner;
    }

    public PropertyType getPropertyType() {
        return propertyType;
    }

    public void setPropertyType(PropertyType propertyType) {
        this.propertyType = propertyType;
    }

}
