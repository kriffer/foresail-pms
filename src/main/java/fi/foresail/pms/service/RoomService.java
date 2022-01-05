/*
 * MIT License
 *
 * Copyright (c) 2021 Anton Kravets (kriffer.io)
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
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

    public Room update(Integer id, Room room) {
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

    public void deleteById(Integer id) {
        Room room = roomRepository.findRoomById(id);
        roomRepository.delete(room);
    }

    public Room findById(Integer roomId) {
        return roomRepository.findRoomById(roomId); //To change body of generated methods, choose Tools | Templates.
    }

}
