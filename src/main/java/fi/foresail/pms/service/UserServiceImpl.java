package fi.foresail.pms.service;

import fi.foresail.pms.exception.UserNotFoundException;
import fi.foresail.pms.model.Role;
import fi.foresail.pms.model.User;
import fi.foresail.pms.repository.RoleRepository;
import fi.foresail.pms.repository.UserRepository;
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
	public User findUserById(Long id) {
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