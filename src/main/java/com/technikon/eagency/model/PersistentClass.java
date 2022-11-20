package com.technikon.eagency.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class PersistentClass {

    private int id;
    private boolean isActive;
}
