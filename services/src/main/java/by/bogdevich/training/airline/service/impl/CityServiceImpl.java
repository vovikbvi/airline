package by.bogdevich.training.airline.service.impl;

import java.util.List;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import by.bogdevich.training.airline.dataaccess.CityDao;
import by.bogdevich.training.airline.dataaccess.filtres.CityFilter;
import by.bogdevich.training.airline.dataaccess.filtres.TicketFilter;
import by.bogdevich.training.airline.datamodel.City;
import by.bogdevich.training.airline.datamodel.Ticket;
import by.bogdevich.training.airline.service.CityService;

@Service
public class CityServiceImpl implements CityService {
	private static Logger LOGGER = LoggerFactory.getLogger(CityServiceImpl.class);

	@Inject
	CityDao cityDao;

	@Override
	public void insert(City city) {
		cityDao.insert(city);

		LOGGER.info("Insert city {}", city);
	}

	@Override
	public void update(City city) {
		cityDao.update(city);

		LOGGER.info("Update city {}", city);
	}

	@Override
	public void delete(Long id) {
		City city = cityDao.get(id);
		cityDao.delete(id);
		LOGGER.info("Delete city {}", city);
	}

	@Override
	public City get(Long id) {
		return cityDao.get(id);
	}

	@Override
	public List<City> getAll() {
		return cityDao.getAll();
	}

	@Override
	public List<City> getRecordsSorted(CityFilter filter) {
		return cityDao.getRecordsSorted(filter);
}

	
	@Override
	public Long count(CityFilter filter){
		return cityDao.count(filter);
	}	

}
