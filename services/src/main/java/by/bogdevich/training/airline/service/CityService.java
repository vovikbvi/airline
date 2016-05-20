package by.bogdevich.training.airline.service;

import java.util.List;

import javax.transaction.Transactional;

import by.bogdevich.training.airline.dataaccess.filtres.CityFilter;
import by.bogdevich.training.airline.datamodel.City;

public interface CityService {
	
	@Transactional
	void update(City city);

	@Transactional
	void insert(City city);

	@Transactional
	void delete(Long id);

	City get(Long id);

	List<City> getAll();

	Long count(CityFilter filter);

}