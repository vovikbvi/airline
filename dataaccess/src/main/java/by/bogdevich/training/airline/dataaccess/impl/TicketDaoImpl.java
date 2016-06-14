package by.bogdevich.training.airline.dataaccess.impl;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaDelete;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;

import org.hibernate.jpa.criteria.OrderImpl;
import org.springframework.stereotype.Repository;
import by.bogdevich.training.airline.dataaccess.TicketDao;
import by.bogdevich.training.airline.dataaccess.filtres.TicketFilter;
import by.bogdevich.training.airline.datamodel.Flight;
import by.bogdevich.training.airline.datamodel.FlightCatalog_;
import by.bogdevich.training.airline.datamodel.Flight_;
import by.bogdevich.training.airline.datamodel.ModelPlane;
import by.bogdevich.training.airline.datamodel.ModelPlane_;
import by.bogdevich.training.airline.datamodel.Plane_;
import by.bogdevich.training.airline.datamodel.Price;
import by.bogdevich.training.airline.datamodel.Price_;
import by.bogdevich.training.airline.datamodel.Ticket;
import by.bogdevich.training.airline.datamodel.TicketClass;
import by.bogdevich.training.airline.datamodel.Ticket_;
import by.bogdevich.training.airline.datamodel.UserProfile_;

@Repository
public class TicketDaoImpl extends AbstractDaoImpl<Ticket, Long, TicketFilter> implements TicketDao {

	protected TicketDaoImpl() {
		super(Ticket.class);
	}


	@Override
	public List<Ticket> getRecordsSorted(TicketFilter filter) {
		EntityManager em = getEntityManager();
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Ticket> cq = cb.createQuery(Ticket.class);
		Root<Ticket> from = cq.from(Ticket.class);

		cq.select(from);

		handleFilterParameters(filter, cb, cq, from);

		if (filter.getFetchFlieght()) {
			from.fetch(Ticket_.flight, JoinType.LEFT);
		}

		if (filter.getFetchUser()) {
			from.fetch(Ticket_.userProfile, JoinType.LEFT);
		}

		if (filter.getFetchAirport()){
			from.fetch(Ticket_.flight, JoinType.LEFT).fetch(Flight_.flightCatalog, JoinType.LEFT).fetch(FlightCatalog_.airportStart, JoinType.LEFT);
			from.fetch(Ticket_.flight, JoinType.LEFT).fetch(Flight_.flightCatalog, JoinType.LEFT).fetch(FlightCatalog_.airportFinish, JoinType.LEFT);
		}
		// set sort params
		if (filter.getSortProperty() != null) {
			Path<Object> expression;
			if (UserProfile_.firstName.equals(filter.getSortProperty())) {
				expression = from.get(Ticket_.userProfile).get(filter.getSortProperty());
			} else {
				expression = from.get(filter.getSortProperty());
			}
			cq.orderBy(new OrderImpl(expression, filter.isSortOrder()));
		}

		TypedQuery<Ticket> q = em.createQuery(cq);

		// set paging
		setPaging(filter, q);

		// set execute query
		List<Ticket> allitems = q.getResultList();
		return allitems;
	}

	@Override
	public void handleFilterParameters(TicketFilter filter, CriteriaBuilder cb, CriteriaQuery<?> cq,
			Root<Ticket> from) {
		if (filter.getUser() != null) {
			Predicate user = cb.equal(from.get(Ticket_.userProfile), filter.getUser());
			cq.where(user);
		}
	}

	@Override
	public Long countBusySeats(Flight flight, TicketClass ticketClass) {
		EntityManager em = getEntityManager();
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Long> cq = cb.createQuery(Long.class);
		Root<Ticket> from = cq.from(Ticket.class);

		cq.select(cb.count(from.get(Ticket_.id)));
		Predicate flightF = cb.equal(from.get(Ticket_.flight), flight);
		Predicate ticketClassF = cb.equal(from.get(Ticket_.ticketClass), ticketClass);
		cq.where(cb.and(flightF, ticketClassF));

		return em.createQuery(cq).getSingleResult();

	}

