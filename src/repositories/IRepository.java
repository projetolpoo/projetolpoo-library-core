package src.repositories;

import java.util.List;

public interface IRepository<T> {

	/**
	 * Add an entity
	 *
	 * @param entity must not be {@literal null}.
	 */
	void add(T entity);

	/**
	 * Retrieves an entity by its id.
	 *
	 * @param id must not be {@literal null}.
	 */
	T getById(int id);

    /**
	 * Returns all instances of the type.
	 *
	 */
	List<T> getAll();

	/**
	 * Update an entity by itself
	 *
	 * @param entity must not be {@literal null}.
	 */
	boolean update(T entity);

	/**
	 * Deletes a an entity by its id.
	 *
	 * @param id must not be {@literal null}.]
	 */
	boolean delete(int id);

}
