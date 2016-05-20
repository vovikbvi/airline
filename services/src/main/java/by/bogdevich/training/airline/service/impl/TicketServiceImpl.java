package by.bogdevich.training.airline.service.impl;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;

import javax.inject.Inject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import by.bogdevich.training.airline.dataaccess.FlightDao;
import by.bogdevich.training.airline.dataaccess.TicketDao;
import by.bogdevich.training.airline.dataaccess.filtres.TicketFilter;
import by.bogdevich.training.airline.datamodel.Flight;
import by.bogdevich.training.airline.datamodel.ModelPlane;
import by.bogdevich.training.airline.datamodel.Ticket;
import by.bogdevich.training.airline.service.TicketService;

@Service
public class TicketServiceImpl implements TicketService {
	private static Logger LOGGER = LoggerFactory.getLogger(CityServiceImpl.class);

	@Inject
	TicketDao ticketDao;

	@Inject
	FlightDao flightDao;

	public ModelPlane getModelplane(Ticket ticket) {
		if (ticket.getFlight() != null) {
			Flight flight = flightDao.getFullFlieght(ticket.getFlight());
			return flight.getPlane().getModelPlane();
		}

		return null;
	}

	private double percentBusySeats(Ticket ticket) {

		// занятое кол во мест
		double busySeats = ticketDao.countBusySeats(ticket.getFlight());
		double colSeats = 0;
		if (ticket.getTicketClass() != null) {
			switch (ticket.getTicketClass()) {
			case BUSINES_CLASS:
				colSeats = getModelplane(ticket).getColPassangersBuisnes();
				break;
			case ECONOMY:
				colSeats = getModelplane(ticket).getColPassangersEconomy();
				break;
			case FIRST_CLASS:
				colSeats = getModelplane(ticket).getColPassangersFirstclass();
				break;
			}
		} else {
			return 0.0;
		}

		double result = 0;
		if (busySeats != 0) {
			result = (busySeats / colSeats);
		} else {
			result = 0;
		}
		return result;
	}

	private double percentToDateDeparture(Ticket ticket) {
		Flight flight = flightDao.getFullFlieght(ticket.getFlight());
		LocalDateTime dateStart = flight.getStartSaleTicket();
		LocalDateTime dateEnd = flight.getDepartureTime();

		Duration durationSale = Duration.between(dateStart, dateEnd);
		double periodSale = durationSale.toDays();

		Duration durationToStart = Duration.between(LocalDateTime.now(), dateEnd);
		double periodToDeparture = durationToStart.toDays();

		double result = 0;
		if (periodSale != 0) {
			result = (periodToDeparture / periodSale);
		} else {
			result = 0;
		}
		return result;
	}

	private double getPrice(Ticket ticket) {
		double basicPrice = ticketDao.fiendBasicPrice(ticket.getDateBought());
		double distance = flightDao.getFullFlieght(ticket.getFlight()).getFlightCatalog().getDistance();
		return basicPrice * distance;
	}

	@Override
	public double baggageCost(Ticket ticket) {
		double fullWeightBaggage = 0.0;
		if (ticketDao.countAllBaggage(ticket.getFlight()) != null) {
			fullWeightBaggage = ticketDao.countAllBaggage(ticket.getFlight()) + ticket.getWeightBaggage();
		}
		double weightBaggagePlane = flightDao.getFullFlieght(ticket.getFlight()).getPlane().getModelPlane()
				.getWeightAllBaggage();

		if (fullWeightBaggage <= weightBaggagePlane) {
			double price = getPrice(ticket);
			double result = price + (price * ticket.getWeightBaggage() / 100);
			return result;
		} else {
			ticket.setBaggage(false);
			LOGGER.info("luggage space is full {}", ticket.getFlight());
			return 0.0;
		}
		
	}

	@Override
	public double ticketCost(Ticket ticket) {
		double price = getPrice(ticket);
		double factorBusySeats = price * 0.2 * percentBusySeats(ticket);
		double factorToDateDeparture = price * 0.2 * percentToDateDeparture(ticket);
		double factorPrioritySeats = ticket.getPrioritySeats() ? 20 : 0;
		double factorProrityRegistration = ticket.getPriorityRegistration() ? 15 : 0;
		double factorForBaby = ticket.getForBaby() ? (price * 0.2) : 0;
		double costBaggage = ticket.getBaggage() ? baggageCost(ticket) : 0;

		double result = price + factorBusySeats + factorToDateDeparture + factorPrioritySeats
				+ factorProrityRegistration - factorForBaby + costBaggage;
		return result;
	}

	@Override
	public void insert(Ticket ticket) {
      Double t = ticketDao.countAllBaggage(ticket.getFlight());
		ticket.setDateBought(LocalDateTime.now());
		ticket.setCosts(ticketCost(ticket));
		ticketDao.insert(ticket);

		LOGGER.info("Insert ticket {}", ticket);
	}

	@Override
	public void update(Ticket ticket) {

		ticket.setDateBought(LocalDateTime.now());
		ticket.setCosts(ticketCost(ticket));

		ticketDao.update(ticket);

		LOGGER.info("Update ticket {}", ticket);
	}

	@Override
	public void delete(Long id) {
		Ticket ticket = ticketDao.get(id);
		
			ticketDao.delete(id);
			LOGGER.info("Delete ticket {}", ticket);
		
	}

	@Override
	public Ticket get(Long id) {
		return ticketDao.get(id);
	}

	@Override
	public List<Ticket> getAll() {
		return ticketDao.getAll();
	}

	@Override
	public List<Ticket> getRecordsSorted(TicketFilter filter) {
		return ticketDao.getRecordsSorted(filter);
}
	
	@Override
	public Long count(TicketFilter filter){
		return ticketDao.count(filter);
	}	
}
