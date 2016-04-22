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


}
