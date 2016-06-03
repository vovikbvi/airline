package by.bogdevich.training.airline.dataaccess.impl;

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
import by.bogdevich.training.airline.dataaccess.FlightCatalogDao;
import by.bogdevich.training.airline.dataaccess.filtres.FlightCatalogFilter;
import by.bogdevich.training.airline.datamodel.Airport_;
import by.bogdevich.training.airline.datamodel.City_;
import by.bogdevich.training.airline.datamodel.FlightCatalog;
import by.bogdevich.training.airline.datamodel.FlightCatalog_;

@Repository
public class FlightCatalogDaoImpl extends AbstractDaoImpl<FlightCatalog, Long> implements FlightCatalogDao {

	protected FlightCatalogDaoImpl() {
		super(FlightCatalog.class);
	}

	@Override
	public List<FlightCatalog> getRecordsSorted(FlightCatalogFilter filter) {
		EntityManager em = getEntityManager();
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<FlightCatalog> cq = cb.createQuery(FlightCatalog.class);
		Root<FlightCatalog> from = cq.from(FlightCatalog.class);

		cq.select(from);

		handleFilterParameters(filter, cb, cq, from);

		// set fetching
		if (filter.isFetchAirportStart()) {
			from.fetch(FlightCatalog_.airportStart, JoinType.LEFT);
		}
		if (filter.isFetchAirportFinish()) {
			from.fetch(FlightCatalog_.airportFinish, JoinType.LEFT);
		}

		// set sort params
		if (filter.getSortProperty() != null) {
			Path<Object> expression;
			if (Airport_.name.equals(filter.getSortProperty())) {
				expression = from.get(FlightCatalog_.airportStart).get(filter.getSortProperty());
			} else {
				expression = from.get(filter.getSortProperty());
			}

			cq.orderBy(new OrderImpl(expression, filter.isSortOrder()));
		}

		TypedQuery<FlightCatalog> q = em.createQuery(cq);

		// set paging
		setPaging(filter, q);

		// set execute query
		List<FlightCatalog> allitems = q.getResultList();
		return allitems;
	}

	@Override
	public FlightCatalog getFlightCatalogWithFetch(FlightCatalog flightCatalog) {
		EntityManager em = getEntityManager();
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<FlightCatalog> cq = cb.createQuery(FlightCatalog.class);
		Root<FlightCatalog> from = cq.from(FlightCatalog.class);

		cq.select(from);
		from.fetch(FlightCatalog_.airportStart).fetch(Airport_.city, JoinType.LEFT).fetch(City_.country);
		from.fetch(FlightCatalog_.airportFinish).fetch(Airport_.city, JoinType.LEFT).fetch(City_.country);
		cq.where(cb.equal(from, flightCatalog));

		FlightCatalog result = em.createQuery(cq).getSingleResult();
		return result;
	}

	@Override
	public Long count(FlightCatalogFilter filter) {
		EntityManager em = getEntityManager();
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Long> cq = cb.createQuery(Long.class);
		Root<FlightCatalog> from = cq.from(FlightCatalog.class);
		cq.select(cb.count(from));

		handleFilterParameters(filter, cb, cq, from);
		TypedQuery<Long> q = em.createQuery(cq);
		return q.getSingleResult();
	}

	private void handleFilterParameters(FlightCatalogFilter filter, CriteriaBuilder cb, CriteriaQuery<?> cq,
			Root<FlightCatalog> from) {
		if (filter.getCityStart() != null && filter.getCityStart() != null) {
			Predicate fName = cb.equal(from.get(FlightCatalog_.airportStart).get(Airport_.city).get(City_.name),
					filter.getCityStart());
			cq.where(cb.and(fName));
		}
	}

}
