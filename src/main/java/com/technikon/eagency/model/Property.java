package com.technikon.eagency.model;

import com.technikon.eagency.enums.PropertyType;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Property extends PersistentClass {

    private long propertyId;
    private String address;
    private int yearOfConstruction;
    private PropertyType propertyType;
    private long vatNumberOfOwner;
}
