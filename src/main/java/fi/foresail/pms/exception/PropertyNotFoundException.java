package fi.foresail.pms.exception;

public class PropertyNotFoundException extends RuntimeException {
	public PropertyNotFoundException(Long id) {

		super("Could not find property " + id);
	}
}
