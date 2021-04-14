package fi.foresail.pms.config;


import fi.foresail.pms.model.*;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurer;
import org.springframework.stereotype.Component;

@Component
public class ExposeEntityIdConfiguration implements RepositoryRestConfigurer {

	@Override
	public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config) {
		config.exposeIdsFor(Room.class);
		config.exposeIdsFor(Unit.class);
		config.exposeIdsFor(Property.class);
		config.exposeIdsFor(Rate.class);
		config.exposeIdsFor(Account.class);
		config.exposeIdsFor(Booking.class);
	}
}