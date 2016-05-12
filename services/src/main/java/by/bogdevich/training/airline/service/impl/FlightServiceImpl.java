package by.bogdevich.training.airline.service.impl;

import java.util.List;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import by.bogdevich.training.airline.dataaccess.FlightCatalogDao;
import by.bogdevich.training.airline.dataaccess.FlightDao;
import by.bogdevich.training.airline.dataaccess.ModelPlaneDao;
import by.bogdevich.training.airline.dataaccess.PlaneDao;
import by.bogdevich.training.airline.datamodel.ClassWeight;
import by.bogdevich.training.airline.datamodel.Flight;
import by.bogdevich.training.airline.service.FlightService;

@Service
public class FlightServiceImpl implements FlightService {
	private static Logger LOGGER = LoggerFactory.getLogger(CityServiceImpl.class);

	@Inject
	FlightDao flightDao;

	@Inject
	FlightCatalogDao flightCatalogDao;
	
	@Inject
	PlaneDao planeDao;

	@Override
	public Boolean checkClassWeight(Flight flight) {// перенести в логику web
		// Flight fullFlieght = flightDao.getFullFlieght(flight);

		int classWeightAirportStart = flightCatalogDao.getFullFlightCatalog(flight.getFlightCatalog()).getAirportStart()
				.getClassWeight().ordinal();

		int classWeightAirportFinish = flightCatalogDao.getFullFlightCatalog(flight.getFlightCatalog()).getAirportFinish()
				.getClassWeight().ordinal();

		int classWeightPlane = planeDao.getFullPlane(flight.getPlane()).getModelPlane().getClassWeight().ordinal();

		boolean checkClassWeightStart = classWeightAirportStart <= classWeightPlane;
		boolean checkClassWeightStartFinish = classWeightAirportFinish <= classWeightPlane;
		
		if (checkClassWeightStart && checkClassWeightStartFinish) {
			return true;
		}

		return false;
	}

	@Override
	public void insert(Flight flight) {

		flightDao.insert(flight);
		LOGGER.info("Insert flight {}", flight);
	}

	@Override
	public void update(Flight flight) {

		flightDao.update(flight);
		LOGGER.info("Update flight {}", flight);
	}

	@Override
	public void delete(Long id) {
		Flight flight = flightDao.get(id);
		flightDao.delete(id);
		LOGGER.info("Delete flight {}", flight);
	}

	@Override
	public Flight get(Long id) {
		return flightDao.get(id);
	}

	@Override
	public List<Flight> getAll() {
		return flightDao.getAll();
	}

	@Override
	public Flight getFullFlieght(Flight flight) {
		return flightDao.getFullFlieght(flight);
	}
}
