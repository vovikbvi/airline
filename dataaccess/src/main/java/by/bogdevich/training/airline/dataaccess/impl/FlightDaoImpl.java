package by.bogdevich.training.airline.dataaccess.impl;

import java.util.ArrayList;
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
import by.bogdevich.training.airline.dataaccess.FlightDao;
import by.bogdevich.training.airline.dataaccess.filtres.FlightFilter;
import by.bogdevich.training.airline.datamodel.Airport_;
import by.bogdevich.training.airline.datamodel.City_;
import by.bogdevich.training.airline.datamodel.Flight;
import by.bogdevich.training.airline.datamodel.FlightCatalog_;
import by.bogdevich.training.airline.datamodel.Flight_;
import by.bogdevich.training.airline.datamodel.Plane_;

@Repository
public class FlightDaoImpl extends AbstractDaoImpl<Flight, Long> implements FlightDao {

	protected FlightDaoImpl() {
		super(Flight.class);
	}

	@Override
	public List<Flight> getRecordsSorted(FlightFilter filter) {
		EntityManager em = getEntityManager();
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Flight> cq = cb.createQuery(Flight.class);
		Root<Flight> from = cq.from(Flight.class);

		cq.select(from);

		handleFilterParameters(filter, cb, cq, from);

		if (filter.isFetchFlieghtCatalog()) {
			from.fetch(Flight_.flightCatalog, JoinType.LEFT).fetch(FlightCatalog_.airportStart, JoinType.LEFT);
			from.fetch(Flight_.flightCatalog, JoinType.LEFT).fetch(FlightCatalog_.airportFinish, JoinType.LEFT);
		}

		if (filter.isFetchPlane()) {
			from.fetch(Flight_.plane, JoinType.LEFT);
		}

		// set sort params
		if (filter.getSortProperty() != null) {
			Path<Object> expression;
			if (FlightCatalog_.id.equals(filter.getSortProperty())) {
				expression = from.get(Flight_.flightCatalog).get(filter.getSortProperty());
			} else if (Plane_.bortNumber.equals(filter.getSortProperty())) {
				expression = from.get(Flight_.plane).get(filter.getSortProperty());
			} else {
				expression = from.get(filter.getSortProperty());
			}
			cq.orderBy(new OrderImpl(expression, filter.isSortOrder()));
		}

		TypedQuery<Flight> q = em.createQuery(cq);

		// set paging
		setPaging(filter, q);

		// set execute query
		List<Flight> allitems = q.getResultList();
		return allitems;
	}

	private void handleFilterParameters(FlightFilter filter, CriteriaBuilder cb, CriteriaQuery<?> cq,
			Root<Flight> from) {

		List<Predicate> predicates = new ArrayList<Predicate>();

		if (filter.getStartDepartureTime() != null) {
			predicates.add(cb.greaterThanOrEqualTo(from.get(Flight_.departureTime), filter.getStartDepartureTime()));
		}

		if (filter.getFinishDepartureTime() != null) {
			predicates.add(cb.lessThanOrEqualTo(from.get(Flight_.departureTime), filter.getFinishDepartureTime()));
		}

		if (filter.getCityStart() != null) {
			predicates.add(cb.equal(
					from.get(Flight_.flightCatalog).get(FlightCatalog_.airportStart).get(Airport_.city).get(City_.name),
					filter.getCityStart()));
		}

		if (filter.getCityFinish() != null) {
			predicates.add(cb.equal(from.get(Flight_.flightCatalog).get(FlightCatalog_.airportFinish).get(Airport_.city)
					.get(City_.name), filter.getCityFinish()));
		}

		cq.where(cb.and(predicates.toArray(new Predicate[] {})));

	}

	@Override
	public Flight getFlieghtWithFetch(Flight flight) {
		EntityManager em = getEntityManager();
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Flight> cq = cb.createQuery(Flight.class);
		Root<Flight> from = cq.from(Flight.class);

		cq.select(from);
		from.fetch(Flight_.plane, JoinType.LEFT).fetch(Plane_.modelPlane, JoinType.LEFT);
		from.fetch(Flight_.flightCatalog, JoinType.LEFT).fetch(FlightCatalog_.airportStart);
		from.fetch(Flight_.flightCatalog, JoinType.LEFT).fetch(FlightCatalog_.airportFinish);

		cq.where(cb.equal(from, flight));

		Flight result = em.createQuery(cq).getSingleResult();
		return result;
	}

	@Override
	public Long count(FlightFilter filter) {
		EntityManager em = getEntityManager();
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Long> cq = cb.createQuery(Long.class);
		Root<Flight> from = cq.from(Flight.class);

		handleFilterParameters(filter, cb, cq, from);
		cq.select(cb.count(from));
		TypedQuery<Long> q = em.createQuery(cq);
		return q.getSingleResult();
	}

	/*
	 * @Override public Integer getColPassangersBuisnes(Flight flight) {
	 * EntityManager em = getEntityManager(); CriteriaBuilder cb =
	 * em.getCriteriaBuilder(); CriteriaQuery<Integer> cq =
	 * cb.createQuery(Integer.class); Root<ModelPlane> from =
	 * cq.from(ModelPlane.class);
	 * 
	 * cq.select(from.get(ModelPlane_.colPassangersBuisnes));
	 * //(Flight_.plane).get(Plane_.modelPlane).get
	 * 
	 * Predicate qq = cb.isMember(from.get(ModelPlane_.variantModel),
	 * flight.getPlane());
	 * 
	 * 
	 * //Predicate qe = cb.equal(from.get(ModelPlane_.variantModel),
	 * from.get(Plane_.id)); // Predicate re = cb.equal(from.get(Plane_.id), 1);
	 * 
	 * //cq.where(qq);
	 * 
	 * Integer result = em.createQuery(cq).getFirstResult();
	 * 
	 * return result; }
	 */

}
