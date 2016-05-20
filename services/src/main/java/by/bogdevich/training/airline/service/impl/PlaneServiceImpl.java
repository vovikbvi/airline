package by.bogdevich.training.airline.service.impl;

import java.util.List;
import javax.inject.Inject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import by.bogdevich.training.airline.dataaccess.PlaneDao;
import by.bogdevich.training.airline.dataaccess.filtres.PlaneFilter;
import by.bogdevich.training.airline.datamodel.Plane;
import by.bogdevich.training.airline.service.PlaneService;

@Service
public class PlaneServiceImpl implements PlaneService {
	private static Logger LOGGER = LoggerFactory.getLogger(CityServiceImpl.class);

	@Inject
	PlaneDao planeDao;

	@Override
	public void insert(Plane plane) {
		planeDao.insert(plane);

		LOGGER.info("Insert plane {}", plane);
	}

	@Override
	public void update(Plane plane) {
		planeDao.update(plane);

		LOGGER.info("Update plane {}", plane);
	}

	@Override
	public void delete(Long id) {
		Plane plane = planeDao.get(id);
		planeDao.delete(id);
		LOGGER.info("Delete plane {}", plane);
	}

	@Override
	public Plane get(Long id) {
		return planeDao.get(id);
	}

	@Override
	public List<Plane> getAll() {
		return planeDao.getAll();
	}
	
	@Override
	public Long count(PlaneFilter filter){
		return planeDao.count(filter);
	}	

}
