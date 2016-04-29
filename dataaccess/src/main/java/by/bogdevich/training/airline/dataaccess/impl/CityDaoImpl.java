package by.bogdevich.training.airline.dataaccess.impl;

import org.springframework.stereotype.Repository;

import by.bogdevich.training.airline.dataaccess.CityDao;
import by.bogdevich.training.airline.datamodel.City;

@Repository
public class CityDaoImpl extends AbstractDaoImpl<City, Long> implements CityDao {

	protected CityDaoImpl() {
		super(City.class);
	}
	
	
	
}
