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
import by.bogdevich.training.airline.dataaccess.ModelPlaneDao;
import by.bogdevich.training.airline.dataaccess.filtres.ModelPlaneFilter;
import by.bogdevich.training.airline.datamodel.ManufacturedPlane_;
import by.bogdevich.training.airline.datamodel.ModelPlane;
import by.bogdevich.training.airline.datamodel.ModelPlane_;

@Repository
public class ModelPlaneDaoImpl extends AbstractDaoImpl<ModelPlane, Long> implements ModelPlaneDao {

	protected ModelPlaneDaoImpl() {
		super(ModelPlane.class);
	}

	@Override
	public List<ModelPlane> getRecordsSorted(ModelPlaneFilter filter) {
		EntityManager em = getEntityManager();
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<ModelPlane> cq = cb.createQuery(ModelPlane.class);
		Root<ModelPlane> from = cq.from(ModelPlane.class);

		cq.select(from);

		handleFilterParameters(filter, cb, cq, from);

		// set fetching
		if (filter.isSetFetchManufactured()) {
			from.fetch(ModelPlane_.manufacturedPlane, JoinType.LEFT);
		}

		// set sort params
		if (filter.getSortProperty() != null) {
			Path<Object> expression;
			if (ManufacturedPlane_.name.equals(filter.getSortProperty())) {
				expression = from.get(ModelPlane_.manufacturedPlane).get(filter.getSortProperty());
			} else {
				expression = from.get(filter.getSortProperty());
			}
			cq.orderBy(new OrderImpl(expression, filter.isSortOrder()));
		}

		
		TypedQuery<ModelPlane> q = em.createQuery(cq);

		// set paging
		setPaging(filter, q);

		// set execute query
		List<ModelPlane> allitems = q.getResultList();
		return allitems;
	}

	private void handleFilterParameters(ModelPlaneFilter filter, CriteriaBuilder cb, CriteriaQuery<?> cq,
			Root<ModelPlane> from) {
		if (filter.getModel() != null) {
			Predicate model = cb.equal(from.get(ModelPlane_.model), filter.getModel());
			cq.where(model);
		}
	}

	@Override
	public Long count(ModelPlaneFilter filter) {
		EntityManager em = getEntityManager();
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Long> cq = cb.createQuery(Long.class);
		Root<ModelPlane> from = cq.from(ModelPlane.class);
		cq.select(cb.count(from));

		handleFilterParameters(filter, cb, cq, from);
		TypedQuery<Long> q = em.createQuery(cq);
		return q.getSingleResult();
	}

}
