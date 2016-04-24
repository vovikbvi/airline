package by.bogdevich.training.airline.dataaccess.impl;

import org.springframework.stereotype.Repository;

import by.bogdevich.training.airline.dataaccess.PriceDao;
import by.bogdevich.training.airline.datamodel.Price;

@Repository
public class PriceDaoImpl extends AbstractDaoImpl<Price, Long> implements PriceDao {

	protected PriceDaoImpl() {
		super(Price.class);
	}

}
