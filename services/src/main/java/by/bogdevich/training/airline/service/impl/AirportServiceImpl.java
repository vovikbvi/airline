package by.bogdevich.training.airline.service.impl;

import java.util.List;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import by.bogdevich.training.airline.dataaccess.AirportDao;
import by.bogdevich.training.airline.dataaccess.filtres.AirportFilter;
import by.bogdevich.training.airline.datamodel.Airport;
import by.bogdevich.training.airline.service.AirportService;

@Service
public class AirportServiceImpl implements AirportService {
	private static Logger LOGGER = LoggerFactory.getLogger(CityServiceImpl.class);

	@Inject
	AirportDao airportDao;

	@Override
	public void update(Airport airport) {
		airportDao.update(airport);
		LOGGER.info("Update airport {}", airport);
	}

	@Override
	public void insert(Airport airport) {
		airportDao.insert(airport);
		LOGGER.info("Insert airport {}", airport);
	}

	@Override
	public void delete(Long id) {
		Airport airport = airportDao.get(id);
		airportDao.delete(id);
		LOGGER.info("Delete airport {}", airport);
	}

	@Override
	public Airport get(Long id) {
		return airportDao.get(id);
	}

	@Override
	public List<Airport> getAll() {
		return airportDao.getAll();
	}
	
	@Override
	public List<Airport> getRecordsSorted(AirportFilter filter) {
		return airportDao.getRecordsSorted(filter);
}

	
	@Override
	public Long count(AirportFilter filter){
		return airportDao.count(filter);
	}	

}
