package by.bogdevich.training.airline.dataaccess.filtres;

import javax.persistence.metamodel.SingularAttribute;

public class UserProfileFilter {

	private Boolean showLogin;
	private String firstName;
	private SingularAttribute sortProperty;
	private boolean sortOrder;
	private Integer offset;
	private Integer limit;
	private boolean isFetchCredentials;
	
	

	public Boolean getShowLogin() {
		return showLogin;
	}

	public void setShowLogin(Boolean showLogin) {
		this.showLogin = showLogin;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public SingularAttribute getSortProperty() {
		return sortProperty;
	}

	public void setSortProperty(SingularAttribute sortProperty) {
		this.sortProperty = sortProperty;
	}

	public boolean isSortOrder() {
		return sortOrder;
	}

	public void setSortOrder(boolean sortOrder) {
		this.sortOrder = sortOrder;
	}

	public Integer getOffset() {
		return offset;
	}

	public void setOffset(Integer offset) {
		this.offset = offset;
	}

	public Integer getLimit() {
		return limit;
	}

	public void setLimit(Integer limit) {
		this.limit = limit;
	}

	public boolean isFetchCredentials() {
		return isFetchCredentials;
	}

	public void setFetchCredentials(boolean isFetchCredentials) {
		this.isFetchCredentials = isFetchCredentials;
	}

}
