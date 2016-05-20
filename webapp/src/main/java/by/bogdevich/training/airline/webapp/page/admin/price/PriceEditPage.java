package by.bogdevich.training.airline.webapp.page.admin.price;

import javax.inject.Inject;

import by.bogdevich.training.airline.service.PriceService;
import by.bogdevich.training.airline.service.UserProfileService;
import by.bogdevich.training.airline.webapp.page.AbstractPage;



public class PriceEditPage extends AbstractPage {
	
	@Inject
	PriceService priceService;

    public PriceEditPage() {
        super();
    
              
    }

}
