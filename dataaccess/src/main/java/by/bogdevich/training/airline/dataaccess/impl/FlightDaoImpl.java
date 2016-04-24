package by.bogdevich.training.airline.dataaccess.impl;

import org.springframework.stereotype.Repository;

import by.bogdevich.training.airline.dataaccess.FlightDao;
import by.bogdevich.training.airline.datamodel.Flight;

@Repository
public class FlightDaoImpl extends AbstractDaoImpl<Flight, Long> implements FlightDao {

	protected FlightDaoImpl() {
		super(Flight.class);
	}

}
