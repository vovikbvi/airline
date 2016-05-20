package by.bogdevich.training.airline.dataaccess.filtres;

public class ModelPlaneFilter extends AbstractFilter {

	private String model;
	private boolean setFetchManufactured;

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public boolean isSetFetchManufactured() {
		return setFetchManufactured;
	}

	public void setSetFetchManufactured(boolean setFetchManufactured) {
		this.setFetchManufactured = setFetchManufactured;
	}

}
