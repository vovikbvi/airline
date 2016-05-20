package by.bogdevich.training.airline.dataaccess.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import org.hibernate.jpa.criteria.OrderImpl;
import org.springframework.stereotype.Repository;
import by.bogdevich.training.airline.dataaccess.UserProfileDao;
import by.bogdevich.training.airline.dataaccess.filtres.UserProfileFilter;
import by.bogdevich.training.airline.datamodel.UserProfile;
import by.bogdevich.training.airline.datamodel.UserProfile_;


@Repository
public class UserProfileDaoImpl extends AbstractDaoImpl<UserProfile, Long> implements UserProfileDao {

	protected UserProfileDaoImpl() {
		super(UserProfile.class);
	}

	// rename method
	@Override
	public Long countLogin(String login) {
		EntityManager em = getEntityManager();
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Long> cq = cb.createQuery(Long.class);
		Root<UserProfile> from = cq.from(UserProfile.class);

		cq.select(cb.count(from.get(UserProfile_.login)));
		cq.where(cb.equal(from.get(UserProfile_.login), login));

		return em.createQuery(cq).getSingleResult();

	}

	@Override
	public List<UserProfile> getRecordsSorted(UserProfileFilter filter) {
		EntityManager em = getEntityManager();
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<UserProfile> cq = cb.createQuery(UserProfile.class);
		Root<UserProfile> from = cq.from(UserProfile.class);

		cq.select(from);

		if (filter.getFirstName() != null) {
			Predicate fName = cb.equal(from.get(UserProfile_.firstName), filter.getFirstName());
			Predicate lName = cb.equal(from.get(UserProfile_.lastName), filter.getFirstName());
			cq.where(cb.or(fName, lName));
		}

		// set fetching
		if (filter.isSetFetchTickets()) {
			from.fetch(UserProfile_.boughtTickets, JoinType.LEFT);
		}

		// set sort params
		if (filter.getSortProperty() != null) {
			cq.orderBy(new OrderImpl(from.get(filter.getSortProperty()), filter.isSortOrder()));
		}

		TypedQuery<UserProfile> q = em.createQuery(cq);

		// set paging
		if (filter.getOffset() != null && filter.getLimit() != null) {
			q.setFirstResult(filter.getOffset());
			q.setMaxResults(filter.getLimit());
		}

		// set execute query
		List<UserProfile> allitems = q.getResultList();
		return allitems;
	}

	@Override
	public Long count(UserProfileFilter filter) {
		EntityManager em = getEntityManager();
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Long> cq = cb.createQuery(Long.class);
		Root<UserProfile> from = cq.from(UserProfile.class);
		cq.select(cb.count(from));
		TypedQuery<Long> q = em.createQuery(cq);
		return q.getSingleResult();
	}
	
}