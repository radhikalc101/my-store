package org.launchcode.boot.store.models.data;

import org.launchcode.boot.store.models.forms.Address;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
@Transactional                  // CRUD refers Create, Read, Update, Delete
public interface addressDao extends CrudRepository<Address, Integer> {
}
