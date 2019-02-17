package org.launchcode.boot.store.repositories;

import org.launchcode.boot.store.models.OwnerAccountInfo;
import org.launchcode.boot.store.models.StoreInfo;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Transactional
@Repository
public interface StoreInfoRepository extends CrudRepository<StoreInfo, Integer> {
    StoreInfo findByOwnerAccountInfo(OwnerAccountInfo ownerAccountInfo);//here we need to use only findBy with the field name followed by to make new method
}                                                                       // in this the passing parameters also used as same field names in StoreInfo class
