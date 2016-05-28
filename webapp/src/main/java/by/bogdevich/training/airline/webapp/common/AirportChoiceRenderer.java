package by.bogdevich.training.airline.webapp.common;

import org.apache.wicket.markup.html.form.ChoiceRenderer;

import by.bogdevich.training.airline.datamodel.Airport;
import by.bogdevich.training.airline.datamodel.Flight;


public class AirportChoiceRenderer extends ChoiceRenderer<Airport>{
	
	public static final AirportChoiceRenderer INSTANCE = new AirportChoiceRenderer();
	
	   @Override
	    public Object getDisplayValue(Airport object) {
	        return object.getName();
	    }

	    @Override
	    public String getIdValue(Airport object, int index) {
	        return String.valueOf(object.getId());
	    }


}
