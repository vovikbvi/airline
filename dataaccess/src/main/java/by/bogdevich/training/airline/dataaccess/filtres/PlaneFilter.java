package by.bogdevich.training.airline.dataaccess.filtres;

public class PlaneFilter extends AbstractFilter {

	private String bortNumber;
	private boolean setFetchModelPlane;

	public String getBortNumber() {
		return bortNumber;
	}

	public void setBortNumber(String bortNumber) {
		this.bortNumber = bortNumber;
	}

	public boolean isSetFetchModelPlane() {
		return setFetchModelPlane;
	}

	public void setSetFetchModelPlane(boolean setFetchModelPlane) {
		this.setFetchModelPlane = setFetchModelPlane;
	}

}
