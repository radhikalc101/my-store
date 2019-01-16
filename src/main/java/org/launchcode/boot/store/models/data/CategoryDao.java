package org.launchcode.boot.store.models.data;

import org.launchcode.boot.store.models.forms.Category;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
@Transactional
public interface CategoryDao extends CrudRepository<Category, Integer> {
}
