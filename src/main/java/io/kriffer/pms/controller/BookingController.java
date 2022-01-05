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

package io.kriffer.pms.controller;

import io.kriffer.pms.model.Account;
import io.kriffer.pms.model.Booking;
import io.kriffer.pms.model.Property;
import io.kriffer.pms.service.AccountContext;
import io.kriffer.pms.service.AccountService;
import io.kriffer.pms.service.BookingService;
import io.kriffer.pms.service.PropertyService;
import io.kriffer.pms.service.RoomService;
import io.kriffer.pms.service.UserServiceImpl;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.validation.Valid;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Slf4j
@Controller
public class BookingController {

    @Autowired
    private PropertyService propertyService;
    
    
    @Autowired
    private RoomService roomService;
    
    @Autowired
    private BookingService bookingService;
    @Autowired
    private UserServiceImpl userService;

    @Autowired
    private AccountService accountService;

    @Autowired
    private AccountContext accountContext;

    @GetMapping("/bookings")
    public String getBookings(Model model) {
        Account account = accountContext.getAccount();
        List<Property> properties = propertyService.findAllByAccount(account);
        Map<Property, List<Booking>> map = new HashMap<>();
        properties.forEach(p -> {
            map.put(p, bookingService.findAllByProperty(p));
        });
        model.addAttribute("properties", map);
        return "bookings";
    }

    @RequestMapping(value = "/bookings/add", method = RequestMethod.POST)
    public String addBooking(@RequestParam(required = false) Integer propertyId,@RequestParam(required = false) Integer roomId, Booking booking) throws Exception {
           
        booking.setProperty(propertyService.findById(propertyId));
        booking.setRoom(roomService.findById(roomId));
     
        bookingService.create(booking);
        return "redirect:/bookings?success";

    }

    @PostMapping("/bookings/{id}/update")
    public String update(@PathVariable("id") Integer id, @Valid Booking booking,
            BindingResult result) throws Exception {

        if (result.hasErrors()) {
            return "bookings?error";
        }
        bookingService.update(id, booking);
        return "redirect:/bookings?success";
    }

    @GetMapping("/bookings/{id}/delete")
    public String delete(@PathVariable("id") Integer id) {
        bookingService.deleteById(id);
        return "redirect:/bookings?success";
    }
}
