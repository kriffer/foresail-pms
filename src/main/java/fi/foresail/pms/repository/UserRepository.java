package fi.foresail.pms.repository;

import fi.foresail.pms.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional(readOnly = true)
@Repository
public interface UserRepository extends JpaRepository<User, Long> {


	@Query("SELECT u FROM User u WHERE u.email = LOWER(:email)")
	User findByEmailIgnoreCase(@Param("email") String email);

	@Query("SELECT u FROM User u WHERE u.name = LOWER(:name)")
	User findByName(@Param("name") String name);

	@Override
	@RestResource(rel = "users", path = "users")
	List<User> findAll();
}
