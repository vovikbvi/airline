package by.bogdevich.training.airline.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import by.bogdevich.training.airline.dataaccess.filtres.CountryFilter;
import by.bogdevich.training.airline.datamodel.Country;

public interface CountryService {
	
	@Transactional 
	void update(Country country);

	@Transactional 
	void insert(Country country);

	@Transactional 
	void delete(Long id);

	Country get(Long id);

	List<Country> getAll();

	Long count(CountryFilter filter);

	List<Country> getRecordsSorted(CountryFilter filter);

}