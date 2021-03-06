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
import by.bogdevich.training.airline.dataaccess.CityDao;
import by.bogdevich.training.airline.dataaccess.filtres.CityFilter;
import by.bogdevich.training.airline.datamodel.City;
import by.bogdevich.training.airline.datamodel.City_;
import by.bogdevich.training.airline.datamodel.Country_;
import by.bogdevich.training.airline.datamodel.FlightCatalog_;

@Repository
public class CityDaoImpl extends AbstractDaoImpl<City, Long, CityFilter> implements CityDao {

	protected CityDaoImpl() {
		super(City.class);
	}
	
	@Override
	public List<City> getRecordsSorted(CityFilter filter) {
		EntityManager em = getEntityManager();
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<City> cq = cb.createQuery(City.class);
		Root<City> from = cq.from(City.class);

		cq.select(from);

		handleFilterParameters(filter, cb, cq, from);
		
		// set fetching
		if (filter.isFetchCountry()) {
			from.fetch(City_.country, JoinType.LEFT);
		}

		// set sort params
		if (filter.getSortProperty() != null) {
			Path<Object> expression;
	          if (Country_.name.equals(filter.getSortProperty())) {
	                expression = from.get(City_.country).get(filter.getSortProperty());
	            } else {
	                expression = from.get(filter.getSortProperty());
	            }
	 
			cq.orderBy(new OrderImpl(expression, filter.isSortOrder()));
		}

		TypedQuery<City> q = em.createQuery(cq);

		// set paging
		setPaging(filter, q);
		
		// set execute query
		List<City> allitems = q.getResultList();
		return allitems;
	}

	@Override
	public void handleFilterParameters(CityFilter filter, CriteriaBuilder cb, CriteriaQuery<?> cq,
			Root<City> from) {
		if (filter.getCityName() != null) {
			Predicate cName = cb.equal(from.get(City_.name), filter.getCityName());
			cq.where(cName);
		}
	}
	
	
	
}
