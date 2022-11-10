package com.technikon.eagency.repository.impl;

import com.technikon.eagency.model.Owner;
import com.technikon.eagency.repository.OwnerRepository;

/**
 *
 * @author panos
 */
public class OwnerRepositoryImpl extends RepositoryImpl<Owner> implements OwnerRepository {

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
