package by.bogdevich.training.airline.dataaccess.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import by.bogdevich.training.airline.dataaccess.AbstractDao;
import by.bogdevich.training.airline.dataaccess.filtres.AbstractFilter;
import by.bogdevich.training.airline.dataaccess.filtres.AirportFilter;
import by.bogdevich.training.airline.datamodel.AbstractModel;
import by.bogdevich.training.airline.datamodel.Airport;

public abstract class AbstractDaoImpl<T, ID, F> implements AbstractDao<T, ID, F> {

	@PersistenceContext
	private EntityManager entityManager;

	private final Class<T> entityClass;

	protected AbstractDaoImpl(final Class<T> entityClass) {
		this.entityClass = entityClass;
	}

	@Override
	public List<T> getAll() {
		final CriteriaQuery<T> query = entityManager.getCriteriaBuilder().createQuery(getEntityClass());
		query.from(getEntityClass());
		final List<T> lst = entityManager.createQuery(query).getResultList();
		return lst;
	}

	@Override
	public T get(final ID id) {
		return entityManager.find(getEntityClass(), id);
	}

	@Override
	public T insert(final T entity) {
		entityManager.persist(entity);
		return entity;
	}

	@Override
	public T update(T entity) {
		entity = entityManager.merge(entity);
		entityManager.flush();
		return entity;
	}

	@Override
	public void delete(ID id) {
		
			entityManager.createQuery(String.format("delete from %s e where e.id = :id", entityClass.getSimpleName()))
			.setParameter("id", id).executeUpdate();
	}
	
	public Class<T> getEntityClass() {
		return entityClass;
	}

	protected EntityManager getEntityManager() {
		return entityManager;
	}

    protected void setPaging(AbstractFilter filter, TypedQuery<? extends AbstractModel> q) {
        if (filter.getOffset() != null && filter.getLimit() != null) {
            q.setFirstResult(filter.getOffset());
            q.setMaxResults(filter.getLimit());
        }
    }

    abstract void handleFilterParameters(F filter, CriteriaBuilder cb, CriteriaQuery<?> cq, Root<T> from);
    
	@Override
	public Long count(F filter) {
		
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<Long> cq = cb.createQuery(Long.class);
		Root<T> from = cq.from(entityClass);
		cq.select(cb.count(from));
		
		handleFilterParameters(filter, cb, cq, from);
		TypedQuery<Long> q = entityManager.createQuery(cq);
		Long result = q.getSingleResult();
		System.out.println("!!!!!!!!!!!!!!!!!!!!!!"+ result +"!!!!!!!!!!!!!!!!!!!!!");
		return result;
	}
}
