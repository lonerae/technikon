package com.technikon.eagency.repository.impl;

import com.technikon.eagency.model.Owner;
import com.technikon.eagency.repository.OwnerRepository;
import java.util.Optional;

/**
 *
 * @author panos
 */
public class OwnerRepositoryImpl extends RepositoryImpl<Owner> implements OwnerRepository {

    @Override
    public Optional<Owner> readVatNumber(long vatNumber) {
        Optional<Owner> owner = read().stream()
                .filter(o -> o.getVatNumber() == vatNumber)
                .findFirst();
        if (owner.isPresent()) {
            return owner;
        }
        return Optional.empty();
    }

    @Override
    public Optional<Owner> readEmail(String email) {
        Optional<Owner> owner = read().stream()
                .filter(o -> o.getEmail().equals(email))
                .findFirst();
        if (owner.isPresent()) {
            return owner;
        }
        return Optional.empty();
    }

    @Override
    public void updateAddress(int ownerId, String address) {
        Optional<Owner> owner = read(ownerId);
        if (owner.isPresent()) {
            owner.get().setAddress(address);
        }
    }

    @Override
    public void updateEmail(int ownerId, String email) {
        Optional<Owner> owner = read(ownerId);
        if (owner.isPresent()) {
            owner.get().setEmail(email);
        }
    }

    @Override
    public void updatePassword(int ownerId, String password) {
        Optional<Owner> owner = read(ownerId);
        if (owner.isPresent()) {
            owner.get().setPassword(password);
        }
    }

}
