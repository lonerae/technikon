package com.technikon.eagency.repository;

import com.technikon.eagency.model.Owner;

public interface OwnerRepository extends Repository<Owner> {

    Owner readVatNumber(long vatNumber);

    Owner readEmail(String email);

    boolean updateAddress(int ownerId, String address);

    boolean updateEmail(int ownerId, String email);

    boolean updatePassword(int ownerId, String password);
}
