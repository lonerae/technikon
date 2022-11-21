package com.technikon.eagency.model;


import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Owner extends PersistentClass {

    private long vatNumber;
    private String username;
    private String password;
    private String name;
    private String surname;
    private String address;
    private String phoneNumber;
    private String email;

    @OneToMany(mappedBy = "owner")
    private List<Property> propertyList;

    @OneToMany(mappedBy = "owner")
    private List<Repair> repairList;
}
