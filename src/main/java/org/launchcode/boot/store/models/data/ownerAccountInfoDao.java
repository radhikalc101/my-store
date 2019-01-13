package org.launchcode.boot.store.models.data;

import org.launchcode.boot.store.models.forms.OwnerAccountInfo;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
@Transactional
public interface ownerAccountInfoDao extends CrudRepository<OwnerAccountInfo, Integer> {
    OwnerAccountInfo findByEmail(String email);// here OwnerAccountInfo class has the same name email field so we have to use
                                                // same data type here as field data type
}
