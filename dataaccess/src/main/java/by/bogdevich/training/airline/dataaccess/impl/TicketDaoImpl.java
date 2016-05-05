package by.bogdevich.training.airline.dataaccess.impl;

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
import by.bogdevich.training.airline.datamodel.Flight_;
import by.bogdevich.training.airline.datamodel.ModelPlane_;
import by.bogdevich.training.airline.datamodel.Plane_;
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

		cq.select(from.get(Ticket_.flight).get(Flight_.plane).get(Plane_.modelPlane).get(ModelPlane_.colPassangersBuisnes));
		from.fetch(Ticket_.flight, JoinType.LEFT).fetch(Flight_.plane, JoinType.LEFT).fetch(Plane_.modelPlane,
				JoinType.LEFT);
		cq.where(cb.equal(from.get(Ticket_.id), 1));  
		// List<Ticket> allitems = em.createQuery(cq).getFirstResult();
		// return allitems;
		return em.createQuery(cq).getFirstResult();
		
	}
}
