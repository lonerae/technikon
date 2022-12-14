package com.technikon.eagency.model;

import com.technikon.eagency.enums.RepairType;
import com.technikon.eagency.enums.StatusType;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "Repair")
public class Repair extends PersistentClass {

    @NotNull
    private RepairType repairtype;

    @Size(max = 100)
    private String shortDescription;

    @NotNull
    private LocalDate dateOfSubmission;

    private String descriptionOfWork;
    private LocalDate proposedDateOfStart;
    private LocalDate proposedDateOfEnd;
    private BigDecimal proposedCost;
    private boolean acceptance;
    private StatusType statusType = StatusType.PENDING;
    private LocalDate dateOfStart;
    private LocalDate dateOfEnd;

    @ManyToOne
    @NotNull
    @JoinColumn(name = "ownerId")
    private Owner owner;

    @ManyToOne
    @NotNull
    @JoinColumn(name = "propertyId")
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

    public LocalDate getDateOfSubmission() {
        return dateOfSubmission;
    }

    public void setDateOfSubmission(LocalDate dateOfSubmission) {
        this.dateOfSubmission = dateOfSubmission;
    }

    public String getDescriptionOfWork() {
        return descriptionOfWork;
    }

    public void setDescriptionOfWork(String descriptionOfWork) {
        this.descriptionOfWork = descriptionOfWork;
    }

    public LocalDate getProposedDateOfStart() {
        return proposedDateOfStart;
    }

    public void setProposedDateOfStart(LocalDate proposedDateOfStart) {
        this.proposedDateOfStart = proposedDateOfStart;
    }

    public LocalDate getProposedDateOfEnd() {
        return proposedDateOfEnd;
    }

    public void setProposedDateOfEnd(LocalDate proposedDateOfEnd) {
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

    public LocalDate getDateOfStart() {
        return dateOfStart;
    }

    public void setDateOfStart(LocalDate dateOfStart) {
        this.dateOfStart = dateOfStart;
    }

    public LocalDate getDateOfEnd() {
        return dateOfEnd;
    }

    public void setDateOfEnd(LocalDate dateOfEnd) {
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
