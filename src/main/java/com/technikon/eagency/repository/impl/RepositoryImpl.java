package com.technikon.eagency.repository.impl;

import com.technikon.eagency.model.PersistentClass;
import com.technikon.eagency.repository.Repository;
import java.util.ArrayList;
import java.util.List;

public abstract class RepositoryImpl<T extends PersistentClass> implements Repository<T> {

    private List<T> list = new ArrayList<>();
    private int index;

    public RepositoryImpl() {
        this.list = new ArrayList<>();
    }
    
    @Override
    public int create(T t){
        t.setId(index++);
        list.add(t);
        return t.getId();
    }

    @Override
    public T read(int id) {
        for (T t : list) {
            if (t.getId() == id) {
                return t;
            }
        }
        return null;
    }

    @Override
    public List<T> read() {
        return list;
    }

    @Override
    public boolean delete(int id) {
        T t = read(id);
        if (t != null) {
            list.remove(t);
            return true;
        }
        return false;
    }

    @Override
    public boolean safeDelete(int id) {
        T t = read(id);
        if (t != null) {
            t.setIsActive(false);
            return true;
        }
        return false;
    }
    
}
