package com.technikon.eagency.model;

import com.technikon.eagency.enums.RepairType;
import com.technikon.eagency.enums.StatusType;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 *
 * @author Nick
 */
public class Repair extends PersistentClass {

    private long vatNumberOfOwner;
    private long propertyId;
    private RepairType repairtype;
    private String shortDescription;
    private LocalDate dateOfSubmisssion;
    private String descriptionOfWork;
    private LocalDate proposedDateOfStart;
    private LocalDate proposedDateOfEnd;
    private BigDecimal proposedCost;
    private boolean acceptance;
    private StatusType statustype;
    private LocalDate dateOfStart;
    private LocalDate dateOfEnd;

    public long getVatNumberOfOwner() {
        return vatNumberOfOwner;
    }

    public void setVatNumberOfOwner(long vatNumberOfOwner) {
        this.vatNumberOfOwner = vatNumberOfOwner;
    }

    public long getPropertyId() {
        return propertyId;
    }

    public void setPropertyId(long propertyId) {
        this.propertyId = propertyId;
    }

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

    public LocalDate getSubmissionDate() {
        return dateOfSubmisssion;
    }

    public void setSubmissionDate(LocalDate dateOfSubmisssion) {
        this.dateOfSubmisssion = dateOfSubmisssion;
    }

    public String getDescription() {
        return descriptionOfWork;
    }

    public void setDescription(String descriptionOfWork) {
        this.descriptionOfWork = descriptionOfWork;
    }

    public LocalDate getProposedStartDate() {
        return proposedDateOfStart;
    }

    public void setProposedStartDate(LocalDate proposedDateOfStart) {
        this.proposedDateOfStart = proposedDateOfStart;
    }

    public LocalDate getProposedEndDate() {
        return proposedDateOfEnd;
    }

    public void setProposedEndDate(LocalDate proposedDateOfEnd) {
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
        return statustype;
    }

    public void setStatusType(StatusType statustype) {
        this.statustype = statustype;
    }

    public LocalDate getActualStartDate() {
        return dateOfStart;
    }

    public void setActualStartDate(LocalDate dateOfStart) {
        this.dateOfStart = dateOfStart;
    }

    public LocalDate getActualEndDate() {
        return dateOfEnd;
    }

    public void setActualEndDate(LocalDate dateOfEnd) {
        this.dateOfEnd = dateOfEnd;
    }

}
