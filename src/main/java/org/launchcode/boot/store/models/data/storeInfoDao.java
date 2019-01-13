package org.launchcode.boot.store.models.data;

import org.launchcode.boot.store.models.forms.OwnerAccountInfo;
import org.launchcode.boot.store.models.forms.StoreInfo;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Transactional
@Repository
public interface storeInfoDao extends CrudRepository<StoreInfo, Integer> {
    StoreInfo findByOwnerAccountInfo(OwnerAccountInfo ownerAccountInfo);//here we need to use only findBy with the field name followed by to make new method
}                                                                       // in this the passing parameters also used as same field names in StoreInfo class
