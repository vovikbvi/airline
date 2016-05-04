package by.bogdevich.training.airline.datamodel;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Plane extends AbstractModel {
	
	@Column
	private String bortNumber;
	
	@ManyToOne(targetEntity = ModelPlane.class, fetch = FetchType.LAZY)
	private ModelPlane modelPlane;
	
	
    @OneToMany(mappedBy = "plane", fetch = FetchType.LAZY)
    private List<Flight> flights;


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

	@Override
	public String toString() {
		return "Plane [bortNumber=" + bortNumber + ", modelPlane=" + modelPlane + "]";
	}

}
