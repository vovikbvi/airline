package by.bogdevich.training.airline.webapp.common;

import javax.inject.Inject;

import org.apache.wicket.injection.Injector;
import org.apache.wicket.markup.html.form.ChoiceRenderer;

import by.bogdevich.training.airline.datamodel.FlightCatalog;
import by.bogdevich.training.airline.service.FlightCatalogService;


public class FlightCatalogChoiceRenderer extends ChoiceRenderer<FlightCatalog>{
	
	public static final FlightCatalogChoiceRenderer INSTANCE = new FlightCatalogChoiceRenderer();
	
	@Inject
	private FlightCatalogService catalogService;
	
	   private FlightCatalogChoiceRenderer() {
		super();
		Injector.get().inject(this);
	}

	@Override
	    public Object getDisplayValue(FlightCatalog object) {
		   FlightCatalog fullFlightCatalog = catalogService.getFullFlieghtCatalog(object);
	        return (fullFlightCatalog.getAirportStart().getName()+" - "+fullFlightCatalog.getAirportFinish().getName());
	    }

	    @Override
	    public String getIdValue(FlightCatalog object, int index) {
	        return String.valueOf(object.getId());
	    }


}
