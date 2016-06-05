package by.bogdevich.training.airline.dataaccess;

import java.util.List;

import by.bogdevich.training.airline.dataaccess.filtres.FlightFilter;
import by.bogdevich.training.airline.datamodel.Flight;

public interface FlightDao extends AbstractDao<Flight, Long, FlightFilter> {



	List<Flight> getRecordsSorted(FlightFilter filter);

	Flight getFlieghtWithFetch(Flight flight);

}
