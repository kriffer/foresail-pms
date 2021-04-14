package fi.foresail.pms.repository;

import fi.foresail.pms.model.Account;
import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Repository
public interface AccountRepository extends CrudRepository<Account, Long> {
	
        @Override
        @Query("select a from Account a join fetch a.properties")
	List<Account> findAll();

}
