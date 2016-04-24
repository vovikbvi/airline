package by.bogdevich.training.airline.dataaccess;

import java.util.List;

public interface AbstractDao<T, ID> {

	List<T> getAll();

	T get(ID id);

	T insert(T entity);

	T update(T entity);

	void delete(ID id);

}