package fi.foresail.pms.service;


public interface UserService<T, U> {
	T save(U u);

	T update(U u);

	T findByUsername(String string);
}