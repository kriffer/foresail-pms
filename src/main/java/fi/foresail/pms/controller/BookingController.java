package fi.foresail.pms.controller;

import fi.foresail.pms.model.Account;
import fi.foresail.pms.model.Booking;
import fi.foresail.pms.model.Property;
import fi.foresail.pms.service.AccountContext;
import fi.foresail.pms.service.AccountService;
import fi.foresail.pms.service.BookingService;
import fi.foresail.pms.service.PropertyService;
import fi.foresail.pms.service.RoomService;
import fi.foresail.pms.service.UserServiceImpl;
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
    public String addBooking(@RequestParam(required = false) Long propertyId,@RequestParam(required = false) Long roomId, Booking booking) throws Exception {
           
        booking.setProperty(propertyService.findById(propertyId));
        booking.setRoom(roomService.findById(roomId));
     
        bookingService.create(booking);
        return "redirect:/bookings?success";

    }

    @PostMapping("/bookings/{id}/update")
    public String update(@PathVariable("id") Long id, @Valid Booking booking,
            BindingResult result) throws Exception {

        if (result.hasErrors()) {
            return "bookings?error";
        }
        bookingService.update(id, booking);
        return "redirect:/bookings?success";
    }

    @GetMapping("/bookings/{id}/delete")
    public String delete(@PathVariable("id") Long id) {
        bookingService.deleteById(id);
        return "redirect:/bookings?success";
    }
}