	@Override
	public Double countAllBaggage(Flight flight) {
		EntityManager em = getEntityManager();
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Double> cq = cb.createQuery(Double.class);
		Root<Ticket> from = cq.from(Ticket.class);

		cq.select(cb.sum(from.get(Ticket_.weightBaggage)));
		cq.where(cb.equal(from.get(Ticket_.flight), flight));
		Double result = em.createQuery(cq).getSingleResult();
		 result = (result!=null) ? result : 0;
		return result;

	}

	private Date fiendDatePrice(Date dateDeparture) {
		EntityManager em = getEntityManager();
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Date> cq = cb.createQuery(Date.class);
		Root<Price> from = cq.from(Price.class);

		cq.select(cb.greatest(from.get(Price_.dataChange)));
		cq.where(cb.lessThan(from.get(Price_.dataChange), dateDeparture));
		return em.createQuery(cq).getSingleResult();
	}

	@Override
	public Double fiendBasicPrice(Date dateDeparture) {
		EntityManager em = getEntityManager();
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Double> cq = cb.createQuery(Double.class);
		Root<Price> from = cq.from(Price.class);

		cq.select(from.get(Price_.basicPrice));
		cq.where(cb.equal(from.get(Price_.dataChange), fiendDatePrice(dateDeparture)));

		return em.createQuery(cq).getSingleResult();
	}

	@Override
	public List<Integer> getBasySeats(Flight flight, TicketClass ticketClass) {
		EntityManager em = getEntityManager();
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Integer> cq = cb.createQuery(Integer.class);
		Root<Ticket> from = cq.from(Ticket.class);

		cq.select(from.get(Ticket_.numberSeats));
		Predicate fFlight = cb.equal(from.get(Ticket_.flight), flight);
		Predicate fClass = cb.equal(from.get(Ticket_.ticketClass), ticketClass);
		cq.where(cb.and(fFlight, fClass));

		return em.createQuery(cq).getResultList();
	}

	@Override
	public Ticket getTicketWithFetch(Ticket ticket) {
		EntityManager em = getEntityManager();
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Ticket> cq = cb.createQuery(Ticket.class);
		Root<Ticket> from = cq.from(Ticket.class);

		cq.select(from);
		from.fetch(Ticket_.userProfile, JoinType.LEFT);
		cq.where(cb.equal(from, ticket));

		Ticket result = em.createQuery(cq).getSingleResult();
		return result;
	}

	@Override
	public void deleteDontPaidTicket() {	
		LocalDateTime ltdMinusDay = LocalDateTime.now().minusDays(3);
		Date deletDtae = Date.from(ltdMinusDay.atZone(ZoneId.systemDefault()).toInstant());

		getEntityManager().createQuery(String.format("delete from %s e where (e.dateBought < :delDate) and (paid = false)", Ticket.class.getSimpleName()))
		.setParameter("delDate", deletDtae).executeUpdate();

	}

	@Override
	public Boolean checkBusySeats(Integer numberSeats, TicketClass ticketClass, Flight flight) {
		EntityManager em = getEntityManager();
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Long> cq = cb.createQuery(Long.class);
		Root<Ticket> from = cq.from(Ticket.class);

		cq.select(cb.count(from.get(Ticket_.numberSeats)));

		Predicate fFlight = cb.equal(from.get(Ticket_.flight), flight);
		Predicate fSeats = cb.equal(from.get(Ticket_.numberSeats), numberSeats);
		Predicate fClass = cb.equal(from.get(Ticket_.ticketClass), ticketClass);
		cq.where(cb.and(fFlight, fSeats, fClass));
		Boolean result;
		if (em.createQuery(cq).getSingleResult() > 0) {
			result = false;
		} else {
			result = true;
		}
		return result;

	}

}
