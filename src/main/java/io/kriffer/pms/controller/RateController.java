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
import io.kriffer.pms.model.Property;
import io.kriffer.pms.model.Rate;
import io.kriffer.pms.service.PropertyService;
import io.kriffer.pms.service.RateService;
import io.kriffer.pms.service.UserServiceImpl;
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
