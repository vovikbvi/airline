package by.bogdevich.training.airline.dataaccess.impl;

import java.util.List;
import javax.persistence.criteria.Predicate;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Root;
import org.hibernate.jpa.criteria.OrderImpl;
import org.springframework.stereotype.Repository;
import by.bogdevich.training.airline.dataaccess.PlaneDao;
import by.bogdevich.training.airline.dataaccess.filtres.PlaneFilter;
import by.bogdevich.training.airline.datamodel.ModelPlane_;
import by.bogdevich.training.airline.datamodel.Plane;
import by.bogdevich.training.airline.datamodel.Plane_;

@Repository
public class PlaneDaoImpl extends AbstractDaoImpl<Plane, Long, PlaneFilter> implements PlaneDao {

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

		handleFilterParameters(filter, cb, cq, from);

		// set fetching
		if (filter.getFetchModelPlane()) {
			from.fetch(Plane_.modelPlane, JoinType.LEFT);
		}

		// set sort params
		if (filter.getSortProperty() != null) {
			Path<Object> expression;
			if (ModelPlane_.model.equals(filter.getSortProperty())) {
				expression = from.get(Plane_.modelPlane).get(filter.getSortProperty());
			} else {
				expression = from.get(filter.getSortProperty());
			}

			cq.orderBy(new OrderImpl(expression, filter.isSortOrder()));
		}

		TypedQuery<Plane> q = em.createQuery(cq);

		// set paging
		setPaging(filter, q);

		// set execute query
		List<Plane> allitems = q.getResultList();
		return allitems;
	}

	@Override
	public void handleFilterParameters(PlaneFilter filter, CriteriaBuilder cb, CriteriaQuery<?> cq, Root<Plane> from) {
		if (filter.getBortNumber() != null) {
			Predicate bortNumber = cb.equal(from.get(Plane_.bortNumber), filter.getBortNumber());
			cq.where(bortNumber);
		}
	}

	@Override
	public Plane getPlaneWithFetch(Plane plane) {
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
