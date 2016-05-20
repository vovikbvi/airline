package by.bogdevich.training.airline.dataaccess.filtres;

public class CityFilter extends AbstractFilter{
	private String cityName;
	private boolean setFetchCountry;

	public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	public boolean isSetFetchCountry() {
		return setFetchCountry;
	}

	public void setSetFetchCountry(boolean setFetchCountry) {
		this.setFetchCountry = setFetchCountry;
	}
	
	

}
