package fi.foresail.pms.exception;


public class UserNotFoundException extends RuntimeException {
	public UserNotFoundException(Long id) {

		super("Could not find property " + id);
	}
}
