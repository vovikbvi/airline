package by.bogdevich.training.airline.dataaccess.filtres;

public class PlaneFilter extends AbstractFilter {

	private String bortNumber;
	private boolean fetchModelPlane;

	public String getBortNumber() {
		return bortNumber;
	}

	public void setBortNumber(String bortNumber) {
		this.bortNumber = bortNumber;
	}

	public boolean getFetchModelPlane() {
		return fetchModelPlane;
	}

	public void setFetchModelPlane(boolean fetchModelPlane) {
		this.fetchModelPlane = fetchModelPlane;
	}

}
