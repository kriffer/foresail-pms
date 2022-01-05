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
package fi.foresail.pms.service;

import fi.foresail.pms.exception.PropertyNotFoundException;
import fi.foresail.pms.model.Account;
import fi.foresail.pms.model.Property;
import fi.foresail.pms.repository.PropertyRepository;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class PropertyService {

	@Autowired
	private PropertyRepository propertyRepository;


	public List<Property> findAll() {
		return propertyRepository.findAllProperties();
	}

	public List<Property> findAllByAccount(Account account) {
		return propertyRepository.findAllPropertiesByAccount(account);
	}

	public Property findById(Integer id) {
		return propertyRepository.findById(id).orElseThrow(() -> new PropertyNotFoundException(id));
	}

	public Property create(Account account, Property property) throws Exception {
		property.setCreated(new Timestamp(new Date().getTime()));
		property.setAccount(account);
		int size = propertyRepository.findAllPropertiesByAccount(account).size();
		property.setName("Property " + (size+1));
		return propertyRepository.save(property);
	}

	public Property update(Integer id, Property property) {
		return propertyRepository.findById(id).map(prop -> {
			prop.setActive(property.getActive());
			prop.setAccount(property.getAccount());
			prop.setAddress(property.getAddress());
			prop.setCity(property.getCity());
			prop.setCountry(property.getCountry());
			prop.setCurrency(property.getCurrency());
			prop.setContactFirstName(property.getContactFirstName());
			prop.setContactLastName(property.getContactLastName());
			prop.setLatitude(property.getLatitude());
			prop.setLongitude(property.getLongitude());
			prop.setEmail(property.getEmail());
			prop.setPhone(property.getPhone());
			prop.setMobile(property.getMobile());
			prop.setVatRate(property.getVatRate());
			prop.setWeb(property.getWeb());
			prop.setUpdated(new Timestamp(new Date().getTime()));
			prop.setPostcode(property.getPostcode());
			prop.setRooms(property.getRooms());
			return propertyRepository.save(prop);
		}).orElseGet(() -> {
			property.setId(id);
			return propertyRepository.save(property);
		});
	}

	public void deleteById(Integer id) {
		propertyRepository.deleteById(id);
	}

}
