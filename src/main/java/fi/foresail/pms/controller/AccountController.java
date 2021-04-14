package fi.foresail.pms.controller;

import fi.foresail.pms.model.User;
import fi.foresail.pms.service.AccountService;
import fi.foresail.pms.service.UserServiceImpl;
import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
class AccountController {

    @Autowired
    private AccountService accountService;

    @Autowired
    private UserServiceImpl userService;

    @GetMapping("/account")
    public String getAccount(Principal principal, Model model) {
        User user = userService.getUserByName(principal.getName());

        model.addAttribute("account", user.getAccount());
        return "account";
    }

}
