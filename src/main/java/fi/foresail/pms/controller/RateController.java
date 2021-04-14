package fi.foresail.pms.controller;

import fi.foresail.pms.model.Account;
import fi.foresail.pms.model.Property;
import fi.foresail.pms.model.Rate;
import fi.foresail.pms.service.PropertyService;
import fi.foresail.pms.service.RateService;
import fi.foresail.pms.service.UserServiceImpl;
import java.security.Principal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class RateController {

    @Autowired
    private PropertyService propertyService;

    @Autowired
    private RateService rateService;

    @Autowired
    private UserServiceImpl userService;

    @GetMapping("/rates")
    public String getRates(Principal principal, Model model) {
        Account account = userService.getUserByName(principal.getName()).getAccount();
        List<Property> properties = propertyService.findAllByAccount(account);
        Map<Property, List<Rate>> map = new HashMap<>();
        properties.forEach(p -> {
            map.put(p, rateService.findAllByProperty(p));
        });
        model.addAttribute("properties", map);
        return "rates";
    }
}
