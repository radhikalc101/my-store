package org.launchcode.boot.store.repositories;

import org.launchcode.boot.store.models.ImageFile;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
@Transactional
public interface ImageRepository extends CrudRepository<ImageFile, Integer> {
}
