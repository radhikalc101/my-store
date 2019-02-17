package org.launchcode.boot.store.repositories;

import org.launchcode.boot.store.models.Address;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
@Transactional                  // CRUD refers Create, Read, Update, Delete
public interface AddressRepository extends CrudRepository<Address, Integer> {
}
