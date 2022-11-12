package com.technikon.eagency.repository;

import com.technikon.eagency.model.PersistentClass;
import java.util.List;

public interface Repository<T extends PersistentClass> {

    int create(T t);

    T read(int id);

    List<T> read();

    boolean delete(int id);

    //maybe implement with use of isActive flag on Persistent?
    boolean safeDelete(int id);
    
}
