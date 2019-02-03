package org.launchcode.boot.store.models.data;

import org.launchcode.boot.store.models.forms.Item;
import org.launchcode.boot.store.models.forms.StoreInfo;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
@Repository
public interface ItemDao extends CrudRepository<Item, Integer> {

    List<Item> findByStoreInfo(StoreInfo storeInfo);
}
