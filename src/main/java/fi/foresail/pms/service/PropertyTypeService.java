package fi.foresail.pms.service;

import fi.foresail.pms.model.PropertyType;
import fi.foresail.pms.repository.PropertyTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PropertyTypeService {

	@Autowired
	private PropertyTypeRepository propertyTypeRepository;

	public List<PropertyType> getTypes(){
		return (List<PropertyType>) propertyTypeRepository.findAll();
	}

	public PropertyType findById(Long id) throws Exception {
		return  propertyTypeRepository.findById(id).orElseThrow(() -> new Exception("Property type not found. id: " + id));
	}
}
