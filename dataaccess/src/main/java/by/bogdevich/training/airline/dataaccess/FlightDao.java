package by.bogdevich.training.airline.dataaccess;

import by.bogdevich.training.airline.datamodel.Flight;

public interface FlightDao extends AbstractDao<Flight, Long> {

	public Integer getColPassangersBuisnes(Flight flight);
}
