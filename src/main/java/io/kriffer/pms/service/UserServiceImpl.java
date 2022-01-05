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
package io.kriffer.pms.service;

import io.kriffer.pms.exception.UserNotFoundException;
import io.kriffer.pms.model.Role;
import io.kriffer.pms.model.User;
import io.kriffer.pms.repository.RoleRepository;
import io.kriffer.pms.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;


@Service
@Slf4j
public class UserServiceImpl implements  UserService<User, UserRegistrationDto> {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private RoleRepository roleRepository;

	@Autowired
	private BCryptPasswordEncoder passwordEncoder;


	@Transactional(propagation = Propagation.REQUIRED)
	public List<User> findAllUsers() {
		return userRepository.findAll();
	}

	@Transactional
	public User findUserById(Integer id) {
		return userRepository.findById(id)
				.orElseThrow(() -> new UserNotFoundException(id));
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public User getUserByName(String name) {
		return userRepository.findByName(name);

	}

	@Transactional
	@Override
	public User update(UserRegistrationDto registration) {
		User user = userRepository.findByName(registration.getName());
		user.setEmail(registration.getEmail());
		user.setPassword(passwordEncoder.encode(registration.getPassword()));
		user.setUpdated(new Date());
		return userRepository.save(user);
	}

	@Transactional
	public User edit(User user, String roles) {
		String[] splitted = roles.split(",");
		ArrayList list = new ArrayList();
		for (String s : splitted) {
			Role r = roleRepository.findByName(s);
			list.add(r);
		}
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		user.setRoles(list);

		user.setUpdated(new Date());
		return userRepository.save(user);
	}

	@Transactional
	@Override
	public User save(UserRegistrationDto registration) {
		User user = new User();
		user.setName(registration.getName());
		user.setEmail(registration.getEmail());
		log.debug("DTO email: " + user.getEmail());
		log.debug("DTO name: " + user.getName());

		user.setPassword(passwordEncoder.encode(registration.getPassword()));
		log.debug("DTO pass: " + user.getPassword());
		Role role = roleRepository.findByName("ROLE_USER");
		user.setRoles(Arrays.asList(role));
		user.setEnabled((byte) 1);
		user.setCreated(new Date());
		return userRepository.save(user);
	}


	@Transactional(propagation = Propagation.REQUIRED)
	public void delete(User user) {
		userRepository.delete(user);
	}

	@Transactional
	public User findByEmail(String email) {
		return userRepository.findByEmailIgnoreCase(email);
	}


	@Override
	public User findByUsername(String username) {
		return userRepository.findByName(username);
	}

	public String newPass(String email){
		User user = findByEmail(email);
	    if(user==null){
	    	return "no user";
	    }
		String pass = new PasswordGenerator().generateRandomPassword();
		user.setPassword(passwordEncoder.encode(pass));
		userRepository.save(user);
		//new EmailService().send(email,pass);
		return "success";

	}

}