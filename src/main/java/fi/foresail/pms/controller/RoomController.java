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

package fi.foresail.pms.controller;

import fi.foresail.pms.model.Account;
import fi.foresail.pms.model.Property;
import fi.foresail.pms.model.Room;
import java.security.Principal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.validation.Valid;

import fi.foresail.pms.service.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@Slf4j
public class RoomController {

    @Autowired
    private PropertyService propertyService;

    @Autowired
    private RoomService roomService;

    @Autowired
    private AccountContext accountContext;

    @Autowired
    private UnitService unitService;

    @Autowired
    private UserServiceImpl userService;

    @GetMapping("/rooms")
    public String getRooms(Principal principal, Model model) {
        Account account = userService.getUserByName(principal.getName()).getAccount();
        List<Property> properties = propertyService.findAllByAccount(account);
        
        Map<Property,List<Room>> map = new HashMap<>();
//        Map<Room, List<Unit>> roomMap = new HashMap<>();
 
        
        properties.forEach(p -> {
           
           
            map.put(p, roomService.findAllByProperty(p));
        });

        
        model.addAttribute("properties", map);
        return "rooms";
    }

  

    @GetMapping("/rooms/add")
    public String addRoom(@RequestParam(required = false) String propertyId) {
        Property property = propertyService.findById(Integer.valueOf(propertyId));
        roomService.create(property);
        return "redirect:/rooms?success";

    }

    @PostMapping("/rooms/{id}/update")
    public String update(@RequestParam(required = false) Integer propertyId,
            @RequestParam(required = false) String unitstring, @PathVariable("id") Integer id, @Valid Room room,
            BindingResult result) {
        Property property = propertyService.findById(propertyId);
        room.setProperty(property);

        if (result.hasErrors()) {
            return "rooms?error";
        }
        Room updatedRoom = roomService.update(id, room);
        unitService.updateUnitsFromString(unitstring, updatedRoom);
        return "redirect:/rooms?success";
    }

    @GetMapping("/rooms/{id}/delete")
    public String delete(@PathVariable("id") Integer id) {
        try {
            roomService.deleteById(id);
            return "redirect:/rooms?success";
        } catch (Exception e) {
            return "redirect:/rooms?error";
        }

    }
}
