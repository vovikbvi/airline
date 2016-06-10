package by.bogdevich.training.airline.service.impl;

import java.util.List;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import by.bogdevich.training.airline.dataaccess.FlightCatalogDao;
import by.bogdevich.training.airline.dataaccess.FlightDao;
import by.bogdevich.training.airline.dataaccess.PlaneDao;
import by.bogdevich.training.airline.dataaccess.TicketDao;
import by.bogdevich.training.airline.dataaccess.filtres.FlightFilter;
import by.bogdevich.training.airline.datamodel.Flight;
import by.bogdevich.training.airline.datamodel.Ticket;
import by.bogdevich.training.airline.datamodel.TicketClass;
import by.bogdevich.training.airline.service.FlightService;

@Service
public class FlightServiceImpl implements FlightService {
	private static Logger LOGGER = LoggerFactory.getLogger(FlightServiceImpl.class);

	@Inject
	private FlightDao flightDao;

	@Inject
	private FlightCatalogDao flightCatalogDao;
	
	@Inject
	private PlaneDao planeDao;
	
	@Inject
	private TicketDao ticketDao; 

	@Override
	public Boolean checkClassWeight(Flight flight) {// перенести в логику web
		
		

		int classWeightAirportStart = flightCatalogDao.getFlightCatalogWithFetch(flight.getFlightCatalog()).getAirportStart()
				.getClassWeight().ordinal();

		int classWeightAirportFinish = flightCatalogDao.getFlightCatalogWithFetch(flight.getFlightCatalog()).getAirportFinish()
				.getClassWeight().ordinal();

		int classWeightPlane = planeDao.getPlaneWithFetch(flight.getPlane()).getModelPlane().getClassWeight().ordinal();

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
	public Flight getFlieghtWithFetch(Flight flight) {
		return flightDao.getFlieghtWithFetch(flight);
	}
	
	@Override
	public List<Flight> getRecordsSorted(FlightFilter filter) {
		return flightDao.getRecordsSorted(filter);
}

	
	@Override
	public Long count(FlightFilter filter){
		Long result = flightDao.count(filter);
		return result;
	}	

	@Override
	public Long countBesySeats(Flight flight, TicketClass ticketClass){
	Long result = ticketDao.countBusySeats(flight, ticketClass);
	return result;
	}

}
