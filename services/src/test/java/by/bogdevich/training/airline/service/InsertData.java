package by.bogdevich.training.airline.service;

import javax.inject.Inject;

import by.bogdevich.training.airline.datamodel.Country;

public class InsertData {
	
	@Inject
	private CountryService countryService;
	
	public Country countryAdd() {
		Country country = new Country();
		country.setName("FR");
		countryService.insert(country);
		return countryService.get(country.getId());

	}


}
