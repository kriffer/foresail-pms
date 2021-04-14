package fi.foresail.pms.repository;

import fi.foresail.pms.model.Account;
import fi.foresail.pms.model.Property;
import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface PropertyRepository extends CrudRepository<Property, Long> {

	@Query("select p from Property p")
	List<Property> findAllProperties();

	@Query("select p from Property p join fetch p.account join fetch p.propertyType where p.account =:account")
	List<Property> findAllPropertiesByAccount(@Param("account") Account account);


}
