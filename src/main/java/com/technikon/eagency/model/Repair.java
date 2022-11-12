package com.technikon.eagency.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class Repair extends PersistentClass {

    private long ownerId;
    private long propertyId;
    private short shortDescription;
    private int vatNumberOfOwner;
    private LocalDateTime dateOfSubmisssion;
    private String descriptionOfWork;
    private LocalDateTime proposedDateOfStart;
    private LocalDateTime proposedDateOfEnd;
    private BigDecimal proposedCost;
    private boolean acceptance;
    private LocalDateTime dateOfStart;
    private LocalDateTime dateOfEnd;

    public long getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(long ownerId) {
        this.ownerId = ownerId;
    }

    public long getPropertyId() {
        return propertyId;
    }

    public void setPropertyId(long propertyId) {
        this.propertyId = propertyId;
    }

    public short getShortDescription() {
        return shortDescription;
    }

    public void setShortDescription(short shortDescription) {
        this.shortDescription = shortDescription;
    }

    public int getVatNumberOfOwner() {
        return vatNumberOfOwner;
    }

    public void setVatNumberOfOwner(int vatNumberOfOwner) {
        this.vatNumberOfOwner = vatNumberOfOwner;
    }

    public LocalDateTime getDateOfSubmisssion() {
        return dateOfSubmisssion;
    }

    public void setDateOfSubmisssion(LocalDateTime dateOfSubmisssion) {
        this.dateOfSubmisssion = dateOfSubmisssion;
    }

    public String getDescriptionOfWork() {
        return descriptionOfWork;
    }

    public void setDescriptionOfWork(String descriptionOfWork) {
        this.descriptionOfWork = descriptionOfWork;
    }

    public LocalDateTime getProposedDateOfStart() {
        return proposedDateOfStart;
    }

    public void setProposedDateOfStart(LocalDateTime proposedDateOfStart) {
        this.proposedDateOfStart = proposedDateOfStart;
    }

    public LocalDateTime getProposedDateOfEnd() {
        return proposedDateOfEnd;
    }

    public void setProposedDateOfEnd(LocalDateTime proposedDateOfEnd) {
        this.proposedDateOfEnd = proposedDateOfEnd;
    }

    public BigDecimal getProposedCost() {
        return proposedCost;
    }

    public void setProposedCost(BigDecimal proposedCost) {
        this.proposedCost = proposedCost;
    }

    public boolean isAcceptance() {
        return acceptance;
    }

    public void setAcceptance(boolean acceptance) {
        this.acceptance = acceptance;
    }

    public LocalDateTime getDateOfStart() {
        return dateOfStart;
    }

    public void setDateOfStart(LocalDateTime dateOfStart) {
        this.dateOfStart = dateOfStart;
    }

    public LocalDateTime getDateOfEnd() {
        return dateOfEnd;
    }

    public void setDateOfEnd(LocalDateTime dateOfEnd) {
        this.dateOfEnd = dateOfEnd;
    }

}
