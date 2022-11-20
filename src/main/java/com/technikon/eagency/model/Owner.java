package com.technikon.eagency.model;

import lombok.Getter;
import lombok.Setter;

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
}
