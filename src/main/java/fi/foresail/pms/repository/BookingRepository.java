package fi.foresail.pms.repository;

import fi.foresail.pms.model.Booking;
import fi.foresail.pms.model.Property;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Repository
public interface BookingRepository extends CrudRepository<Booking, Long> {

	@Query("select b from Booking b join fetch b.room")
	List<Booking> findAllBookings();

	@Query("select b from Booking b join fetch b.room  where b.property=:property")
	List<Booking> findAllBookingsByProperty(@Param("property") Property property);
}
