package org.launchcode.boot.store.models.data;

import org.launchcode.boot.store.models.forms.Brand;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Transactional
@Repository
public interface BrandDao extends CrudRepository<Brand, Integer>{
}
