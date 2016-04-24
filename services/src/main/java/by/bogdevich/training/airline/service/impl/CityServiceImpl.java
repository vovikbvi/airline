package by.bogdevich.training.airline.service.impl;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import by.bogdevich.training.airline.dataaccess.CityDao;
import by.bogdevich.training.airline.datamodel.City;
import by.bogdevich.training.airline.service.CityService;

@Service
public class CityServiceImpl implements CityService {

	@Inject
	CityDao cityDao;
	
	
@Override
public void update(City city){
	cityDao.update(city);
}	
	
	
}
