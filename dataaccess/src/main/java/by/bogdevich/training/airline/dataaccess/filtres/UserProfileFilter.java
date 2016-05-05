package by.bogdevich.training.airline.dataaccess.filtres;

public class UserProfileFilter extends AbstractFilter {


	private String firstName;
	private boolean isFetchCredentials;
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public boolean isFetchCredentials() {
		return isFetchCredentials;
	}
	public void setFetchCredentials(boolean isFetchCredentials) {
		this.isFetchCredentials = isFetchCredentials;
	}


}
