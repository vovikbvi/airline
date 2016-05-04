package by.bogdevich.training.airline.dataaccess.impl;

import java.util.List;

import javax.persistence.EntityManager;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Root;
import org.springframework.stereotype.Repository;
import by.bogdevich.training.airline.dataaccess.FlightDao;
import by.bogdevich.training.airline.datamodel.ClassWeight;
import by.bogdevich.training.airline.datamodel.Flight;
import by.bogdevich.training.airline.datamodel.Flight_;
import by.bogdevich.training.airline.datamodel.ModelPlane_;
import by.bogdevich.training.airline.datamodel.Plane_;

@Repository
public class FlightDaoImpl extends AbstractDaoImpl<Flight, Long> implements FlightDao {

	protected FlightDaoImpl() {
		super(Flight.class);
	}

	@Override
	public Integer getColPassangersBuisnes(Flight flight) {
		EntityManager em = getEntityManager();
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Integer> cq = cb.createQuery(Integer.class);
		Root<Flight> from = cq.from(Flight.class);

		cq.select(from.get(Flight_.plane).get(Plane_.modelPlane).get(ModelPlane_.colPassangersBuisnes));
		cq.where(cb.equal(from.get(Flight_.id), flight.getId()));  

		return em.createQuery(cq).getFirstResult();
	}

}
