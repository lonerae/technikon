package com.technikon.eagency.model;

import com.technikon.eagency.enums.PropertyType;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Property extends PersistentClass {

    private long propertyId;
    private String address;
    private int yearOfConstruction;
    private PropertyType propertyType;

    @ManyToOne
    private Owner owner;
    
    @OneToMany(mappedBy = "property")
    private List<Repair> repairList;
}
