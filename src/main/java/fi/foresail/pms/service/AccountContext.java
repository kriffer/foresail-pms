package fi.foresail.pms.service;

import fi.foresail.pms.model.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AccountContext {

	@Autowired
	private IAuthenticationFacade authenticationFacade;

	@Autowired
	private UserServiceImpl userService;

	public Account getAccount() {
		return userService.getUserByName(authenticationFacade.getAuthentication().getName()).getAccount();
	}

}
