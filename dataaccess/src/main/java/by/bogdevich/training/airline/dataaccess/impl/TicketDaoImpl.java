package by.bogdevich.training.airline.dataaccess.impl;

import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import org.hibernate.jpa.criteria.OrderImpl;
import org.springframework.stereotype.Repository;
import by.bogdevich.training.airline.dataaccess.TicketDao;
import by.bogdevich.training.airline.dataaccess.filtres.TicketFilter;
import by.bogdevich.training.airline.dataaccess.filtres.UserProfileFilter;
import by.bogdevich.training.airline.datamodel.Flight;
import by.bogdevich.training.airline.datamodel.ModelPlane;
import by.bogdevich.training.airline.datamodel.ModelPlane_;
import by.bogdevich.training.airline.datamodel.Price;
import by.bogdevich.training.airline.datamodel.Price_;
import by.bogdevich.training.airline.datamodel.Ticket;
import by.bogdevich.training.airline.datamodel.Ticket_;
import by.bogdevich.training.airline.datamodel.UserProfile;
import by.bogdevich.training.airline.datamodel.UserProfile_;

@Repository
public class TicketDaoImpl extends AbstractDaoImpl<Ticket, Long, TicketFilter> implements TicketDao {

	protected TicketDaoImpl() {
		super(Ticket.class);
	}

	@Override
	public Integer getColPassBuisnes() {
		EntityManager em = getEntityManager();
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Integer> cq = cb.createQuery(Integer.class);
		Root<ModelPlane> from = cq.from(ModelPlane.class);

		cq.select(from.get(ModelPlane_.colPassangersBuisnes));
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

		handleFilterParameters(filter, cb, cq, from);

		if (filter.getFetchFlieght()) {
			from.fetch(Ticket_.flight, JoinType.LEFT);
		}

		if (filter.getFetchUser()) {
			from.fetch(Ticket_.userProfile, JoinType.LEFT);
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
	public Double countAllBaggage(Flight flight) {
		EntityManager em = getEntityManager();
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Double> cq = cb.createQuery(Double.class);
		Root<Ticket> from = cq.from(Ticket.class);

		cq.select(cb.sum(from.get(Ticket_.weightBaggage)));
		cq.where(cb.equal(from.get(Ticket_.flight), flight));
		Double result = em.createQuery(cq).getSingleResult();
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

}
