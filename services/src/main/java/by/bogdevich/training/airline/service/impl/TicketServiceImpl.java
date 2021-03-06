package by.bogdevich.training.airline.service.impl;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import by.bogdevich.training.airline.dataaccess.FlightDao;
import by.bogdevich.training.airline.dataaccess.TicketDao;
import by.bogdevich.training.airline.dataaccess.filtres.TicketFilter;
import by.bogdevich.training.airline.datamodel.Flight;
import by.bogdevich.training.airline.datamodel.Ticket;
import by.bogdevich.training.airline.datamodel.TicketClass;
import by.bogdevich.training.airline.service.TicketService;

@Service
public class TicketServiceImpl implements TicketService {
	private static Logger LOGGER = LoggerFactory.getLogger(TicketServiceImpl.class);

	@Inject
	private TicketDao ticketDao;

	@Inject
	private FlightDao flightDao;

	private Flight flight;

	private void getFlight(Ticket ticket) {
		if (ticket.getFlight() != null) {
			flight = flightDao.getFlieghtWithFetch(ticket.getFlight());
		}

	}

	private double percentBusySeats(Ticket ticket) {

		// занятое кол во мест
		double busySeats = ticketDao.countBusySeats(ticket.getFlight(), ticket.getTicketClass());
		double colSeats = getColSeats(ticket);

		double result = 0;
		if (colSeats != 0) {
			result = (busySeats / colSeats);
		} else {
			result = 0;
		}
		return result;
	}

	private double getColSeats(Ticket ticket) {
		double colSeats = 0;
		if (ticket.getTicketClass() != null) {
			switch (ticket.getTicketClass()) {
			case BUSINES_CLASS:
				colSeats = flight.getPlane().getModelPlane().getColPassangersBuisnes();
				break;
			case ECONOMY:
				colSeats = flight.getPlane().getModelPlane().getColPassangersEconomy();
				break;
			case FIRST_CLASS:
				colSeats = flight.getPlane().getModelPlane().getColPassangersFirstclass();
				break;
			}
		}
		return colSeats;
	}

	private double percentToDateDeparture(Ticket ticket) {

		Date dateStart = flight.getStartSaleTicket();
		Date dateEnd = flight.getDepartureTime();

		Instant instantStart = Instant.ofEpochMilli(dateStart.getTime());
		LocalDateTime dateStartConvert = LocalDateTime.ofInstant(instantStart, ZoneId.systemDefault());

		Instant instantFinish = Instant.ofEpochMilli(dateEnd.getTime());
		LocalDateTime dateFinishConvert = LocalDateTime.ofInstant(instantFinish, ZoneId.systemDefault());

		Duration durationSale = Duration.between(dateStartConvert, dateFinishConvert);
		double periodSale = durationSale.toDays();

		Duration durationToStart = Duration.between(LocalDateTime.now(), dateFinishConvert);
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
		double distance = flight.getFlightCatalog().getDistance();

		double factorTicketClass = 1;
		if (ticket.getTicketClass() != null) {
			switch (ticket.getTicketClass()) {
			case BUSINES_CLASS:
				factorTicketClass = 1.3;
				break;
			case FIRST_CLASS:
				factorTicketClass = 1.2;
				break;
			case ECONOMY:
				factorTicketClass = 1;
				break;
			}
		}

		double result = basicPrice * distance * factorTicketClass;
		return result;
	}

	@Override
	public double baggageCost(Ticket ticket) {

		double result = 0.0;
		if (ticket.getBaggage() && ticket.getWeightBaggage() != 0) {
			double price = getPrice(ticket);
			result = price + (price * ticket.getWeightBaggage() / 100);
		}
		return result;
	}

	private boolean checkLuggageSpace(Ticket ticket) {
		double fullWeightBaggage = 0.0;
		if (ticket.getBaggage() && ticket.getWeightBaggage() != 0) {
			fullWeightBaggage = ticketDao.countAllBaggage(ticket.getFlight()) + ticket.getWeightBaggage();
		}
		double weightBaggagePlane = flight.getPlane().getModelPlane().getWeightAllBaggage();

		boolean result = fullWeightBaggage <= weightBaggagePlane;
		return result;
	}

	@Override
	public double ticketCost(Ticket ticket) {
		// getFlight(ticket);
		double price = getPrice(ticket);
		double factorBusySeats = price * 0.2 * percentBusySeats(ticket);
		double factorToDateDeparture = price * 0.2 * percentToDateDeparture(ticket);
		double factorPrioritySeats = ticket.getPrioritySeats() ? 20 : 0;
		double factorProrityRegistration = ticket.getPriorityRegistration() ? 15 : 0;
		double factorForBaby = ticket.getForBaby() ? (price * 0.2) : 0;
		double costBaggage = ticket.getBaggage() ? baggageCost(ticket) : 0;

		double result = price + factorBusySeats + factorToDateDeparture + factorPrioritySeats
				+ factorProrityRegistration - factorForBaby + costBaggage;
		result = BigDecimal.valueOf(result).setScale(2,BigDecimal.ROUND_HALF_DOWN).doubleValue();
		return result;
	}

	@Override
	public void insert(Ticket ticket) {
		getFlight(ticket);
		ticket.setDateBought(new Date());
		ticket.setCosts(ticketCost(ticket));

		if (!checkLuggageSpace(ticket)) {
			throw new IllegalArgumentException("luggage space is full");
		}else if(!ticketDao.checkBusySeats(ticket.getNumberSeats(), ticket.getTicketClass(), flight)){
			throw new IllegalArgumentException("seats is busy");
		} else {
			ticketDao.insert(ticket);
			
		}

		LOGGER.info("Insert ticket {}", ticket);
	}

	@Override
	public void update(Ticket ticket) {

		if (ticket.getCosts() == null) {
			getFlight(ticket);
			ticket.setDateBought(new Date());
			ticket.setCosts(ticketCost(ticket));
		}
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
	public Long count(TicketFilter filter) {
		return ticketDao.count(filter);
	}

	@Override
	public List<Integer> getListEmtySeats(Ticket ticket) {
		getFlight(ticket);
		
		List<Integer> allSeats = new ArrayList<Integer>();
		List<Integer> basySeats = ticketDao.getBasySeats(ticket.getFlight(), ticket.getTicketClass());
		List<Integer> result = new ArrayList<Integer>();

		Double colSeats = getColSeats(ticket);
		if (colSeats != null) {

			for (int i = 0; i < colSeats; i++) {
				allSeats.add(i + 1);
			}
		}

		for (Integer i : allSeats) {
			if (!basySeats.contains(i)) {
				result.add(i);
			}
		}

		return result;
	}

	@Override
	public Ticket getTicketWithFetch(Ticket ticket) {
		return ticketDao.getTicketWithFetch(ticket);
	}
	
	@Override
	public void deleteDontPaidTicket(){
		ticketDao.deleteDontPaidTicket();
	}
	
	
}
