package by.bogdevich.training.airline.service.impl;

import java.util.List;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import by.bogdevich.training.airline.dataaccess.ManufacturedPlaneDao;
import by.bogdevich.training.airline.datamodel.ManufacturedPlane;
import by.bogdevich.training.airline.service.ManufacturedPlainService;

@Service
public class ManufacturedPlainServiceImpl implements ManufacturedPlainService {
	private static Logger LOGGER = LoggerFactory.getLogger(CityServiceImpl.class);

	@Inject
	ManufacturedPlaneDao manufacturedPlaneDao;


	@Override
	public void update(ManufacturedPlane manufacturedPlane) {
		manufacturedPlaneDao.update(manufacturedPlane);
		LOGGER.info("Update manufactured plane {}", manufacturedPlane);
	}


	@Override
	public void insert(ManufacturedPlane manufacturedPlane) {
		manufacturedPlaneDao.insert(manufacturedPlane);
		LOGGER.info("Insert manufactured plane {}", manufacturedPlane);
	}


	@Override
	public void delete(Long id) {
		ManufacturedPlane manufacturedPlane = manufacturedPlaneDao.get(id);
		manufacturedPlaneDao.delete(id);
		LOGGER.info("Delete manufactured plane {}", manufacturedPlane);
	}


	@Override
	public ManufacturedPlane get(Long id) {
		return manufacturedPlaneDao.get(id);
	}


	@Override
	public List<ManufacturedPlane> getAll() {
		return manufacturedPlaneDao.getAll();
	}

}
