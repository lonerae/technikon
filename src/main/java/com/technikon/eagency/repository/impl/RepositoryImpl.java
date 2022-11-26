package com.technikon.eagency.repository.impl;

import com.technikon.eagency.model.PersistentClass;
import com.technikon.eagency.repository.Repository;
import java.util.ArrayList;
import java.util.List;

public abstract class RepositoryImpl<T extends PersistentClass> implements Repository<T> {

    private final List<T> repository;
    private int index;

    public RepositoryImpl() {
        this.repository = new ArrayList<>();
    }

    @Override
    public int create(T t) {
        t.setId(index++);
        repository.add(t);
        return t.getId();
    }

    @Override
    public T readById(int id) {
        return repository.stream()
                .filter(t -> t.getId() == id)
                .findFirst().orElse(null);
    }

    @Override
    public List<T> readAll() {
        return new ArrayList<>(repository);
    }

    @Override
    public boolean delete(int id) {
        T t = readById(id);
        if (t != null) {
            repository.remove(t);
            return true;
        }
        return false;
    }

    @Override
    public boolean safeDelete(int id) {
        T t = readById(id);
        if (t != null) {
            t.setActive(false);
            return true;
        }
        return false;
    }

}
