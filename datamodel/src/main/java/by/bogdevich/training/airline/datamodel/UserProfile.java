package by.bogdevich.training.airline.datamodel;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;


@Entity
public class UserProfile extends AbstractModel {

	@Column(updatable = false)
	private String login;

	@Column
	private String password;

	@Column
	private String firstName;

	@Column
	private String lastName;

	@Column
	private String email;

	@Column
	private String passportNumber;

	@Column
	private String phoneNumber;

	@Column
	private Integer countOder;

	@Column
	private Boolean vip;

	@Column
	// @Convert(converter = LocalDateTimePersistenceConverter.class)
	private Date dateRegistr;

	@Column
	@Enumerated(value = EnumType.ORDINAL)
	private UserRole role;

	@Column
	private Boolean aceptRegistr;

	@OneToMany(mappedBy = "userProfile", fetch = FetchType.LAZY)
	private List<Ticket> boughtTickets;

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassportNumber() {
		return passportNumber;
	}

	public void setPassportNumber(String passportNumber) {
		this.passportNumber = passportNumber;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public Integer getCountOder() {
		return countOder;
	}

	public void setCountOder(Integer countOder) {
		this.countOder = countOder;
	}

	public Boolean getVip() {
		return vip;
	}

	public void setVip(Boolean vip) {
		this.vip = vip;
	}

	public Date getDateRegistr() {
		return dateRegistr;
	}

	public void setDateRegistr(Date dateRegistr) {
		this.dateRegistr = dateRegistr;
	}

	public UserRole getRole() {
		return role;
	}

	public void setRole(UserRole role) {
		this.role = role;
	}

	public Boolean getAceptRegistr() {
		return aceptRegistr;
	}

	public void setAceptRegistr(Boolean aceptRegistr) {
		this.aceptRegistr = aceptRegistr;
	}

	@Override
	public String toString() {
		return "User [login=" + login + ", password=" + password + ", firstName=" + firstName + ", lastName=" + lastName
				+ ", eMail=" + email + ", passportNumber=" + passportNumber + ", phoneNumber=" + phoneNumber
				+ ", countOder=" + countOder + ", vip=" + vip + ", dateRegistr=" + dateRegistr + ", role=" + role
				+ ", aceptRegistr=" + aceptRegistr + "]";
	}

}
