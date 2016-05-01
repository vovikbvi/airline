package by.bogdevich.training.airline.dataaccess.impl;


import org.springframework.stereotype.Repository;

import by.bogdevich.training.airline.dataaccess.ManufacturedPlaneDao;
import by.bogdevich.training.airline.datamodel.ManufacturedPlane;

@Repository
public class ManufacuredPlaneDaoImpl extends AbstractDaoImpl<ManufacturedPlane, Long> implements ManufacturedPlaneDao{

	protected ManufacuredPlaneDaoImpl() {
		super(ManufacturedPlane.class);
	}

}
