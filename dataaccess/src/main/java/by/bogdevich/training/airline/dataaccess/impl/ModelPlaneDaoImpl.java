package by.bogdevich.training.airline.dataaccess.impl;

import org.springframework.stereotype.Repository;

import by.bogdevich.training.airline.dataaccess.ModelPlaneDao;

import by.bogdevich.training.airline.datamodel.ModelPlane;

@Repository
public class ModelPlaneDaoImpl extends AbstractDaoImpl<ModelPlane, Long> implements ModelPlaneDao {

	protected ModelPlaneDaoImpl() {
		super(ModelPlane.class);
	}

}
