package by.bogdevich.training.airline.dataaccess;

import java.util.List;

import by.bogdevich.training.airline.dataaccess.filtres.AirportFilter;
import by.bogdevich.training.airline.datamodel.Airport;

public interface AirportDao extends AbstractDao<Airport, Long>{

	List<Airport> getRecordsSorted(AirportFilter filter);

	Airport getAirportWithFetch(Airport airport);

	Long count(AirportFilter filter);


}
