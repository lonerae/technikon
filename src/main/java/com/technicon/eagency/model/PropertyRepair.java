package com.technicon.eagency.model;

import java.math.BigDecimal;
import java.time.LocalDate;

public class PropertyRepair {

    private long OwnerRepairId;
    private long propertyRepairId;
    private String summaryDesc;
    private LocalDate submissionRepair;
    private String repairDesc;
    private LocalDate proposedStartDate;
    private LocalDate proposedEndDate;
    private BigDecimal cost;
    private boolean startRepair;
    private LocalDate actualStartDate;
    private LocalDate actualEndDate;

    public PropertyRepair() {
    }

    public PropertyRepair(long OwnerRepairId, long propertyRepairId, String summaryDesc, LocalDate submissionRepair, String repairDesc, LocalDate proposedStartDate, LocalDate proposedEndDate, BigDecimal cost, boolean startRepair, LocalDate actualStartDate, LocalDate actualEndDate) {
        this.OwnerRepairId = OwnerRepairId;
        this.propertyRepairId = propertyRepairId;
        this.summaryDesc = summaryDesc;
        this.submissionRepair = submissionRepair;
        this.repairDesc = repairDesc;
        this.proposedStartDate = proposedStartDate;
        this.proposedEndDate = proposedEndDate;
        this.cost = cost;
        this.startRepair = startRepair;
        this.actualStartDate = actualStartDate;
        this.actualEndDate = actualEndDate;
    }

    public long getOwnerRepairId() {
        return OwnerRepairId;
    }

    public void setOwnerRepairId(long OwnerRepairId) {
        this.OwnerRepairId = OwnerRepairId;
    }

    public long getPropertyRepairId() {
        return propertyRepairId;
    }

    public void setPropertyRepairId(long propertyRepairId) {
        this.propertyRepairId = propertyRepairId;
    }

    public String getSummaryDesc() {
        return summaryDesc;
    }

    public void setSummaryDesc(String summaryDesc) {
        this.summaryDesc = summaryDesc;
    }

    public LocalDate getSubmissionRepair() {
        return submissionRepair;
    }

    public void setSubmissionRepair(LocalDate submissionRepair) {
        this.submissionRepair = submissionRepair;
    }

    public String getRepairDesc() {
        return repairDesc;
    }

    public void setRepairDesc(String repairDesc) {
        this.repairDesc = repairDesc;
    }

    public LocalDate getProposedStartDate() {
        return proposedStartDate;
    }

    public void setProposedStartDate(LocalDate proposedStartDate) {
        this.proposedStartDate = proposedStartDate;
    }

    public LocalDate getProposedEndDate() {
        return proposedEndDate;
    }

    public void setProposedEndDate(LocalDate proposedEndDate) {
        this.proposedEndDate = proposedEndDate;
    }

    public BigDecimal getCost() {
        return cost;
    }

    public void setCost(BigDecimal cost) {
        this.cost = cost;
    }

    public boolean isStartRepair() {
        return startRepair;
    }

    public void setStartRepair(boolean startRepair) {
        this.startRepair = startRepair;
    }

    public LocalDate getActualStartDate() {
        return actualStartDate;
    }

    public void setActualStartDate(LocalDate actualStartDate) {
        this.actualStartDate = actualStartDate;
    }

    public LocalDate getActualEndDate() {
        return actualEndDate;
    }

    public void setActualEndDate(LocalDate actualEndDate) {
        this.actualEndDate = actualEndDate;
    }

    @Override
    public String toString() {
        return "PropertyRepair{" + "OwnerRepairId=" + OwnerRepairId + ", propertyRepairId=" + propertyRepairId
                + ", summaryDesc=" + summaryDesc + ", submissionRepair=" + submissionRepair + ", repairDesc=" + repairDesc
                + ", proposedStartDate=" + proposedStartDate + ", proposedEndDate=" + proposedEndDate + ", cost=" + cost
                + ", startRepair=" + startRepair + ", actualStartDate=" + actualStartDate + ", actualEndDate=" + actualEndDate + '}';
    }

}
