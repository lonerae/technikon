package com.technikon.eagency.model;

import com.technikon.eagency.enums.PropertyType;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import java.util.List;

@Entity
public class Property extends PersistentClass {

    private long propertyId;
    private String address;
    private int yearOfConstruction;
    private PropertyType propertyType;

    @ManyToOne
    private Owner owner;

    @OneToMany(mappedBy = "property")
    private List<Repair> repairList;

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

    public PropertyType getPropertyType() {
        return propertyType;
    }

    public void setPropertyType(PropertyType propertyType) {
        this.propertyType = propertyType;
    }

    public Owner getOwner() {
        return owner;
    }

    public void setOwner(Owner owner) {
        this.owner = owner;
    }

    public List<Repair> getRepairList() {
        return repairList;
    }

    public void setRepairList(List<Repair> repairList) {
        this.repairList = repairList;
    }
    
    
}
