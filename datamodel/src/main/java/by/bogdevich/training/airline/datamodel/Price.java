package by.bogdevich.training.airline.datamodel;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;

import by.bogdevich.training.airline.datamodel.util.LocalDateTimePersistenceConverter;

@Entity
public class Price extends AbstractModel {
	
	@Column
	@Convert(converter = LocalDateTimePersistenceConverter.class)
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

	@Override
	public String toString() {
		return "Price [dataChange=" + dataChange + ", basicPrice=" + basicPrice + "]";
	}


}
