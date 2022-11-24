package com.technikon.eagency.model;

import com.technikon.eagency.enums.RepairType;
import com.technikon.eagency.enums.StatusType;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
public class Repair extends PersistentClass {

    private RepairType repairtype;
    private String shortDescription;
    private LocalDateTime dateOfSubmisssion;
    private String descriptionOfWork;
    private LocalDateTime proposedDateOfStart;
    private LocalDateTime proposedDateOfEnd;
    private BigDecimal proposedCost;
    private boolean acceptance;
    private StatusType statusType;
    private LocalDateTime dateOfStart;
    private LocalDateTime dateOfEnd;
    
    @ManyToOne
    private Owner owner;

    @ManyToOne
    private Property property;

    public RepairType getRepairtype() {
        return repairtype;
    }

    public void setRepairtype(RepairType repairtype) {
        this.repairtype = repairtype;
    }

    public String getShortDescription() {
        return shortDescription;
    }

    public void setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
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

    public StatusType getStatusType() {
        return statusType;
    }

    public void setStatusType(StatusType statusType) {
        this.statusType = statusType;
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

    public Owner getOwner() {
        return owner;
    }

    public void setOwner(Owner owner) {
        this.owner = owner;
    }

    public Property getProperty() {
        return property;
    }

    public void setProperty(Property property) {
        this.property = property;
    }
    
    
}
