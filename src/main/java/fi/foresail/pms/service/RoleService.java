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
