package by.bogdevich.training.airline.datamodel;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;

@Entity
public class ModelPlane extends AbstractModel {
	
	@ManyToOne(targetEntity = ManufacturedPlane.class, fetch = FetchType.LAZY)
	private ManufacturedPlane manufacturedPlane;
	
	@Column
	private String model;
	
	@Column
	private Integer colPassangersBuisnes;
	
	@Column
	private Integer colPassangersFirstclass;
	
	@Column
	private Integer colPassangersEconomy;
	
	@Column
	private Integer weightAllBaggage;
	
	@Column
	private Integer avgSpeed;
	
	@Column
	@Enumerated(value = EnumType.ORDINAL)
	private ClassWeight classWeight;

	public ManufacturedPlane getManufacturedPlane() {
		return manufacturedPlane;
	}

	public void setManufacturedPlane(ManufacturedPlane manufacturedPlane) {
		this.manufacturedPlane = manufacturedPlane;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public Integer getColPassangersBuisnes() {
		return colPassangersBuisnes;
	}

	public void setColPassangersBuisnes(Integer colPassangersBuisnes) {
		this.colPassangersBuisnes = colPassangersBuisnes;
	}

	public Integer getColPassangersFirstclass() {
		return colPassangersFirstclass;
	}

	public void setColPassangersFirstclass(Integer colPassangersFirstclass) {
		this.colPassangersFirstclass = colPassangersFirstclass;
	}

	public Integer getColPassangersEconomy() {
		return colPassangersEconomy;
	}

	public void setColPassangersEconomy(Integer colPassangersEconomy) {
		this.colPassangersEconomy = colPassangersEconomy;
	}

	public Integer getWeightAllBaggage() {
		return weightAllBaggage;
	}

	public void setWeightAllBaggage(Integer weightAllBaggage) {
		this.weightAllBaggage = weightAllBaggage;
	}

	public Integer getAvgSpeed() {
		return avgSpeed;
	}

	public void setAvgSpeed(Integer avgSpeed) {
		this.avgSpeed = avgSpeed;
	}

	public ClassWeight getClassWeight() {
		return classWeight;
	}

	public void setClassWeight(ClassWeight classWeight) {
		this.classWeight = classWeight;
	}

	@Override
	public String toString() {
		return "ModelPlane [manufacturedPlane=" + manufacturedPlane + ", model=" + model + ", colPassangersBuisnes="
				+ colPassangersBuisnes + ", colPassangersFirstclass=" + colPassangersFirstclass
				+ ", colPassangersEconomy=" + colPassangersEconomy + ", weightAllBaggage=" + weightAllBaggage
				+ ", avgSpeed=" + avgSpeed + ", classWeight=" + classWeight + "]";
	}

	
	
}
