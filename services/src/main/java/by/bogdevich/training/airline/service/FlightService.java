package by.bogdevich.training.airline.service;

import java.util.List;

import javax.transaction.Transactional;

import by.bogdevich.training.airline.dataaccess.filtres.FlightFilter;
import by.bogdevich.training.airline.datamodel.Flight;

public interface FlightService {

	@Transactional
	void update(Flight flight);

	@Transactional
	void insert(Flight flight);

	@Transactional
	void delete(Long id);

	Flight get(Long id);

	List<Flight> getAll();
	
	Flight getFullFlieght(Flight flight);

	Boolean checkClassWeight(Flight flight);

	Long count(FlightFilter filter);

	List<Flight> getRecordsSorted(FlightFilter filter);

}