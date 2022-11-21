package com.technikon.eagency.model;


import com.technikon.eagency.enums.RepairType;
import com.technikon.eagency.enums.StatusType;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Repair extends PersistentClass {

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

    @ManyToOne
    private Owner owner;

    @ManyToOne
    private Property property;

}
//setdateOfSubmission(LocalDate.parse(words[4].trim()))
