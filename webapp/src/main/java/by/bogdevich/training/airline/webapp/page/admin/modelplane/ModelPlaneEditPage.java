package by.bogdevich.training.airline.webapp.page.admin.modelplane;

import javax.inject.Inject;

import by.bogdevich.training.airline.datamodel.ModelPlane;
import by.bogdevich.training.airline.datamodel.Plane;
import by.bogdevich.training.airline.service.ModelPlaneService;
import by.bogdevich.training.airline.service.PriceService;
import by.bogdevich.training.airline.service.UserProfileService;
import by.bogdevich.training.airline.webapp.page.AbstractPage;



public class ModelPlaneEditPage extends AbstractPage {
	
	@Inject
	private ModelPlaneService modelPlaneService;
	
	private ModelPlane modelPlane;
	
    public ModelPlaneEditPage() {
        super();
    }

	public ModelPlaneEditPage(ModelPlane modelPlane) {
		super();
		this.modelPlane = modelPlane;
	}

    @Override
    protected void onInitialize() {
    	super.onInitialize();
    	
    	
    	
    }
}
