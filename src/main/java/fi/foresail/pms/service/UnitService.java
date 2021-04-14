package fi.foresail.pms.service;

import fi.foresail.pms.model.Room;
import fi.foresail.pms.model.Unit;
import fi.foresail.pms.repository.UnitRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service
@Slf4j
public class UnitService {

    @Autowired
    private UnitRepository unitRepository;

    public Set<Unit> getUnits(Room room) {
        Set<Unit> units = room.getUnits();
        return units;
    }

    public Unit create(Room room) {
        Unit unit = new Unit();
        unit.setId(1);
        unit.setName("Unit 1");
        unit.setRoom(room);
        unitRepository.save(unit);
        return unit;
    }

    public Unit update(Integer id, Unit unit) {
        return unitRepository.findById(id, unit.getRoom()).map(rm -> {
            rm.setRoom(unit.getRoom());
            rm.setName(unit.getName());
            rm.setInfo(unit.getInfo());

            return unitRepository.save(rm);
        }).orElseGet(() -> {
            unit.setId(id);
            return unitRepository.save(unit);
        });
    }

    @Transactional
    public List<Unit> updateUnitsFromString(String unitstring, Room room) {
        List units = new ArrayList();
        log.info("==unitstring: " + unitstring);

        deleteByRoom(room);

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

                update(unit.getId(), unit);
                units.add(unit);
                id++;
            }
        }

        return units;
    }

    void deleteByRoom(Room room) {
        unitRepository.deleteByRoom(room);
    }

    public List<Unit> findUnitsByRoomId(Long id) {
        return unitRepository.findByRoomId(id);

    }

}
