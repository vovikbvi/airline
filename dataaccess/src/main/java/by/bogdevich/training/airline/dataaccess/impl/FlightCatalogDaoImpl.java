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

import by.bogdevich.training.airline.dataaccess.FlightCatalogDao;
import by.bogdevich.training.airline.dataaccess.filtres.FlightCatalogFilter;
import by.bogdevich.training.airline.dataaccess.filtres.TicketFilter;
import by.bogdevich.training.airline.datamodel.Airport;
import by.bogdevich.training.airline.datamodel.Airport_;
import by.bogdevich.training.airline.datamodel.City_;
import by.bogdevich.training.airline.datamodel.Country;
import by.bogdevich.training.airline.datamodel.Flight;
import by.bogdevich.training.airline.datamodel.FlightCatalog;
import by.bogdevich.training.airline.datamodel.FlightCatalog_;
import by.bogdevich.training.airline.datamodel.Flight_;


import by.bogdevich.training.airline.datamodel.Price_;
import by.bogdevich.training.airline.datamodel.Ticket;

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

		/*
		 * if (filter.getFirstName() != null) { Predicate fName =
		 * cb.equal(from.get(UserProfile_.firstName), filter.getFirstName());
		 * Predicate lName = cb.equal(from.get(UserProfile_.lastName),
		 * filter.getFirstName()); cq.where(cb.or(fName, lName)); }
		 */
		
		// set fetching
		if (filter.isFetchAirportStart()) {
			from.fetch(FlightCatalog_.airportStart, JoinType.LEFT);
		}
		if (filter.isFetchAirportFinish()) {
			from.fetch(FlightCatalog_.airportFinish, JoinType.LEFT);
		}

		// set sort params
		if (filter.getSortProperty() != null) {
			cq.orderBy(new OrderImpl(from.get(filter.getSortProperty()), filter.isSortOrder()));
		}

		TypedQuery<FlightCatalog> q = em.createQuery(cq);

		// set paging
		if (filter.getOffset() != null && filter.getLimit() != null) {
			q.setFirstResult(filter.getOffset());
			q.setMaxResults(filter.getLimit());
		}

		// set execute query
		List<FlightCatalog> allitems = q.getResultList();
		return allitems;
	}

	
/*	
	@Override
	public Country getCountry(Airport airport) {
		EntityManager em = getEntityManager();
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Country> cq = cb.createQuery(Country.class);
		Root<Country> from = cq.from(Country.class);

		cq.select(from);
		//cq.where(cb.isMember(elem, collection)(from.get(FlightCatalog_.airportStart), airport));

		return null; //em.createQuery(cq).getFirstResult();
	}
	*/

	@Override
	public FlightCatalog getFullFlightCatalog(FlightCatalog flightCatalog) {
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

	
}
