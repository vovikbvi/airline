package by.bogdevich.training.airline.service.impl;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;

import javax.inject.Inject;
import org.hibernate.exception.ConstraintViolationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import by.bogdevich.training.airline.dataaccess.FlightDao;
import by.bogdevich.training.airline.dataaccess.TicketDao;
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
			 result = (busySeats/colSeats) * 100;
		} else {
			result = 100.0;
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
			result = (periodToDeparture / periodSale) * 100;
		} else {
			result = 0;
		}
		return result;
	}

	private double ticketCost() {

		return 12.2;
	}

	@Override
	public void insert(Ticket ticket) {

		percentBusySeats(ticket);
		percentToDateDeparture(ticket);
		int i = ticketDao.fiendDate(ticket.getDateBought());
		
		ticketDao.insert(ticket);

		LOGGER.info("Insert ticket {}", ticket);
	}

	@Override
	public void update(Ticket ticket) {
		ticketDao.update(ticket);

		LOGGER.info("Update ticket {}", ticket);
	}

	@Override
	public void delete(Long id) {
		Ticket ticket = ticketDao.get(id);
		try {
			ticketDao.delete(id);
			LOGGER.info("Delete ticket {}", ticket);
		} catch (ConstraintViolationException e) {
			LOGGER.info("Can't delete {}", ticket); // дописать логер
		}
	}

	@Override
	public Ticket get(Long id) {
		return ticketDao.get(id);
	}

	@Override
	public List<Ticket> getAll() {
		return ticketDao.getAll();
	}

}
