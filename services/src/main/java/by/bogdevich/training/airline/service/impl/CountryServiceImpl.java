package by.bogdevich.training.airline.service.impl;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import by.bogdevich.training.airline.dataaccess.CountryDao;
import by.bogdevich.training.airline.datamodel.Country;
import by.bogdevich.training.airline.service.CountryService;

@Service
public class CountryServiceImpl implements CountryService{

	@Inject
	CountryDao countryDao;
	
	
	@Override
	public void coumtryPrint(Country country) {
		// TODO Auto-generated method stub
		
	}

}
