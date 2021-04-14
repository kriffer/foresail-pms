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

	@Autowired
	private PropertyTypeService propertyTypeService;

	public List<Property> findAll() {
		return propertyRepository.findAllProperties();
	}

	public List<Property> findAllByAccount(Account account) {
		return propertyRepository.findAllPropertiesByAccount(account);
	}

	public Property findById(Long id) {
		return propertyRepository.findById(id).orElseThrow(() -> new PropertyNotFoundException(id));
	}

	public Property create(Account account, Property property) throws Exception {
		property.setCreated(new Timestamp(new Date().getTime()));
		property.setPropertyType(propertyTypeService.findById(1L));
		property.setAccount(account);
		int size = propertyRepository.findAllPropertiesByAccount(account).size();
		property.setName("Property " + (size+1));
		return propertyRepository.save(property);
	}

	public Property update(Long id, Property property) {
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
			prop.setPropertyType(property.getPropertyType());
			prop.setVatRate(property.getVatRate());
			prop.setWeb(property.getWeb());
			prop.setUpdated(new Timestamp(new Date().getTime()));
			prop.setPostcode(property.getPostcode());
			prop.setRooms(property.getRooms());
                        prop.setPropertyType(property.getPropertyType());
			return propertyRepository.save(prop);
		}).orElseGet(() -> {
			property.setId(id);
			return propertyRepository.save(property);
		});
	}

	public void deleteById(Long id) {
		propertyRepository.deleteById(id);
	}

}
