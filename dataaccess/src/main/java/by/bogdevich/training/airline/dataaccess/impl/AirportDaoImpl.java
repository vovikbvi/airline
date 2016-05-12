package by.bogdevich.training.airline.dataaccess.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.hibernate.jpa.criteria.OrderImpl;
import org.springframework.stereotype.Repository;

import by.bogdevich.training.airline.dataaccess.AirportDao;
import by.bogdevich.training.airline.dataaccess.filtres.AirportFilter;
import by.bogdevich.training.airline.datamodel.Airport;
import by.bogdevich.training.airline.datamodel.Airport_;


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

		if (filter.getAirportName() != null) {
			Predicate aName = cb.equal(from.get(Airport_.name), filter.getAirportName());
			cq.where(aName);
		}
	/*	
		// set fetching
		if (filter.isFetchCredentials()) {
			from.fetch(UserProfile_., JoinType.LEFT);
		}
*/
		// set sort params
		if (filter.getSortProperty() != null) {
			cq.orderBy(new OrderImpl(from.get(filter.getSortProperty()), filter.isSortOrder()));
		}

		TypedQuery<Airport> q = em.createQuery(cq);

		// set paging
		if (filter.getOffset() != null && filter.getLimit() != null) {
			q.setFirstResult(filter.getOffset());
			q.setMaxResults(filter.getLimit());
		}

		// set execute query
		List<Airport> allitems = q.getResultList();
		return allitems;
	}

}
