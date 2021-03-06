package by.bogdevich.training.airline.service.impl;

import java.util.List;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import by.bogdevich.training.airline.dataaccess.AirportDao;
import by.bogdevich.training.airline.dataaccess.FlightCatalogDao;
import by.bogdevich.training.airline.dataaccess.filtres.FlightCatalogFilter;
import by.bogdevich.training.airline.datamodel.Airport;
import by.bogdevich.training.airline.datamodel.Country;
import by.bogdevich.training.airline.datamodel.FlightCatalog;
import by.bogdevich.training.airline.service.FlightCatalogService;

@Service
public class FlightCatalogServiceImpl implements FlightCatalogService {
	private static Logger LOGGER = LoggerFactory.getLogger(FlightCatalogServiceImpl.class);

	@Inject
	FlightCatalogDao flightCatalogDao;
	
	
	@Inject
	AirportDao airportDao;

	@Override
	public Boolean checkInternatoinal(FlightCatalog flightCatalog) {
		
			Country countryStart =  airportDao.getAirportWithFetch(flightCatalog.getAirportStart()).getCity().getCountry();  
			Country countryFinish = airportDao.getAirportWithFetch(flightCatalog.getAirportFinish()).getCity().getCountry();
			return !countryStart.equals(countryFinish);

	}

	@Override
	public void update(FlightCatalog flightCatalog) {

		flightCatalog.setInternational(checkInternatoinal(flightCatalog));
		flightCatalogDao.update(flightCatalog);
		LOGGER.info("Update flight catalog {}", flightCatalog);
	}
	
	@Override
	public void insert(FlightCatalog flightCatalog) {
		
		flightCatalog.setInternational(checkInternatoinal(flightCatalog));
		flightCatalogDao.insert(flightCatalog);
		LOGGER.info("Insert flight catalog {}", flightCatalog);
	}


	@Override
	public void delete(Long id) {
		FlightCatalog flightCatalog = flightCatalogDao.get(id);
		flightCatalogDao.delete(id);
		LOGGER.info("Delete flight catalog {}", flightCatalog);
	}


	@Override
	public FlightCatalog get(Long id) {
		return flightCatalogDao.get(id);
	}


	@Override
	public List<FlightCatalog> getAll() {
		return flightCatalogDao.getAll();
	}

	@Override
	public List<FlightCatalog> getRecordsSorted(FlightCatalogFilter filter) {
		return flightCatalogDao.getRecordsSorted(filter);
}

	@Override
	public FlightCatalog getFlieghtCatalogWithFetch(FlightCatalog flightCatalog) {
		return flightCatalogDao.getFlightCatalogWithFetch(flightCatalog);
	}

	
	@Override
	public Long count(FlightCatalogFilter filter){
		return flightCatalogDao.count(filter);
	}	

}
