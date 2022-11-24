package com.technikon.eagency.model;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import java.util.List;

@Entity
public class Owner extends PersistentClass {

    private long vatNumber;
    private String username;
    private String password;
    private String name;
    private String surname;
    private String address;
    private String phoneNumber;
    private String email;

    @OneToMany(mappedBy = "owner")
    private List<Property> propertyList;

    @OneToMany(mappedBy = "owner")
    private List<Repair> repairList;

    public long getVatNumber() {
        return vatNumber;
    }

    public void setVatNumber(long vatNumber) {
        this.vatNumber = vatNumber;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<Property> getPropertyList() {
        return propertyList;
    }

    public void setPropertyList(List<Property> propertyList) {
        this.propertyList = propertyList;
    }

    public List<Repair> getRepairList() {
        return repairList;
    }

    public void setRepairList(List<Repair> repairList) {
        this.repairList = repairList;
    }
    
    
}
