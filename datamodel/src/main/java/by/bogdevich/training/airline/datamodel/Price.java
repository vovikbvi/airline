package by.bogdevich.training.airline.datamodel;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class Price extends AbstractModel {
	
	@Column
	private LocalDateTime dataChange;
	
	@Column
	private Double basicPrice;

	public LocalDateTime getDataChange() {
		return dataChange;
	}

	public void setDataChange(LocalDateTime dataChange) {
		this.dataChange = dataChange;
	}

	public Double getBasicPrice() {
		return basicPrice;
	}

	public void setBasicPrice(Double basicPrice) {
		this.basicPrice = basicPrice;
	}

}
