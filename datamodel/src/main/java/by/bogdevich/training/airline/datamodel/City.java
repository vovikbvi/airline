package by.bogdevich.training.airline.datamodel;

public class City extends AbstractModel {
	private String city;
	private Country country;

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public Country getCountry() {
		return country;
	}

	public void setCountry(Country country) {
		this.country = country;
	}

}
