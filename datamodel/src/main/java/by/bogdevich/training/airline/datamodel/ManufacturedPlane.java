package by.bogdevich.training.airline.datamodel;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;

@Entity
public class ManufacturedPlane extends AbstractModel {
	
	@Column
	private String name;
	
    @OneToMany(mappedBy = "manufacturedPlane", fetch = FetchType.LAZY)
    private List<ModelPlane> variantModelPlanes;


	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "ManufacturedPlane [name=" + name + "]";
	}

}
