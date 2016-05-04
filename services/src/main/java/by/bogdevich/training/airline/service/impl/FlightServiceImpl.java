package by.bogdevich.training.airline.service.impl;

import java.util.List;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import by.bogdevich.training.airline.dataaccess.FlightDao;
import by.bogdevich.training.airline.dataaccess.ModelPlaneDao;
import by.bogdevich.training.airline.datamodel.ClassWeight;
import by.bogdevich.training.airline.datamodel.Flight;
import by.bogdevich.training.airline.service.FlightService;

@Service
public class FlightServiceImpl implements FlightService {
	private static Logger LOGGER = LoggerFactory.getLogger(CityServiceImpl.class);

	@Inject
	FlightDao flightDao;
	
	@Inject
	ModelPlaneDao modelPlaneDao;

	private boolean checkClassWeight(Flight flight) {
		ClassWeight classWeightAirport = flight.getPlane().getModelPlane().getClassWeight();
		ClassWeight classWeightPlane = flight.getFlightCatalog().getAirportFinish().getClassWeight();

		if (classWeightAirport.ordinal() >= classWeightPlane.ordinal()) {
			return true;
		}
		return true;
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
	public Integer getColPassangersBuisnes(Flight flight){
		return flightDao.getColPassangersBuisnes(flight);
	}
}
