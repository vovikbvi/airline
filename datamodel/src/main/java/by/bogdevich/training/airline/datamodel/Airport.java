package by.bogdevich.training.airline.datamodel;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;

@Entity
public class Airport extends AbstractModel {
	
	@Column
	private String codeIata;
	
	@Column
	private String codeIcao;
	
	@ManyToOne(targetEntity = City.class, fetch = FetchType.LAZY)
	private City city;
	
	@Column
	@Enumerated(value = EnumType.STRING)
	private ClassWeight classWeight;
	
	@Column
	private Double coordinatesX;
	
	@Column
	private Double coordinatesY;

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

}
