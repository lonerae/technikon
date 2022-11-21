package com.technikon.eagency.repository.impl;


import com.technikon.eagency.model.Owner;
import com.technikon.eagency.repository.OwnerRepository;
import com.technikon.eagency.util.JPAUtil;
import java.util.Optional;

public class OwnerRepositoryImpl extends RepositoryImpl<Owner> implements OwnerRepository {

    private EntityManager entityManager = JPAUtil.getEntityManager();

    @Override
    public Optional<Owner> readVatNumber(long vatNumber) {
        Optional<Owner> owner = entityManager.find(Owner.class, vatNumber);
        if (owner.isPresent()) {
            return owner;
        }
        return Optional.empty();
    }

    @Override
    public Optional<Owner> readEmail(String email) {
        Optional<Owner> owner = entityManager.find(Owner.class, email);
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
