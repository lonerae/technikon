package com.technikon.eagency.model;

import java.time.LocalDate;

/**
 *
 * @author Nick
 */
public class Property extends PersistentClass {

    private String address;
    private LocalDate constructionYear;
    private long id;

    public Property() {
    }

    public Property(String address, LocalDate constructionYear, long id) {
        this.address = address;
        this.constructionYear = constructionYear;
        this.id = id;
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
        return "Property{" + "address=" + address + ", constructionYear=" + constructionYear + ", id=" + id + '}';
    }

}
