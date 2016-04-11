package by.bogdevich.training.airline.datamodel;

import java.util.Date;

public class Price extends AbstractModel {
	private Date dataChange;
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

}
