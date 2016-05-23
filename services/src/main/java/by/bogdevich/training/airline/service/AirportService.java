package by.bogdevich.training.airline.service;

import java.util.List;

import javax.transaction.Transactional;

import by.bogdevich.training.airline.dataaccess.filtres.AirportFilter;
import by.bogdevich.training.airline.datamodel.Airport;

public interface AirportService {

	@Transactional
	void update(Airport airport);

	@Transactional
	void insert(Airport airport);

	@Transactional
	void delete(Long id);

	Airport get(Long id);

	List<Airport> getAll();

	Long count(AirportFilter filter);

	List<Airport> getRecordsSorted(AirportFilter filter);

}