package by.bogdevich.training.airline.dataaccess.impl;

import org.springframework.stereotype.Repository;

import by.bogdevich.training.airline.dataaccess.TicketDao;
import by.bogdevich.training.airline.datamodel.Ticket;

@Repository
public class TicketDaoImpl extends AbstractDaoImpl<Ticket, Long> implements TicketDao {

	protected TicketDaoImpl() {
		super(Ticket.class);
	}

}
