package by.bogdevich.training.airline.service.impl;

import java.util.List;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import by.bogdevich.training.airline.dataaccess.TicketDao;
import by.bogdevich.training.airline.datamodel.Ticket;
import by.bogdevich.training.airline.service.TicketService;

@Service
public class TicketServiceImpl implements TicketService {
	private static Logger LOGGER = LoggerFactory.getLogger(CityServiceImpl.class);
	
	@Inject
	TicketDao ticketDao;


	@Override
	public void insert(Ticket ticket) {
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
}
