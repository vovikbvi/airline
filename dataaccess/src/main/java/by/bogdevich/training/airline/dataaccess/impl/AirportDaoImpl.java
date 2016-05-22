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
import by.bogdevich.training.airline.dataaccess.AirportDao;
import by.bogdevich.training.airline.dataaccess.filtres.AirportFilter;
import by.bogdevich.training.airline.datamodel.Airport;
import by.bogdevich.training.airline.datamodel.Airport_;
import by.bogdevich.training.airline.datamodel.City_;


@Repository
public class AirportDaoImpl extends AbstractDaoImpl<Airport, Long> implements AirportDao{

	protected AirportDaoImpl() {
		super(Airport.class);
	}

	@Override
	public List<Airport> getRecordsSorted(AirportFilter filter) {
		EntityManager em = getEntityManager();
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Airport> cq = cb.createQuery(Airport.class);
		Root<Airport> from = cq.from(Airport.class);

		cq.select(from);

		handleFilterParameters(filter, cb, cq, from);
	
		// set fetching
		if (filter.isSetFetchCity()) {
			from.fetch(Airport_.city, JoinType.LEFT);
		}

		// set sort params
		if (filter.getSortProperty() != null) {
			cq.orderBy(new OrderImpl(from.get(filter.getSortProperty()), filter.isSortOrder()));
		}

		TypedQuery<Airport> q = em.createQuery(cq);

		// set paging
		setPaging(filter, q);
		
		// set execute query
		List<Airport> allitems = q.getResultList();
		return allitems;
	}

	private void handleFilterParameters(AirportFilter filter, CriteriaBuilder cb, CriteriaQuery<?> cq,
			Root<Airport> from) {
		if (filter.getAirportName() != null) {
			Predicate aName = cb.equal(from.get(Airport_.name), filter.getAirportName());
			cq.where(aName);
		}
	}
	
	@Override
	public Airport getFullAirport(Airport airport) {
		EntityManager em = getEntityManager();
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Airport> cq = cb.createQuery(Airport.class);
		Root<Airport> from = cq.from(Airport.class);

		cq.select(from);
		from.fetch(Airport_.city, JoinType.LEFT).fetch(City_.country);
		cq.where(cb.equal(from, airport));

		Airport result = em.createQuery(cq).getSingleResult();
		return result;
	}
	
	@Override
	public Long count(AirportFilter filter) {
		EntityManager em = getEntityManager();
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Long> cq = cb.createQuery(Long.class);
		Root<Airport> from = cq.from(Airport.class);
		cq.select(cb.count(from));
		
		handleFilterParameters(filter, cb, cq, from);
		TypedQuery<Long> q = em.createQuery(cq);
		return q.getSingleResult();
	}

}
