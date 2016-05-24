package by.bogdevich.training.airline.webapp.page.admin.price;

import javax.inject.Inject;

import by.bogdevich.training.airline.datamodel.Price;
import by.bogdevich.training.airline.service.PriceService;
import by.bogdevich.training.airline.service.UserProfileService;
import by.bogdevich.training.airline.webapp.page.AbstractPage;



public class PriceEditPage extends AbstractPage {
	
	@Inject
	private PriceService priceService;
	
	private Price price; 
	

    public PriceEditPage() {
        super();
      }


	public PriceEditPage(Price price) {
		super();
		this.price = price;
	}

    @Override
    protected void onInitialize() {
    	super.onInitialize();
    	
    	
    	
    	
    }
}
