package by.bogdevich.training.airline.dataaccess;

import java.util.List;

import by.bogdevich.training.airline.dataaccess.filtres.CityFilter;
import by.bogdevich.training.airline.datamodel.City;

public interface CityDao extends AbstractDao<City, Long>{

	List<City> getRecordsSorted(CityFilter filter);

}
