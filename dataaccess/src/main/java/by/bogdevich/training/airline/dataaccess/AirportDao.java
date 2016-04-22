package by.bogdevich.training.airline.dataaccess;

import by.bogdevich.training.airline.datamodel.Airport;

public interface AirportDao {

	Airport get (Long id);
	
	Airport save();
}
