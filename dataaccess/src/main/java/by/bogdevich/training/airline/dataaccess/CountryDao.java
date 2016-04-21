package by.bogdevich.training.airline.dataaccess;

import by.bogdevich.training.airline.datamodel.Country;

public interface CountryDao {
	
	Country get(Long id);
	
	Country save();
}
