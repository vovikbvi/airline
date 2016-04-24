package by.bogdevich.training.airline.dataaccess.impl;

import org.springframework.stereotype.Repository;

import by.bogdevich.training.airline.dataaccess.PlaneDao;
import by.bogdevich.training.airline.datamodel.Plane;

@Repository
public class PlaneDaoImpl extends AbstractDaoImpl<Plane, Long> implements PlaneDao {

	protected PlaneDaoImpl() {
		super(Plane.class);
	}

}
