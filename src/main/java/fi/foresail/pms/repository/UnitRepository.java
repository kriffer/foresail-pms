package fi.foresail.pms.repository;

import fi.foresail.pms.model.Room;
import fi.foresail.pms.model.Unit;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface UnitRepository extends CrudRepository<Unit, Integer> {

    @Query("select u from Unit u  where u.id =:id and u.room=:room")
    Optional<Unit> findById(@Param("id") Integer id, @Param("room") Room room);

    @Modifying
    @Query("delete from Unit u where u.room=:room")
    void deleteByRoom(@Param("room") Room room);

    @Query("select u from Unit u where u.room.id=:id")
    public List<Unit> findByRoomId(@Param("id") Long id);
}
