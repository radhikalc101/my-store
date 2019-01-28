package org.launchcode.boot.store.models.data;

import org.launchcode.boot.store.models.forms.DBFile;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
@Transactional
public interface DBFileDao extends CrudRepository<DBFile, Integer> {
}
