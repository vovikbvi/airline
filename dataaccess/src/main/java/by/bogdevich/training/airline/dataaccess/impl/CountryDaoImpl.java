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
import by.bogdevich.training.airline.dataaccess.CountryDao;
import by.bogdevich.training.airline.dataaccess.filtres.CountryFilter;
import by.bogdevich.training.airline.datamodel.Country;
import by.bogdevich.training.airline.datamodel.Country_;


@Repository
public class CountryDaoImpl extends AbstractDaoImpl<Country, Long> implements CountryDao {

	protected CountryDaoImpl() {
		super(Country.class);
	}

	@Override
	public List<Country> getRecordsSorted(CountryFilter filter) {
		EntityManager em = getEntityManager();
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Country> cq = cb.createQuery(Country.class);
		Root<Country> from = cq.from(Country.class);

		cq.select(from);

		if (filter.getCountryName() != null) {
			Predicate name = cb.equal(from.get(Country_.name), filter.getCountryName());

			cq.where(name);
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

		TypedQuery<Country> q = em.createQuery(cq);

		// set paging
		if (filter.getOffset() != null && filter.getLimit() != null) {
			q.setFirstResult(filter.getOffset());
			q.setMaxResults(filter.getLimit());
		}

		// set execute query
		List<Country> allitems = q.getResultList();
		return allitems;
	}
	
	@Override
	public Long count(CountryFilter filter) {
		EntityManager em = getEntityManager();
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Long> cq = cb.createQuery(Long.class);
		Root<Country> from = cq.from(Country.class);
		cq.select(cb.count(from));
		TypedQuery<Long> q = em.createQuery(cq);
		return q.getSingleResult();
	}

	
}
