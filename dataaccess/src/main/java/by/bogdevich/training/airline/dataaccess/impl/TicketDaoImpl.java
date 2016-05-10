package by.bogdevich.training.airline.dataaccess.impl;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.hibernate.jpa.criteria.OrderImpl;
import org.springframework.stereotype.Repository;

import by.bogdevich.training.airline.dataaccess.TicketDao;
import by.bogdevich.training.airline.dataaccess.filtres.TicketFilter;

import by.bogdevich.training.airline.datamodel.Flight;
import by.bogdevich.training.airline.datamodel.Flight_;
import by.bogdevich.training.airline.datamodel.ModelPlane_;
import by.bogdevich.training.airline.datamodel.Plane_;
import by.bogdevich.training.airline.datamodel.Price;
import by.bogdevich.training.airline.datamodel.Price_;
import by.bogdevich.training.airline.datamodel.Ticket;
import by.bogdevich.training.airline.datamodel.Ticket_;

@Repository
public class TicketDaoImpl extends AbstractDaoImpl<Ticket, Long> implements TicketDao {

	protected TicketDaoImpl() {
		super(Ticket.class);
	}

	public Integer getColPassBuisnes() {
		EntityManager em = getEntityManager();
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Integer> cq = cb.createQuery(Integer.class);
		Root<Ticket> from = cq.from(Ticket.class);

		cq.select(from.get(Ticket_.flight).get(Flight_.plane).get(Plane_.modelPlane)
				.get(ModelPlane_.colPassangersBuisnes));
		from.fetch(Ticket_.flight, JoinType.LEFT).fetch(Flight_.plane, JoinType.LEFT).fetch(Plane_.modelPlane,
				JoinType.LEFT);
		cq.where(cb.equal(from.get(Ticket_.id), 1));
		// List<Ticket> allitems = em.createQuery(cq).getFirstResult();
		// return allitems;
		return em.createQuery(cq).getFirstResult();

	}

	@Override
	public Integer getColPassangerB() {
		EntityManager em = getEntityManager();
		String qery = "SELECT m.col_passangers_buisnes FROM ticket t LEFT JOIN flight f on t.flight_id = f.id LEFT JOIN plane p on f.plane_id = p.id LEFT JOIN model_plane m on p.model_plane_id = m.id WHERE t.id = 1";
		Integer result = em.createNamedQuery(qery).getFirstResult();
		return result;
	}

	@Override
	public List<Ticket> getRecordsSorted(TicketFilter filter) {
		EntityManager em = getEntityManager();
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Ticket> cq = cb.createQuery(Ticket.class);
		Root<Ticket> from = cq.from(Ticket.class);

		cq.select(from);

		/*
		 * if (filter.getFirstName() != null) { Predicate fName =
		 * cb.equal(from.get(UserProfile_.firstName), filter.getFirstName());
		 * Predicate lName = cb.equal(from.get(UserProfile_.lastName),
		 * filter.getFirstName()); cq.where(cb.or(fName, lName)); }
		 * 
		 * // set fetching if (filter.isFetchCredentials()) {
		 * from.fetch(UserProfile_., JoinType.LEFT); }
		 */
		// set sort params

		if (filter.getSortProperty() != null) {
			cq.orderBy(new OrderImpl(from.get(filter.getSortProperty()), filter.isSortOrder()));
		}

		TypedQuery<Ticket> q = em.createQuery(cq);

		// set paging
		if (filter.getOffset() != null && filter.getLimit() != null) {
			q.setFirstResult(filter.getOffset());
			q.setMaxResults(filter.getLimit());
		}

		// set execute query
		List<Ticket> allitems = q.getResultList();
		return allitems;
	}

	@Override
	public Long countBusySeats(Flight flight) {
		EntityManager em = getEntityManager();
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Long> cq = cb.createQuery(Long.class);
		Root<Ticket> from = cq.from(Ticket.class);

		cq.select(cb.count(from.get(Ticket_.id)));
		cq.where(cb.equal(from.get(Ticket_.flight), flight));

		return em.createQuery(cq).getSingleResult();

	}
	
	@Override
	public Integer fiendDate(LocalDateTime dateDeparture){
		EntityManager em = getEntityManager();
		return
		em.createQuery("SELECT basicPrice FROM Price WHERE dataChange = "
				+ "(SELECT max(dataChange) FROM Price p WHERE dataChange < :dateDeparture)")
		        .setParameter(":dateDeparture", dateDeparture)
		        .getFirstResult();
		
		
	}
	
/*
	private Date fiendDateBasicPrice(Date dateDeparture){
		EntityManager em = getEntityManager();
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Number> cq = cb.createQuery(Number.class);
		Root<Price> from = cq.from(Price.class);
		
		cq.select(cb.max(from.get(Price_.dataChange)));
		cq.where(cb.equal(from.get(Ticket_.flight), flight));
	}
	
	public Long fiendBasicPrice (Date dateDeparture){
		EntityManager em = getEntityManager();
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Long> cq = cb.createQuery(Long.class);
		Root<Price> from = cq.from(Price.class);

		
		cq.select(cb.max(from.get(Price_.dataChange)));
		cq.where(cb.equal(from.get(Ticket_.flight), flight));
        
	
		return null;
	}
	
	*/
}
