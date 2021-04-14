package fi.foresail.pms.service;

import fi.foresail.pms.exception.AccountNotFoundException;
import fi.foresail.pms.model.Account;
import fi.foresail.pms.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class AccountService {

	@Autowired
	private AccountRepository accountRepository;

	public List<Account> findAll() {
		return accountRepository.findAll();
	}


	public Account findById(Long id){
		return accountRepository.findById(id).orElseThrow(() -> new AccountNotFoundException(id));
	}

	public Account create(Account account) {
		return accountRepository.save(account);
	}

	public Account update(Long id, Account account) {
		return accountRepository.findById(id).map(accnt -> {
			accnt.setActive(account.getActive());
			accnt.setBalance(account.getBalance());
			accnt.setCharge(account.getCharge());
			accnt.setProperties(account.getProperties());
			accnt.setTimeZone(account.getTimeZone());
			accnt.setUsers(account.getUsers());
			return accountRepository.save(accnt);
		}).orElseGet(() -> {
			account.setId(id);
			return accountRepository.save(account);
		});
	}

	public void deleteById(Long id) {
		accountRepository.deleteById(id);
	}
}
