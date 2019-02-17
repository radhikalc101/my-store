package org.launchcode.boot.store.repositories;

import org.launchcode.boot.store.models.Item;
import org.launchcode.boot.store.models.StoreInfo;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
@Repository
public interface ItemRepository extends CrudRepository<Item, Integer> {

    List<Item> findByStoreInfo(StoreInfo storeInfo);
}
