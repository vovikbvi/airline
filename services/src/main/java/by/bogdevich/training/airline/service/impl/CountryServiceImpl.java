package by.bogdevich.training.airline.service.impl;

import java.util.List;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import by.bogdevich.training.airline.dataaccess.CountryDao;
import by.bogdevich.training.airline.dataaccess.filtres.CountryFilter;
import by.bogdevich.training.airline.datamodel.Country;
import by.bogdevich.training.airline.service.CountryService;

@Service
public class CountryServiceImpl implements CountryService {
	private static Logger LOGGER = LoggerFactory.getLogger(CityServiceImpl.class);

	@Inject
	CountryDao countryDao;

	@Override
	public void insert(Country country) {
		countryDao.insert(country);
		LOGGER.info("Insert country {}", country);
	}
	
	@Override
	public void update(Country country) {
		countryDao.update(country);
		LOGGER.info("Update country {}", country);
	}

	@Override
	public void delet(Long id) {
		Country country = countryDao.get(id);
		countryDao.delete(id);
		LOGGER.warn("Delet country {}", country);
	}

	@Override
	public Country get(Long id) {
		return countryDao.get(id);
	}

	@Override
	public List<Country> getAll() {
		return countryDao.getAll();
	}
	
	@Override
	public List<Country> getRecordsSorted(CountryFilter filter) {
		return countryDao.getRecordsSorted(filter);
}
	
	@Override
	public Long count(CountryFilter filter){
		return countryDao.count(filter);
	}	

}
