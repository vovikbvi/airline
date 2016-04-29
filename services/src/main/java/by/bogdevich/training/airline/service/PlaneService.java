package by.bogdevich.training.airline.service;

import java.util.List;

import javax.transaction.Transactional;

import by.bogdevich.training.airline.datamodel.Plane;

public interface PlaneService {

	@Transactional
	void insert(Plane plane);

	@Transactional
	void update(Plane plane);

	@Transactional
	void delete(Long id);

	Plane get(Long id);

	List<Plane> getAll();

}