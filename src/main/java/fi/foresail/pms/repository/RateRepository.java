package fi.foresail.pms.repository;

import fi.foresail.pms.model.Property;
import fi.foresail.pms.model.Rate;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Repository
public interface RateRepository extends CrudRepository<Rate, Long> {

	@Query("select r from Rate r where r.property =:property ")
	List<Rate> findAllByProperty(@Param("property") Property property);

}
