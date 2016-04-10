package by.bogdevich.training.airline.datamodel;

public class ModelPlane extends AbstractModel {
	private ManufacturedPlain manufacturedPlain;
	private String model;
	private Integer colPassangerBuisnes;
	private Integer colPassangerFirstclass;
	private Integer colPassangerEconomy;
	private Integer weightAllBaggage;
	private Integer AvgSpeed;
	private ClassWeight classWeight;

	public ClassWeight getClassWeight() {
		return classWeight;
	}

	public void setClassWeight(ClassWeight classWeight) {
		this.classWeight = classWeight;
	}

	public ManufacturedPlain getManufacturedPlain() {
		return manufacturedPlain;
	}

	public void setManufacturedPlain(ManufacturedPlain manufacturedPlain) {
		this.manufacturedPlain = manufacturedPlain;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public Integer getColPassangerBuisnes() {
		return colPassangerBuisnes;
	}

	public void setColPassangerBuisnes(Integer colPassangerBuisnes) {
		this.colPassangerBuisnes = colPassangerBuisnes;
	}

	public Integer getColPassangerFirstclass() {
		return colPassangerFirstclass;
	}

	public void setColPassangerFirstclass(Integer colPassangerFirstclass) {
		this.colPassangerFirstclass = colPassangerFirstclass;
	}

	public Integer getColPassangerEconomy() {
		return colPassangerEconomy;
	}

	public void setColPassangerEconomy(Integer colPassangerEconomy) {
		this.colPassangerEconomy = colPassangerEconomy;
	}

	public Integer getWeightAllBaggage() {
		return weightAllBaggage;
	}

	public void setWeightAllBaggage(Integer weightAllBaggage) {
		this.weightAllBaggage = weightAllBaggage;
	}

	public Integer getAvgSpeed() {
		return AvgSpeed;
	}

	public void setAvgSpeed(Integer avgSpeed) {
		AvgSpeed = avgSpeed;
	}

}
