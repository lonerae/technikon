package com.technikon.eagency.repository.impl;

import com.technikon.eagency.model.Owner;
import com.technikon.eagency.repository.OwnerRepository;

public class OwnerRepositoryImpl extends RepositoryImpl<Owner> implements OwnerRepository {

    @Override
    public Owner readVatNumber(long vatNumber) {
        return readAll().stream()
                .filter(owner -> owner.getVatNumber() == vatNumber)
                .findFirst().orElse(null);
    }

    @Override
    public Owner readEmail(String email) {
        return readAll().stream()
                .filter(owner -> owner.getEmail().equals(email))
                .findFirst().orElse(null);
    }

    @Override
    public boolean updateAddress(int ownerId, String address) {
        Owner owner = readById(ownerId);
        if (owner != null) {
            owner.setAddress(address);
            return true;
        }
        return false;
    }

    @Override
    public boolean updateEmail(int ownerId, String email) {
        Owner owner = readById(ownerId);
        if (owner != null) {
            owner.setEmail(email);
            return true;
        }
        return false;
    }

    @Override
    public boolean updatePassword(int ownerId, String password) {
        Owner owner = readById(ownerId);
        if (owner != null) {
            owner.setPassword(password);
            return true;
        }
        return false;
    }
    
}
