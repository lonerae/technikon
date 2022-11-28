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
    public void updateAddress(int ownerId, String address) {
        Owner owner = readById(ownerId);
        owner.setAddress(address);
    }

    @Override
    public void updateEmail(int ownerId, String email) {
        Owner owner = readById(ownerId);
        owner.setEmail(email);
    }

    @Override
    public void updatePassword(int ownerId, String password) {
        Owner owner = readById(ownerId);
        owner.setPassword(password);
    }

}
