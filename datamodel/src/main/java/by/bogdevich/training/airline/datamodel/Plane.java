package by.bogdevich.training.airline.datamodel;

public class Plane extends AbstractModel {
	private String bortNumber;
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
