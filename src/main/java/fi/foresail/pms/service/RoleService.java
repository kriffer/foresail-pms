package fi.foresail.pms.service;


import fi.foresail.pms.model.Role;
import fi.foresail.pms.repository.RoleRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@Transactional
public class RoleService {

	@Autowired
	private RoleRepository roleRepository;

	@Transactional
	public List<Role> findAll() {
		List<Role> allRoles = roleRepository.findAllRoles();
		return allRoles;

	}

	public Role findByName(String name) {
		return roleRepository.findByName(name);
	}

	public Role addRole(Role role) {
		return roleRepository.save(role);
	}

	@Transactional
	public Optional<Role> findRoleById(Long id) {
		return roleRepository.findById(id);
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public Role create(Role role) {
		return roleRepository.save(role);
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public void delete(Role role) {
		roleRepository.delete(role);


	}

	@Transactional(propagation = Propagation.REQUIRED)
	public Role update(Role role) {
		return roleRepository.save(role);
	}
}
