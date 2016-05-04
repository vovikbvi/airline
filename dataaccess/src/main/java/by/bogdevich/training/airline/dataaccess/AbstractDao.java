package by.bogdevich.training.airline.dataaccess;

import java.util.List;

import by.bogdevich.training.airline.datamodel.Flight;

public interface AbstractDao<T, ID> {

	List<T> getAll();

	T get(ID id);

	T insert(T entity);

	T update(T entity);

	void delete(ID id);



}