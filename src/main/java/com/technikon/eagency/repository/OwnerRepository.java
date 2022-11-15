package com.technikon.eagency.repository;

import com.technikon.eagency.model.Owner;
import java.util.Optional;

public interface OwnerRepository extends Repository<Owner> {

    Optional<Owner> readVatNumber(long vatNumber);
    
    Optional<Owner> readEmail(String email);
    
    void updateAddress(int ownerId, String address);

    void updateEmail(int ownerId, String email);

    void updatePassword(int ownerId, String password);

}
