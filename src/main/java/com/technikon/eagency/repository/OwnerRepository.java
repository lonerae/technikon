package com.technikon.eagency.repository;

import com.technikon.eagency.model.Owner;

public interface OwnerRepository extends Repository<Owner> {

    Owner readVatNumber(long vatNumber);
    
    Owner readEmail(String email);
    
    void updateAddress(int ownerId, String address);

    void updateEmail(int ownerId, String email);

    void updatePassword(int ownerId, String password);

}
