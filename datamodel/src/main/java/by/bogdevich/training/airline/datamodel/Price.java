package by.bogdevich.training.airline.datamodel;


import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;


@Entity
public class Price extends AbstractModel {
	
	@Column
	//@Convert(converter = LocalDateTimePersistenceConverter.class)
	private Date dataChange;
	
	@Column
	private Double basicPrice;



	public Date getDataChange() {
		return dataChange;
	}


	public void setDataChange(Date dataChange) {
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
