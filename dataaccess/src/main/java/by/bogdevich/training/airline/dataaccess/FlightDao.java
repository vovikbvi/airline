package by.bogdevich.training.airline.dataaccess;

import java.util.List;

import by.bogdevich.training.airline.dataaccess.filtres.FlightFilter;
import by.bogdevich.training.airline.datamodel.Flight;

public interface FlightDao extends AbstractDao<Flight, Long> {

	public Flight getFullFlieght(Flight flight);

	List<Flight> getRecordsSorted(FlightFilter filter);

//	Integer getColPassangersBuisnes(Flight flight);
}
