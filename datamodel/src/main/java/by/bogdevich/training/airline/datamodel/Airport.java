package by.bogdevich.training.airline.datamodel;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Airport extends AbstractModel {

	@Column
	private String name;

	@Column
	private String codeIata;

	@Column
	private String codeIcao;

	@ManyToOne(targetEntity = City.class, fetch = FetchType.LAZY)
	private City city;

	@Column
	@Enumerated(value = EnumType.ORDINAL)
	private ClassWeight classWeight;

	@Column(name="coordinates_x")
	private Double coordinatesX;

	@Column(name="coordinates_y")
	private Double coordinatesY;
	
    @OneToMany(mappedBy = "airportStart", fetch = FetchType.LAZY)
    private List<FlightCatalog> departureFlieghts;

    @OneToMany(mappedBy = "airportFinish", fetch = FetchType.LAZY)
    private List<FlightCatalog> arrivalFlieghts;


	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCodeIata() {
		return codeIata;
	}

	public void setCodeIata(String codeIata) {
		this.codeIata = codeIata;
	}

	public String getCodeIcao() {
		return codeIcao;
	}

	public void setCodeIcao(String codeIcao) {
		this.codeIcao = codeIcao;
	}

	public City getCity() {
		return city;
	}

	public void setCity(City city) {
		this.city = city;
	}

	public ClassWeight getClassWeight() {
		return classWeight;
	}

	public void setClassWeight(ClassWeight classWeight) {
		this.classWeight = classWeight;
	}

	public Double getCoordinatesX() {
		return coordinatesX;
	}

	public void setCoordinatesX(Double coordinatesX) {
		this.coordinatesX = coordinatesX;
	}

	public Double getCoordinatesY() {
		return coordinatesY;
	}

	public void setCoordinatesY(Double coordinatesY) {
		this.coordinatesY = coordinatesY;
	}

	@Override
	public String toString() {
		return "Airport [name=" + name + ", codeIata=" + codeIata + ", codeIcao=" + codeIcao + ", city=" + city
				+ ", classWeight=" + classWeight + ", coordinatesX=" + coordinatesX + ", coordinatesY=" + coordinatesY
				+ "]";
	}

	
	
}
