package by.bogdevich.training.airline.dataaccess.impl;

import java.time.LocalDateTime;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Root;
import org.hibernate.jpa.criteria.OrderImpl;
import org.springframework.stereotype.Repository;
import by.bogdevich.training.airline.dataaccess.TicketDao;
import by.bogdevich.training.airline.dataaccess.filtres.TicketFilter;
import by.bogdevich.training.airline.datamodel.Flight;
import by.bogdevich.training.airline.datamodel.FlightCatalog_;
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
	return em.createQuery(cq).getFirstResult();

	}

	@Override
	public List<Ticket> getRecordsSorted(TicketFilter filter) {
		EntityManager em = getEntityManager();
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Ticket> cq = cb.createQuery(Ticket.class);
		Root<Ticket> from = cq.from(Ticket.class);

		cq.select(from);

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
	public Double countAllBaggage (Flight flight) {
		EntityManager em = getEntityManager();
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Double> cq = cb.createQuery(Double.class);
		Root<Ticket> from = cq.from(Ticket.class);

		cq.select(cb.sum(from.get(Ticket_.weightBaggage)));
		cq.where(cb.equal(from.get(Ticket_.flight), flight));  
		Double result = em.createQuery(cq).getSingleResult();
		return result;

	}
	
	
	private LocalDateTime fiendDate (LocalDateTime dateDeparture){
		EntityManager em = getEntityManager();
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<LocalDateTime> cq = cb.createQuery(LocalDateTime.class);
		Root<Price> from = cq.from(Price.class);
		
		cq.select(cb.greatest(from.get(Price_.dataChange)));
		cq.where(cb.lessThan(from.get(Price_.dataChange), dateDeparture));
		return em.createQuery(cq).getSingleResult();
		
		
		
	}
    @Override
	public Double fiendBasicPrice (LocalDateTime dateDeparture){
		EntityManager em = getEntityManager();
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Double> cq = cb.createQuery(Double.class);
		Root<Price> from = cq.from(Price.class);

		
		cq.select(from.get(Price_.basicPrice));
		cq.where(cb.equal(from.get(Price_.dataChange), fiendDate(dateDeparture)));
        
	
		return em.createQuery(cq).getSingleResult();
	}
	

}
