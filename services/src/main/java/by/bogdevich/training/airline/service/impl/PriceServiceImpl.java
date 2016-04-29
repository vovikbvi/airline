package by.bogdevich.training.airline.service.impl;

import java.util.List;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import by.bogdevich.training.airline.dataaccess.PriceDao;
import by.bogdevich.training.airline.datamodel.Price;
import by.bogdevich.training.airline.service.PriceService;

@Service
public class PriceServiceImpl implements PriceService {
	private static Logger LOGGER = LoggerFactory.getLogger(CityServiceImpl.class);

	@Inject
	PriceDao priceDao;

	@Override
	public void insert(Price price) {
		priceDao.insert(price);

		LOGGER.info("Insert price {}", price);
	}

	@Override
	public void update(Price price) {
		priceDao.update(price);

		LOGGER.info("Update price {}", price);
	}

	@Override
	public void delete(Long id) {
		Price price = priceDao.get(id);
		priceDao.delete(id);

		LOGGER.info("Delete price {}", price);
	}

	@Override
	public Price get(Long id) {
		return priceDao.get(id);
	}

	@Override
	public List<Price> getAll() {
		return priceDao.getAll();
	}

}
