package by.bogdevich.training.airline.service;

import java.util.List;

import javax.transaction.Transactional;

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
	
	public Integer getColPassangersBuisnes(Flight flight);

}