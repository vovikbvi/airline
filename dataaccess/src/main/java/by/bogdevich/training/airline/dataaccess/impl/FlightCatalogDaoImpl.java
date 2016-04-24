package by.bogdevich.training.airline.dataaccess.impl;

import org.springframework.stereotype.Repository;

import by.bogdevich.training.airline.dataaccess.FlightCatalogDao;
import by.bogdevich.training.airline.datamodel.FlightCatalog;

@Repository
public class FlightCatalogDaoImpl extends AbstractDaoImpl<FlightCatalog, Long> implements FlightCatalogDao {

	protected FlightCatalogDaoImpl() {
		super(FlightCatalog.class);
	}

}
