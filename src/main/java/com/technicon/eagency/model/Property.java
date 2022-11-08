package com.technicon.eagency.model;

import java.time.LocalDate;

/**
 *
 * @author Nick
 */
public class Property {

    private long propertyId;
    private String address;
    private LocalDate constructionYear;
    private long id;

    public Property() {
    }

    public Property(long propertyId, String address, LocalDate constructionYear, long id) {
        this.propertyId = propertyId;
        this.address = address;
        this.constructionYear = constructionYear;
        this.id = id;
    }

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

    public LocalDate getConstructionYear() {
        return constructionYear;
    }

    public void setConstructionYear(LocalDate constructionYear) {
        this.constructionYear = constructionYear;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Property{" + "propertyId=" + propertyId + ", address=" + address
                + ", constructionYear=" + constructionYear + ", id=" + id + '}';
    }
}
