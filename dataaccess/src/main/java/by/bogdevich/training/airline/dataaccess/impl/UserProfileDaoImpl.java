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
public class UserProfileDaoImpl extends AbstractDaoImpl<UserProfile, Long, UserProfileFilter> implements UserProfileDao {

	protected UserProfileDaoImpl() {
		super(UserProfile.class);
	}

	// rename method
	@Override
	public Long countUserLogin(String login) {
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

		handleFilterParameters(filter, cb, cq, from);

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
		setPaging(filter, q);

		// set execute query
		List<UserProfile> allitems = q.getResultList();
		return allitems;
	}


	@Override
	public void handleFilterParameters(UserProfileFilter filter, CriteriaBuilder cb, CriteriaQuery<?> cq,
			Root<UserProfile> from) {
		if (filter.getFirstName() != null) {
			Predicate fName = cb.equal(from.get(UserProfile_.firstName), filter.getFirstName());
			Predicate lName = cb.equal(from.get(UserProfile_.lastName), filter.getFirstName());
			cq.where(cb.or(fName, lName));
		}
		if (filter.getLogin() !=null){
			cq.where(cb.equal(from.get(UserProfile_.login),filter.getLogin()));
		}
	}

	@Override
	public UserProfile findUser(String userName, String password) {
		EntityManager em = getEntityManager();
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<UserProfile> cq = cb.createQuery(UserProfile.class);
		Root<UserProfile> from = cq.from(UserProfile.class);

		cq.select(from);
		Predicate usernameEqualCondition = cb.equal(from.get(UserProfile_.login), userName);
		Predicate passwEqualCondition = cb.equal(from.get(UserProfile_.password), password);
		cq.where(cb.and(usernameEqualCondition, passwEqualCondition));

		TypedQuery<UserProfile> q = em.createQuery(cq);

		List<UserProfile> allitems = q.getResultList();

		if (allitems.isEmpty()) {
			return null;
		} else if (allitems.size() == 1) {
			return allitems.get(0);
		} else {
			throw new IllegalArgumentException("more than 1 user found ");
		}

	}
}
