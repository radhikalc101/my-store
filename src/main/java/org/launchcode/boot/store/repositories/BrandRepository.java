package org.launchcode.boot.store.repositories;

import org.launchcode.boot.store.models.Brand;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Transactional
@Repository
public interface BrandRepository extends CrudRepository<Brand, Integer>{
}
