package by.bogdevich.training.airline.service;

import java.util.List;

import javax.transaction.Transactional;

import by.bogdevich.training.airline.datamodel.FlightCatalog;

public interface FlightCatalogService {

	@Transactional
	void update(FlightCatalog flightCatalog);

	@Transactional
	void insert(FlightCatalog flightCatalog);

	@Transactional
	void delete(Long id);

	FlightCatalog get(Long id);

	List<FlightCatalog> getAll();

}