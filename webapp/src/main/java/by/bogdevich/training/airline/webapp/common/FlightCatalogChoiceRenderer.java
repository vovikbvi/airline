package by.bogdevich.training.airline.webapp.common;

import org.apache.wicket.markup.html.form.ChoiceRenderer;

import by.bogdevich.training.airline.datamodel.FlightCatalog;


public class FlightCatalogChoiceRenderer extends ChoiceRenderer<FlightCatalog>{
	
	public static final FlightCatalogChoiceRenderer INSTANCE = new FlightCatalogChoiceRenderer();
	
	   @Override
	    public Object getDisplayValue(FlightCatalog object) {
	        return object.getId();
	    }

	    @Override
	    public String getIdValue(FlightCatalog object, int index) {
	        return String.valueOf(object.getId());
	    }


}
