package fi.foresail.pms.controller;

import fi.foresail.pms.model.Account;
import fi.foresail.pms.model.Property;
import fi.foresail.pms.model.PropertyType;
import javax.validation.Valid;

import fi.foresail.pms.service.AccountContext;
import fi.foresail.pms.service.AccountService;
import fi.foresail.pms.service.PropertyService;
import fi.foresail.pms.service.PropertyTypeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@Slf4j
public class PropertyController {

    @Autowired
    private PropertyService propertyService;

    @Autowired
    private PropertyTypeService propertyTypeService;
    
    @Autowired
    private AccountService accountService;

    @Autowired
    private AccountContext accountContext;

    @GetMapping("/properties")
    public String getProperties(Model model) {
        Account account = accountContext.getAccount();
        model.addAttribute("types", propertyTypeService.getTypes());
        model.addAttribute("properties", propertyService.findAllByAccount(account));
        return "properties";
    }

    @RequestMapping(value = "/properties/add", method = RequestMethod.POST)
    public String addProperty(Property property) throws Exception {
        Account account = accountContext.getAccount();
        propertyService.create(account, property);
        return "redirect:/properties?success";

    }

    @PostMapping("/properties/{id}/update")
    public String update(@PathVariable("id") Long id,@RequestParam(required = false) Long type,
            @RequestParam(required = false) Long accountId, @Valid Property property,
            BindingResult result) throws Exception {
        property.setAccount(accountService.findById(accountId));
        PropertyType propertyType = propertyTypeService.findById(type);
        property.setPropertyType(propertyType);

        if (result.hasErrors()) {
            return "properties?error";
        }
        propertyService.update(id, property);
        return "redirect:/properties?success";
    }

    @GetMapping("/properties/{id}/delete")
    public String delete(@PathVariable("id") Long id) {
        propertyService.deleteById(id);
        return "redirect:/properties?success";
    }
}
