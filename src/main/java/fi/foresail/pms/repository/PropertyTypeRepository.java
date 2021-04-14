package fi.foresail.pms.repository;

import fi.foresail.pms.model.PropertyType;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PropertyTypeRepository extends CrudRepository<PropertyType, Long> {

}
