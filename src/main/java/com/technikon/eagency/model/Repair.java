package com.technikon.eagency.model;

import com.technikon.eagency.enums.RepairType;
import com.technikon.eagency.enums.StatusType;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
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
    private StatusType statusType;
    private LocalDateTime dateOfStart;
    private LocalDateTime dateOfEnd;
}
