package by.bogdevich.training.airline.datamodel;

public class Airport extends AbstractModel {
	private String codeIata;
	private String codeIcao;
	private City city;
	private ClassWeight classWeight;
	private Double coordinatesX;
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
