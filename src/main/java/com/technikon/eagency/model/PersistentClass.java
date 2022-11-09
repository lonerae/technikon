/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.technikon.eagency.model;

/**
 *
 * @author Nick
 */
public abstract class PersistentClass {

    private long OwnerId;
    private long propertyId;

    public long getOwnerId() {
        return OwnerId;
    }

    public void setOwnerId(long OwnerId) {
        this.OwnerId = OwnerId;
    }

    public long getPropertyId() {
        return propertyId;
    }

    public void setPropertyId(long propertyId) {
        this.propertyId = propertyId;
    }
}
