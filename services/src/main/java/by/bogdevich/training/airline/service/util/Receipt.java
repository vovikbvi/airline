package by.bogdevich.training.airline.service.util;

import java.time.LocalDateTime;

class Receipt {
	private String purpose;
	private double amount;
	private LocalDateTime date = LocalDateTime.now();
	private String name;

	public Receipt(String purpose, double amount, String name) {
		this.purpose = purpose;
		this.amount = amount;
		this.name = name;
	}

	public String getPurpose() {
		return purpose;
	}

	public void setPurpose(String purpose) {
		this.purpose = purpose;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public LocalDateTime getDate() {
		return date;
	}

	public void setDate(LocalDateTime date) {
		this.date = date;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
