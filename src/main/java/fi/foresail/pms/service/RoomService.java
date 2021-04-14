package fi.foresail.pms.service;

import fi.foresail.pms.model.Property;
import fi.foresail.pms.model.Room;
import fi.foresail.pms.repository.RoomRepository;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class RoomService {

    @Autowired
    private RoomRepository roomRepository;

    @Autowired
    private UnitService unitService;

    public List<Room> findAllByProperty(Property property) {
        return roomRepository.findAllByProperty(property);
    }

    public Room create(Property property) {
        Room room = new Room();
        room.setCreated(new Timestamp(new Date().getTime()));
        int roomCount = roomRepository.findAllByProperty(property).size();
        room.setProperty(property);

        room.setName("Room " + (roomCount + 1));
        room.setMaxGuests(2);
        room.setQuantity(1);
        room.setPrice(BigDecimal.valueOf(0.00));
        Room newRoom = roomRepository.save(room);

        unitService.create(newRoom);
        return roomRepository.save(room);
    }

    public Room update(Long id, Room room) {
        return roomRepository.findById(id).map(rm -> {
            rm.setProperty(room.getProperty());
            rm.setName(room.getName());
            rm.setMaxGuests(room.getMaxGuests());
            rm.setPrice(room.getPrice());
            rm.setRate(room.getRate());
            rm.setQuantity(room.getQuantity());
            rm.setUpdated(new Timestamp(new Date().getTime()));
            return roomRepository.save(rm);
        }).orElseGet(() -> {
            room.setId(id);
            return roomRepository.save(room);
        });
    }

    public void deleteById(Long id) {
        Room room = roomRepository.findRoomById(id);
        roomRepository.delete(room);
    }

    public Room findById(Long roomId) {
        return roomRepository.findRoomById(roomId); //To change body of generated methods, choose Tools | Templates.
    }

}
