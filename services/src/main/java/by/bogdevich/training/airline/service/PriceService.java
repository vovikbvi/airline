package by.bogdevich.training.airline.service;

import java.util.List;

import javax.transaction.Transactional;

import by.bogdevich.training.airline.dataaccess.filtres.PriceFilter;
import by.bogdevich.training.airline.datamodel.Price;

public interface PriceService {

	@Transactional
	void insert(Price price);

	@Transactional
	void update(Price price);

	@Transactional
	void delete(Long id);

	Price get(Long id);

	List<Price> getAll();

	List<Price> getRecordsSorted(PriceFilter filter);

	Long count(PriceFilter filter);

}