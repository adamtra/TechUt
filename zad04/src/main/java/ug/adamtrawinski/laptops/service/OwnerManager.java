package ug.adamtrawinski.laptops.service;

import ug.adamtrawinski.laptops.domain.Owner;

import java.util.List;

public interface OwnerManager {
    void addOwner(Owner owner);
    Owner findOwnerById(long id);
    List<Owner> getAllOwners();
    void deleteOwner(long id);
    void updateOwner(Owner owner);
    void clearTable();
}
