package by.bogdevich.training.airline.dataaccess;

import java.util.List;

import by.bogdevich.training.airline.dataaccess.filtres.FlightCatalogFilter;
import by.bogdevich.training.airline.datamodel.Airport;
import by.bogdevich.training.airline.datamodel.Country;
import by.bogdevich.training.airline.datamodel.FlightCatalog;

public interface FlightCatalogDao extends AbstractDao<FlightCatalog, Long>{

	List<FlightCatalog> getRecordsSorted(FlightCatalogFilter filter);

	FlightCatalog getFlightCatalogWithFetch(FlightCatalog flightCatalog);

	Long count(FlightCatalogFilter filter);

	//Country getCountry(Airport airport);

	


}
