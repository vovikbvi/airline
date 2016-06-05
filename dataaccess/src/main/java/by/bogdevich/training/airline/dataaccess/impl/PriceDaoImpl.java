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
import by.bogdevich.training.airline.dataaccess.PriceDao;
import by.bogdevich.training.airline.dataaccess.filtres.PriceFilter;
import by.bogdevich.training.airline.dataaccess.filtres.UserProfileFilter;
import by.bogdevich.training.airline.datamodel.Price;
import by.bogdevich.training.airline.datamodel.UserProfile;
import by.bogdevich.training.airline.datamodel.UserProfile_;

@Repository
public class PriceDaoImpl extends AbstractDaoImpl<Price, Long, PriceFilter> implements PriceDao {

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
		setPaging(filter, q);
		
		// set execute query
		List<Price> allitems = q.getResultList();
		return allitems;
	}
	
	@Override
	public void handleFilterParameters(PriceFilter filter, CriteriaBuilder cb, CriteriaQuery<?> cq,
			Root<Price> from) {
		}
	

}
