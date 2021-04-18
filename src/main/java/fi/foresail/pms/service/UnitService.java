/*
 * MIT License
 *
 * Copyright (c) 2021 Foresail Consulting (www.foresail.fi)
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

import fi.foresail.pms.model.Room;
import fi.foresail.pms.model.Unit;

import java.util.ArrayList;
import java.util.List;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service
@Slf4j
public class UnitService {



//    public Set<Unit> getUnits(Room room) {
//        Set<Unit> units = room.getUnits();
//        return units;
//    }

    public Unit create(Room room) {
        Unit unit = new Unit();
        unit.setId(1);
        unit.setName("Unit 1");
        unit.setRoom(room);

        return unit;
    }

//    public Unit update(Integer id, Unit unit) {
//        return unitRepository.findById(id, unit.getRoom()).map(rm -> {
//            rm.setRoom(unit.getRoom());
//            rm.setName(unit.getName());
//            rm.setInfo(unit.getInfo());
//
//            return unitRepository.save(rm);
//        }).orElseGet(() -> {
//            unit.setId(id);
//            return unitRepository.save(unit);
//        });
//    }

    @Transactional
    public List<Unit> updateUnitsFromString(String unitstring, Room room) {
        List units = new ArrayList();
        log.info("==unitstring: " + unitstring);

//        deleteByRoom(room);

        if (!unitstring.isEmpty()) {
            String[] unts = unitstring.split("\n");
            int id = 1;
            for (String u : unts) {
                Unit unit = new Unit();
                unit.setId(id);
                log.info("==unit: " + u);
                String[] split2 = u.split("\\|");

                unit.setName(split2[0].replaceAll("\r", "").replaceAll("\n", ""));
                if (split2.length == 2) {
                    unit.setInfo(split2[1].replaceAll("\r", "").replaceAll("\n", ""));
                }
                unit.setRoom(room);

//                update(unit.getId(), unit);
                units.add(unit);
                id++;
            }
        }

        return units;
    }

//    void deleteByRoom(Room room) {
//        unitRepository.deleteByRoom(room);
//    }
//
//    public List<Unit> findUnitsByRoomId(Long id) {
//        return unitRepository.findByRoomId(id);
//
//    }

}
