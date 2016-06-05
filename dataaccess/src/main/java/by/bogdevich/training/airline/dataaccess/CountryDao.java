package by.bogdevich.training.airline.dataaccess;

import java.util.List;

import by.bogdevich.training.airline.dataaccess.filtres.CountryFilter;
import by.bogdevich.training.airline.datamodel.Country;

public interface CountryDao extends AbstractDao<Country, Long, CountryFilter>{

	List<Country> getRecordsSorted(CountryFilter filter);


}
