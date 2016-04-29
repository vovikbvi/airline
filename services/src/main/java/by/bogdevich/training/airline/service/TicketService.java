package by.bogdevich.training.airline.service;

import java.util.List;

import javax.transaction.Transactional;

import by.bogdevich.training.airline.datamodel.Ticket;

public interface TicketService {

	@Transactional
	void insert(Ticket ticket);

	@Transactional
	void update(Ticket ticket);

	@Transactional
	void delete(Long id);

	Ticket get(Long id);

	List<Ticket> getAll();

}