package com.technikon.eagency.model;

import com.technikon.eagency.enums.RepairType;
import com.technikon.eagency.enums.StatusType;
import java.math.BigDecimal;
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
    private LocalDateTime dateOfSubmisssion;
    private String descriptionOfWork;
    private LocalDateTime proposedDateOfStart;
    private LocalDateTime proposedDateOfEnd;
    private BigDecimal proposedCost;
    private boolean acceptance;
    private StatusType statustype;
    private LocalDateTime dateOfStart;
    private LocalDateTime dateOfEnd;

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

    public LocalDateTime getSubmissionDate() {
        return dateOfSubmisssion;
    }

    public void setSubmissionDate(LocalDateTime dateOfSubmisssion) {
        this.dateOfSubmisssion = dateOfSubmisssion;
    }

    public String getDescription() {
        return descriptionOfWork;
    }

    public void setDescription(String descriptionOfWork) {
        this.descriptionOfWork = descriptionOfWork;
    }

    public LocalDateTime getProposedStartDate() {
        return proposedDateOfStart;
    }

    public void setProposedStartDate(LocalDateTime proposedDateOfStart) {
        this.proposedDateOfStart = proposedDateOfStart;
    }

    public LocalDateTime getProposedEndDate() {
        return proposedDateOfEnd;
    }

    public void setProposedEndDate(LocalDateTime proposedDateOfEnd) {
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

    public LocalDateTime getActualStartDate() {
        return dateOfStart;
    }

    public void setActualStartDate(LocalDateTime dateOfStart) {
        this.dateOfStart = dateOfStart;
    }

    public LocalDateTime getActualEndDate() {
        return dateOfEnd;
    }

    public void setActualEndDate(LocalDateTime dateOfEnd) {
        this.dateOfEnd = dateOfEnd;
    }

}
