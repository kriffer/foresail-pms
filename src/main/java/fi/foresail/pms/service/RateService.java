package fi.foresail.pms.service;

import fi.foresail.pms.model.Property;
import fi.foresail.pms.model.Rate;
import fi.foresail.pms.repository.RateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Service
public class RateService {


	@Autowired
	private RateRepository rateRepository;

	public List<Rate> findAllByProperty(Property property) {
		return rateRepository.findAllByProperty(property);
	}
}
