package by.bogdevich.training.airline.dataaccess;

import java.util.List;

import by.bogdevich.training.airline.dataaccess.filtres.PriceFilter;
import by.bogdevich.training.airline.datamodel.Price;

public interface PriceDao extends AbstractDao<Price, Long, PriceFilter>{

	List<Price> getRecordsSorted(PriceFilter filter);

}
