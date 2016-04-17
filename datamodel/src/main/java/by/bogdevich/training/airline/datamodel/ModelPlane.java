package by.bogdevich.training.airline.datamodel;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;

@Entity
public class ModelPlane extends AbstractModel {
	
	@ManyToOne(targetEntity = ManufacturedPlain.class, fetch = FetchType.LAZY)
	private ManufacturedPlain manufacturedPlain;
	
	@Column
	private String model;
	
	@Column
	private Integer colPassangerBuisnes;
	
	@Column
	private Integer colPassangerFirstclass;
	
	@Column
	private Integer colPassangerEconomy;
	
	@Column
	private Integer weightAllBaggage;
	
	@Column
	private Integer avgSpeed;
	
	@Column
	@Enumerated(value = EnumType.STRING)
	private ClassWeight classWeight;

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

}
