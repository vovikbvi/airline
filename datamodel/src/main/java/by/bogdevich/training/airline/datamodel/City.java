package by.bogdevich.training.airline.datamodel;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class City extends AbstractModel {

	@Column
	private String name;

	@ManyToOne(targetEntity = Country.class, fetch = FetchType.LAZY)
	private Country country;
	
	@Column
	private Double timezone;
	
    @OneToMany(mappedBy = "city", fetch = FetchType.LAZY)
    private List<Airport> airports;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Country getCountry() {
		return country;
	}

	public void setCountry(Country country) {
		this.country = country;
	}

	public Double getTimezone() {
		return timezone;
	}

	public void setTimezone(Double timezone) {
		this.timezone = timezone;
	}

	@Override
	public String toString() {
		return "City [name=" + name + ", timezone=" + timezone + "]";
	}


	
	
}
