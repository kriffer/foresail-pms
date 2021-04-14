package fi.foresail.pms.repository;

import fi.foresail.pms.model.Property;
import fi.foresail.pms.model.Room;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface RoomRepository extends CrudRepository <Room, Long> {

	@Query("select r from Room r join fetch r.property  where r.property =:property ")
	List<Room> findAllByProperty(@Param("property") Property property);


	@Query("select r from Room r where r.id=:id")
	Room findRoomById(Long id);


}
