package by.bogdevich.training.airline.datamodel;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;

@Entity
public class Plane extends AbstractModel {
	
	@Column
	private String bortNumber;
	
	@ManyToOne(targetEntity = ModelPlane.class, fetch = FetchType.LAZY)
	private ModelPlane modelPlane;

	public String getBortNumber() {
		return bortNumber;
	}

	public void setBortNumber(String bortNumber) {
		this.bortNumber = bortNumber;
	}

	public ModelPlane getModelPlane() {
		return modelPlane;
	}

	public void setModelPlane(ModelPlane modelPlane) {
		this.modelPlane = modelPlane;
	}

}
