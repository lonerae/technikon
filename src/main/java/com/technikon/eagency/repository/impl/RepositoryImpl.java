package com.technikon.eagency.repository.impl;

import com.technikon.eagency.model.PersistentClass;
import com.technikon.eagency.repository.Repository;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
    public Optional<T> read(int id) {
        Optional<T> t = read()
                .stream()
                .filter(obj -> obj.getId() == id)
                .findFirst();
        if (t.isPresent()) {
            return t;
        }
        return Optional.empty();
    }

    @Override
    public List<T> read() {
        return list;
    }

    @Override
    public boolean delete(int id) {
        Optional<T> t = read(id);
        if (t.isPresent()) {
            list.remove(t.get());
            return true;
        }
        return false;
    }

    @Override
    public boolean safeDelete(int id) {
        Optional<T> t = read(id);
        if (t.isPresent()) {
            t.get().setIsActive(false);
            return true;
        }
        return false;
    }
    
}
