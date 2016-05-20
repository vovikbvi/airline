package by.bogdevich.training.airline.service.impl;

import java.util.List;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import by.bogdevich.training.airline.dataaccess.ModelPlaneDao;
import by.bogdevich.training.airline.dataaccess.filtres.ModelPlaneFilter;
import by.bogdevich.training.airline.datamodel.ModelPlane;
import by.bogdevich.training.airline.service.ModelPlaneService;

@Service
public class ModelPlaneServiceImpl implements ModelPlaneService {
	private static Logger LOGGER = LoggerFactory.getLogger(CityServiceImpl.class);

	@Inject
	ModelPlaneDao modelPlaneDao;

	@Override
	public void insert(ModelPlane modelPlane) {
		modelPlaneDao.insert(modelPlane);

		LOGGER.info("Insert model plane {}", modelPlane);
	}

	@Override
	public void update(ModelPlane modelPlane) {
		modelPlaneDao.update(modelPlane);

		LOGGER.info("Update model plane {}", modelPlane);
	}

	@Override
	public void delete(Long id) {
		ModelPlane modelPlane = modelPlaneDao.get(id);
		modelPlaneDao.delete(id);
		LOGGER.info("Delete model plane {}", modelPlane);
	}

	@Override
	public ModelPlane get(Long id) {
		return modelPlaneDao.get(id);
	}

	@Override
	public List<ModelPlane> getAll() {
		return modelPlaneDao.getAll();
	}
	
	@Override
	public Long count(ModelPlaneFilter filter){
		return modelPlaneDao.count(filter);
	}	

}
