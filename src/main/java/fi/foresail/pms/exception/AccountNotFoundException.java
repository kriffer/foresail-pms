package fi.foresail.pms.exception;

public class AccountNotFoundException extends RuntimeException{
		public AccountNotFoundException(Long id) {
			super("Could not find account " + id);
		}
}
