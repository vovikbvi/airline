package by.bogdevich.training.airline.webapp.common.renderer;

import org.apache.wicket.markup.html.form.ChoiceRenderer;

import by.bogdevich.training.airline.datamodel.Flight;


public class FlightChoiceRenderer extends ChoiceRenderer<Flight>{
	
	public static final FlightChoiceRenderer INSTANCE = new FlightChoiceRenderer();
	
	   @Override
	    public Object getDisplayValue(Flight object) {
	        return object.getId();
	    }

	    @Override
	    public String getIdValue(Flight object, int index) {
	        return String.valueOf(object.getId());
	    }


}
