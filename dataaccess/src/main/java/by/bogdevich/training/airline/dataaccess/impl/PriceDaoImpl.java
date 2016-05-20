package by.bogdevich.training.airline.dataaccess.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import org.hibernate.jpa.criteria.OrderImpl;
import org.springframework.stereotype.Repository;
import by.bogdevich.training.airline.dataaccess.PriceDao;
import by.bogdevich.training.airline.dataaccess.filtres.PriceFilter;
import by.bogdevich.training.airline.datamodel.Price;

@Repository
public class PriceDaoImpl extends AbstractDaoImpl<Price, Long> implements PriceDao {

	protected PriceDaoImpl() {
		super(Price.class);
	}

	@Override
	public List<Price> getRecordsSorted(PriceFilter filter) {
		EntityManager em = getEntityManager();
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Price> cq = cb.createQuery(Price.class);
		Root<Price> from = cq.from(Price.class);

		cq.select(from);

		// set sort params
		if (filter.getSortProperty() != null) {
			cq.orderBy(new OrderImpl(from.get(filter.getSortProperty()), filter.isSortOrder()));
		}

		TypedQuery<Price> q = em.createQuery(cq);

		// set paging
		if (filter.getOffset() != null && filter.getLimit() != null) {
			q.setFirstResult(filter.getOffset());
			q.setMaxResults(filter.getLimit());
		}

		// set execute query
		List<Price> allitems = q.getResultList();
		return allitems;
	}

	@Override
	public Long count(PriceFilter filter) {
		EntityManager em = getEntityManager();
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Long> cq = cb.createQuery(Long.class);
		Root<Price> from = cq.from(Price.class);
		cq.select(cb.count(from));
		TypedQuery<Long> q = em.createQuery(cq);
		return q.getSingleResult();
	}
}
