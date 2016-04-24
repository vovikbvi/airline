package by.bogdevich.training.airline.dataaccess.impl;

import org.springframework.stereotype.Repository;

import by.bogdevich.training.airline.dataaccess.AirportDao;
import by.bogdevich.training.airline.datamodel.Airport;

@Repository
public class AirportDaoImpl extends AbstractDaoImpl<Airport, Long> implements AirportDao{

	protected AirportDaoImpl() {
		super(Airport.class);
	}


}
