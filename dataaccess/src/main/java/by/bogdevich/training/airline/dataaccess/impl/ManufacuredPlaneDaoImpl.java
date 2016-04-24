package by.bogdevich.training.airline.dataaccess.impl;


import by.bogdevich.training.airline.dataaccess.ManufacturedPlaneDao;
import by.bogdevich.training.airline.datamodel.ManufacturedPlane;

public class ManufacuredPlaneDaoImpl extends AbstractDaoImpl<ManufacturedPlane, Long> implements ManufacturedPlaneDao{

	protected ManufacuredPlaneDaoImpl() {
		super(ManufacturedPlane.class);
	}

}
