package fi.foresail.pms.repository;

import fi.foresail.pms.model.Role;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional(readOnly = true)
@Component
@Repository
public interface RoleRepository extends CrudRepository<Role, Long> {

	@Query("SELECT r FROM Role r")
	List<Role> findAllRoles();

	@Query("SELECT r FROM Role r WHERE r.name = :name")
	Role findByName(@Param("name") String name);
}
