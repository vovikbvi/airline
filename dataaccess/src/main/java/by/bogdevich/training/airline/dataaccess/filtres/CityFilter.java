package by.bogdevich.training.airline.dataaccess.filtres;

public class CityFilter extends AbstractFilter {
	private String cityName;
	private boolean fetchCountry;

	public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	public boolean isFetchCountry() {
		return fetchCountry;
	}

	public void setFetchCountry(boolean fetchCountry) {
		this.fetchCountry = fetchCountry;
	}

}
