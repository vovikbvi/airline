package by.bogdevich.training.airline.dataaccess.impl;

import org.springframework.stereotype.Repository;

import by.bogdevich.training.airline.dataaccess.CountryDao;
import by.bogdevich.training.airline.datamodel.Country;


@Repository
public class CountryDaoImpl extends AbstractDaoImpl implements CountryDao {

	@Override
	public Country get(Long id) {
		return null;
	}

	@Override
	public Country save() {
		return null;
	}

}
