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
import by.bogdevich.training.airline.dataaccess.ManufacturedPlaneDao;
import by.bogdevich.training.airline.dataaccess.filtres.ManufacturedPlaneFilter;
import by.bogdevich.training.airline.datamodel.ManufacturedPlane;
import by.bogdevich.training.airline.datamodel.ManufacturedPlane_;

@Repository
public class ManufacuredPlaneDaoImpl extends AbstractDaoImpl<ManufacturedPlane, Long, ManufacturedPlaneFilter> implements ManufacturedPlaneDao {

	protected ManufacuredPlaneDaoImpl() {
		super(ManufacturedPlane.class);
	}

	@Override
	public List<ManufacturedPlane> getRecordsSorted(ManufacturedPlaneFilter filter) {
		EntityManager em = getEntityManager();
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<ManufacturedPlane> cq = cb.createQuery(ManufacturedPlane.class);
		Root<ManufacturedPlane> from = cq.from(ManufacturedPlane.class);

		cq.select(from);

		handleFilterParameters(filter, cb, cq, from);

		// set sort params
		if (filter.getSortProperty() != null) {
			cq.orderBy(new OrderImpl(from.get(filter.getSortProperty()), filter.isSortOrder()));
		}

		TypedQuery<ManufacturedPlane> q = em.createQuery(cq);

		// set paging
		setPaging(filter, q);

		// set execute query
		List<ManufacturedPlane> allitems = q.getResultList();
		return allitems;
	}

	@Override
	public void handleFilterParameters(ManufacturedPlaneFilter filter, CriteriaBuilder cb, CriteriaQuery<?> cq,
			Root<ManufacturedPlane> from) {
		if (filter.getName() != null) {
			Predicate name = cb.equal(from.get(ManufacturedPlane_.name), filter.getName());
			cq.where(name);
		}
	}

}
