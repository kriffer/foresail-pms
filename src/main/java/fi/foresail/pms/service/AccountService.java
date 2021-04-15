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
