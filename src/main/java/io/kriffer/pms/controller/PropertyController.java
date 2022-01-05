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

package io.kriffer.pms.controller;

import io.kriffer.pms.model.Account;
import io.kriffer.pms.model.Property;

import javax.validation.Valid;

import io.kriffer.pms.service.AccountContext;
import io.kriffer.pms.service.AccountService;
import io.kriffer.pms.service.PropertyService;
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
    private AccountService accountService;

    @Autowired
    private AccountContext accountContext;

    @GetMapping("/properties")
    public String getProperties(Model model) {
        Account account = accountContext.getAccount();
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
    public String update(@PathVariable("id") Integer id,@RequestParam(required = false) Integer type,
            @RequestParam(required = false) Integer accountId, @Valid Property property,
            BindingResult result) throws Exception {
        property.setAccount(accountService.findById(accountId));
        if (result.hasErrors()) {
            return "properties?error";
        }
        propertyService.update(id, property);
        return "redirect:/properties?success";
    }

    @GetMapping("/properties/{id}/delete")
    public String delete(@PathVariable("id") Integer id) {
        propertyService.deleteById(id);
        return "redirect:/properties?success";
    }
}
