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
    public Owner readVatNumber(long vatNumber) {
        Optional<Owner> owner = read().stream()
                .filter(o -> o.getVatNumber() == vatNumber)
                .findFirst();
        return owner.orElse(null);
    }

    @Override
    public Owner readEmail(String email) {
        Optional<Owner> owner = read().stream()
                .filter(o -> o.getEmail().equals(email))
                .findFirst();
        return owner.orElse(null);
    }

    @Override
    public void updateAddress(int ownerId, String address) {
        Owner owner = read(ownerId);
        if (owner != null) {
            owner.setAddress(address);
        }
    }

    @Override
    public void updateEmail(int ownerId, String email) {
        Owner owner = read(ownerId);
        if (owner != null) {
            owner.setEmail(email);
        }
    }

    @Override
    public void updatePassword(int ownerId, String password) {
        Owner owner = read(ownerId);
        if (owner != null) {
            owner.setPassword(password);
        }
    }

}
