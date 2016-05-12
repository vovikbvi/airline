package by.bogdevich.training.airline.dataaccess.impl;

import java.util.List;
import javax.persistence.criteria.Predicate;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Root;

import org.hibernate.jpa.criteria.OrderImpl;
import org.springframework.stereotype.Repository;

import by.bogdevich.training.airline.dataaccess.PlaneDao;
import by.bogdevich.training.airline.dataaccess.filtres.PlaneFilter;
import by.bogdevich.training.airline.datamodel.Airport_;
import by.bogdevich.training.airline.datamodel.City_;
import by.bogdevich.training.airline.datamodel.FlightCatalog;
import by.bogdevich.training.airline.datamodel.FlightCatalog_;
import by.bogdevich.training.airline.datamodel.Plane;
import by.bogdevich.training.airline.datamodel.Plane_;


@Repository
public class PlaneDaoImpl extends AbstractDaoImpl<Plane, Long> implements PlaneDao {

	protected PlaneDaoImpl() {
		super(Plane.class);
	}

	
	@Override
	public List<Plane> getRecordsSorted(PlaneFilter filter) {
		EntityManager em = getEntityManager();
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Plane> cq = cb.createQuery(Plane.class);
		Root<Plane> from = cq.from(Plane.class);

		cq.select(from);

		if (filter.getBortNumber() != null) {
			Predicate bortNumber = cb.equal(from.get(Plane_.bortNumber), filter.getBortNumber());
			cq.where(bortNumber);
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

		TypedQuery<Plane> q = em.createQuery(cq);

		// set paging
		if (filter.getOffset() != null && filter.getLimit() != null) {
			q.setFirstResult(filter.getOffset());
			q.setMaxResults(filter.getLimit());
		}

		// set execute query
		List<Plane> allitems = q.getResultList();
		return allitems;
	}

	@Override
	public Plane getFullPlane(Plane plane) {
		EntityManager em = getEntityManager();
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Plane> cq = cb.createQuery(Plane.class);
		Root<Plane> from = cq.from(Plane.class);

		cq.select(from);
		from.fetch(Plane_.modelPlane);
		cq.where(cb.equal(from, plane));

		Plane result = em.createQuery(cq).getSingleResult();
		return result;
	}

}
